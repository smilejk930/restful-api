package kr.app.restfulapi.domain.common.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import kr.app.restfulapi.domain.common.file.dto.FileReqstDto;
import kr.app.restfulapi.domain.common.file.dto.FileRspnsDto;
import kr.app.restfulapi.domain.common.file.entity.BaseFileEntity;
import kr.app.restfulapi.domain.common.file.repository.BaseFileRepository;
import kr.app.restfulapi.global.response.error.FieldErrorReason;
import kr.app.restfulapi.global.response.error.exception.BusinessException;
import kr.app.restfulapi.global.response.error.exception.FieldNullPointException;
import kr.app.restfulapi.global.response.error.exception.IllegalArgumentException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.global.util.CustomDateUtils;
import kr.app.restfulapi.global.util.CustomFileUtils;
import kr.app.restfulapi.global.util.CustomObjectUtils;
import kr.app.restfulapi.global.util.SecurityContextHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
public abstract class BaseFileService<T extends BaseFileEntity> {

  private final T fileEntity;
  private final BaseFileRepository<T> fileRepository;

  @Value("${file.upload-dir}")
  private String uploadDir;

  @Transactional(readOnly = true)
  public List<FileRspnsDto> getAllFiles(FileReqstDto<T> fileReqstDto) {

    if (fileReqstDto.isAnyNullOfRequiredlField()) {
      log.error("FileReqstDto의 필요 속성값들이 NULL");
      throw new FieldNullPointException();
    }

    List<T> fileEntities = fileRepository.findAllWithCriteria(fileReqstDto.toEntity(fileEntity));

    return fileEntities.stream().map(FileRspnsDto::toDto).toList();
  }

  @Transactional
  public List<FileRspnsDto> storeFiles(List<MultipartFile> files, FileReqstDto<T> fileReqstDto) {

    if (CustomObjectUtils.isAnyNullOfFields(fileReqstDto, "delFileTsids")) {
      log.error("fileReqstDto의 속성값들 중 NULL 존재");
      throw new FieldNullPointException();
    }

    // 파일 순번
    Long maxFileSeq = fileRepository.findByMaxFileSeq(fileReqstDto.toEntity(fileEntity));

    List<FileRspnsDto> uploadedFiles = new ArrayList<>();

    AtomicLong atFileSeq = new AtomicLong(maxFileSeq);

    Stream.ofNullable(files).flatMap(List<MultipartFile>::stream).forEach(file -> {

      if (!CustomFileUtils.isAllowedExtension(file)) {
        throw new IllegalArgumentException(
            file.getOriginalFilename(),
            CustomFileUtils.getFileExtension(file),
            FieldErrorReason.FILE_EXTENSION_NOT_ALLOWED);
      }

      String cleanedFilename = StringUtils.cleanPath(file.getOriginalFilename());
      String saveFileName = "FILE_" + CustomDateUtils.getCurrentDateTimeMillisecond();
      String fileStrgPath = uploadDir + File.separator + fileReqstDto.getFileGroupNm() + File.separator + CustomDateUtils.getCurrentYear()
          + File.separator + CustomDateUtils.getCurrentMonth();
      Path savePath = Paths.get(fileStrgPath).resolve(saveFileName);

      try {
        Files.createDirectories(savePath.getParent());
        Files.copy(file.getInputStream(), savePath);
      } catch (IOException e) {
        throw new BusinessException(e);
      }

      T saveFileEntity = (T) fileEntity.toBuilder()
          .fileNm(cleanedFilename)
          .fileGroupNm(fileReqstDto.getFileGroupNm())
          .rfrncTsid(fileReqstDto.getRfrncTsid())
          .fileClsfNm(fileReqstDto.getFileClsfNm())
          .fileSeq(atFileSeq.getAndIncrement())
          .strgFileNm(saveFileName)
          .fileStrgPath(savePath.getParent().toString())
          .fileExtnNm(CustomFileUtils.getFileExtension(cleanedFilename))
          .fileSize(file.getSize())
          .build();

      T savedFileEntity = fileRepository.save(saveFileEntity);

      if (savedFileEntity != null) {
        uploadedFiles.add(FileRspnsDto.toDto(savedFileEntity));
      }
    });

    return uploadedFiles;
  }

  @Transactional
  public void deleteFiles(List<String> fileTsids, List<String> delFileTsids) {

    if (!fileTsids.isEmpty()) {
      String userTsid = SecurityContextHelper.getUserPrincipal().getUserTsid();

      fileTsids.forEach(fileTsid -> delFileTsids.forEach(delFileTsid -> {
        if (fileTsid.equals(delFileTsid)) {
          fileRepository.findByFileTsidAndDelYn(fileTsid, "N").ifPresent(file -> {
            file.setDelYn("Y");
            file.setMdfrTsid(userTsid);
            file.setMdfcnDt(LocalDateTime.now());
          });
        }
      }));
    }
  }

  @Transactional
  public void deleteFilesByFileTsids(List<String> fileTsids) {

    if (!fileTsids.isEmpty()) {
      String userTsid = SecurityContextHelper.getUserPrincipal().getUserTsid();

      fileTsids.forEach(fileTsid -> fileRepository.findByFileTsidAndDelYn(fileTsid, "N").ifPresent(file -> {
        file.setDelYn("Y");
        file.setMdfrTsid(userTsid);
        file.setMdfcnDt(LocalDateTime.now());
      }));
    }
  }

  @Transactional(readOnly = true)
  public Optional<FileRspnsDto> getFile(String fileTsid) {

    Optional<FileRspnsDto> optFileRspnsDto = fileRepository.findByFileTsidAndDelYn(fileTsid, "N").map(FileRspnsDto::toDto);

    return optFileRspnsDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public Optional<FileRspnsDto> getFileDownload(String fileTsid) {

    Optional<FileRspnsDto> optFileRspnsDto = fileRepository.findByFileTsidAndDelYn(fileTsid, "N").map(fileEntity -> {
      fileEntity.setDwnldCnt(fileEntity.getDwnldCnt() + 1);

      return FileRspnsDto.toDto(fileEntity);
    });

    return optFileRspnsDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public byte[] getImage(FileRspnsDto fileRspnsDto) throws IOException {
    Path imagePath = Paths.get(fileRspnsDto.fileStrgPath()).resolve(fileRspnsDto.strgFileNm());
    return Files.readAllBytes(imagePath);
  }
}
