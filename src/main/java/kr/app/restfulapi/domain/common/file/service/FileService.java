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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import kr.app.restfulapi.global.response.error.FieldErrorReason;
import kr.app.restfulapi.global.response.error.exception.BusinessException;
import kr.app.restfulapi.global.response.error.exception.FieldNullPointException;
import kr.app.restfulapi.global.response.error.exception.IllegalArgumentException;
import kr.app.restfulapi.global.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.global.util.CustomDateUtils;
import kr.app.restfulapi.global.util.CustomFileUtils;
import kr.app.restfulapi.global.util.CustomObjectUtils;
import kr.app.restfulapi.domain.common.file.dto.FileDataDto;
import kr.app.restfulapi.domain.common.file.dto.FileDataInit;
import kr.app.restfulapi.domain.common.file.entity.FileData;
import kr.app.restfulapi.domain.common.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

  private final FileRepository fileRepository;

  @Value("${file.upload-dir}")
  private String uploadDir;

  @Transactional(readOnly = true)
  public List<FileDataDto> getAllFiles(FileDataInit fileDataInit) {

    if (fileDataInit.isAnyNullOfRequiredlField()) {
      log.error("FileDataInit의 필요 속성값들이 NULL");
      throw new FieldNullPointException();
    }

    List<FileData> fileDataList = fileRepository.findAllWithCriteria(fileDataInit.toEntity());

    return fileDataList.stream().map(FileDataDto::toDto).toList();
  }

  @Transactional
  public List<FileDataDto> storeFiles(List<MultipartFile> files, FileDataInit fileDataInit) {

    if (CustomObjectUtils.isAnyNullOfFields(fileDataInit)) {
      log.error("FileDataInit의 속성값들 중 NULL 존재");
      throw new FieldNullPointException();
    }

    List<FileDataDto> uploadedFiles = new ArrayList<>();

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
      String fileStreCours = uploadDir + File.separator + fileDataInit.getFileGroupNm() + File.separator + CustomDateUtils.getCurrentYear()
          + File.separator + CustomDateUtils.getCurrentMonth();
      Path savePath = Paths.get(fileStreCours).resolve(saveFileName);

      try {
        Files.createDirectories(savePath.getParent());
        Files.copy(file.getInputStream(), savePath);
      } catch (IOException e) {
        throw new BusinessException(e);
      }

      FileData fileData = fileRepository.save(FileData.builder()
          .fileNm(cleanedFilename)
          .fileGroupNm(fileDataInit.getFileGroupNm())
          .refrnId(fileDataInit.getRefrnId())
          .fileSectValue(fileDataInit.getFileSectValue())
          .fileSn(atFileSn.getAndIncrement())
          .fileStreNm(saveFileName)
          .fileStreCours(savePath.getParent().toString())
          .fileExtsnNm(CustomFileUtils.getFileExtension(cleanedFilename))
          .fileSize(file.getSize())
          .build());

      if (fileData != null) {
        uploadedFiles.add(FileDataDto.toDto(fileData));
      }
    });

    return uploadedFiles;
  }

  @Transactional(readOnly = true)
  public Optional<FileDataDto> getFile(String fileId) {

    Optional<FileDataDto> optFileDataDto = fileRepository.findByFileIdAndDeleteAt(fileId, "N").map(FileDataDto::toDto);

    return optFileDataDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional
  public Optional<FileDataDto> getFileDownload(String fileId) {

    Optional<FileDataDto> optFileDataDto = fileRepository.findByFileIdAndDeleteAt(fileId, "N").map(fileData -> {
      fileData.setDwldCo(fileData.getDwldCo() + 1);

      return FileDataDto.toDto(fileData);
    });

    return optFileDataDto.map(Optional::of).orElseThrow(ResourceNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public byte[] getImage(FileDataDto fileDataDto) throws IOException {
    Path imagePath = Paths.get(fileDataDto.fileStreCours()).resolve(fileDataDto.fileStreNm());
    return Files.readAllBytes(imagePath);
  }
}
