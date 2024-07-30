package kr.app.restfulapi.domain.common.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import kr.app.restfulapi.domain.common.file.repository.BaseFileRepository;
import kr.app.restfulapi.global.entity.BaseFileEntity;
import kr.app.restfulapi.global.response.error.FieldErrorReason;
import kr.app.restfulapi.global.response.error.exception.BusinessException;
import kr.app.restfulapi.global.response.error.exception.FieldNullPointException;
import kr.app.restfulapi.global.response.error.exception.IllegalArgumentException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.global.util.CustomDateUtils;
import kr.app.restfulapi.global.util.CustomFileUtils;
import kr.app.restfulapi.global.util.CustomObjectUtils;
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

    List<T> fileEntityList = fileRepository.findAllWithCriteria(fileReqstDto.toEntity(fileEntity));

    return fileEntityList.stream().map(FileRspnsDto::toDto).toList();
  }

  @Transactional
  public List<FileRspnsDto> storeFiles(List<MultipartFile> files, FileReqstDto<T> fileReqstDto) {

    if (CustomObjectUtils.isAnyNullOfFields(fileReqstDto)) {
      log.error("fileReqstDto의 속성값들 중 NULL 존재");
      throw new FieldNullPointException();
    }

    List<FileRspnsDto> uploadedFiles = new ArrayList<>();

    AtomicLong atFileSn = new AtomicLong(1L);

    Stream.ofNullable(files).flatMap(List<MultipartFile>::stream).forEach(file -> {

      if (!CustomFileUtils.isAllowedExtension(file)) {
        throw new IllegalArgumentException(
            file.getOriginalFilename(),
            CustomFileUtils.getFileExtension(file),
            FieldErrorReason.FILE_EXTENSION_NOT_ALLOWED);
      }

      String cleanedFilename = StringUtils.cleanPath(file.getOriginalFilename());
      String saveFileName = "FILE_" + CustomDateUtils.getCurrentDateTimeMillisecond();
      String fileStreCours = uploadDir + File.separator + fileReqstDto.getFileGroupNm() + File.separator + CustomDateUtils.getCurrentYear()
          + File.separator + CustomDateUtils.getCurrentMonth();
      Path savePath = Paths.get(fileStreCours).resolve(saveFileName);

      try {
        Files.createDirectories(savePath.getParent());
        Files.copy(file.getInputStream(), savePath);
      } catch (IOException e) {
        throw new BusinessException(e);
      }

      T saveFileEntity = (T) fileEntity.toBuilder()
          .fileNm(cleanedFilename)
          .fileGroupNm(fileReqstDto.getFileGroupNm())
          .refrnId(fileReqstDto.getRefrnId())
          .fileSectValue(fileReqstDto.getFileSectValue())
          .fileSn(atFileSn.getAndIncrement())
          .fileStreNm(saveFileName)
          .fileStreCours(savePath.getParent().toString())
          .fileExtsnNm(CustomFileUtils.getFileExtension(cleanedFilename))
          .fileSize(file.getSize())
          .build();

      T savedFileEntity = fileRepository.save(saveFileEntity);

      if (savedFileEntity != null) {
        uploadedFiles.add(FileRspnsDto.toDto(savedFileEntity));
      }
    });

    return uploadedFiles;
  }

  @Transactional(readOnly = true)
  public Optional<FileRspnsDto> getFile(String fileId) {

    Optional<FileRspnsDto> optFileRspnsDto = fileRepository.findByFileIdAndDeleteAt(fileId, "N").map(FileRspnsDto::toDto);

    return optFileRspnsDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public Optional<FileRspnsDto> getFileDownload(String fileId) {

    Optional<FileRspnsDto> optFileRspnsDto = fileRepository.findByFileIdAndDeleteAt(fileId, "N").map(fileEntity -> {
      fileEntity.setDwldCo(fileEntity.getDwldCo() + 1);

      return FileRspnsDto.toDto(fileEntity);
    });

    return optFileRspnsDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public byte[] getImage(FileRspnsDto fileRspnsDto) throws IOException {
    Path imagePath = Paths.get(fileRspnsDto.fileStreCours()).resolve(fileRspnsDto.fileStreNm());
    return Files.readAllBytes(imagePath);
  }
}
