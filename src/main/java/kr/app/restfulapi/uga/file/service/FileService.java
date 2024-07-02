package kr.app.restfulapi.uga.file.service;

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
import kr.app.restfulapi.response.error.FieldErrorReason;
import kr.app.restfulapi.response.error.exception.IllegalArgumentException;
import kr.app.restfulapi.uga.common.util.DateUtils;
import kr.app.restfulapi.uga.common.util.FileUtils;
import kr.app.restfulapi.uga.file.dto.FileDataDto;
import kr.app.restfulapi.uga.file.dto.FileDataSetup;
import kr.app.restfulapi.uga.file.entity.FileData;
import kr.app.restfulapi.uga.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

  private final FileRepository fileRepository;

  @Value("${file.upload-dir}")
  private String uploadDir;

  @Transactional
  public List<FileDataDto> storeFiles(List<MultipartFile> files, FileDataSetup fileDataSetup) {

    List<FileDataDto> uploadedFiles = new ArrayList<>();

    AtomicLong atFileSn = new AtomicLong(1L);

    Stream.ofNullable(files).flatMap(List<MultipartFile>::stream).forEach(file -> {

      if (!FileUtils.isAllowedExtension(file)) {
        throw new IllegalArgumentException("파일", file.getOriginalFilename(), FieldErrorReason.FILE_EXTENSION_NOT_ALLOWED);
      }

      String cleanedFilename = StringUtils.cleanPath(file.getOriginalFilename());
      String saveFileName = "FILE_" + DateUtils.getCurrentDateTimeMillisecond();
      String fileStreCours = uploadDir + File.separator + fileDataSetup.fileGroupNm() + File.separator + DateUtils.getCurrentYear() + File.separator
          + DateUtils.getCurrentMonth();
      Path savePath = Paths.get(fileStreCours).resolve(saveFileName);

      try {
        Files.createDirectories(savePath.getParent());
        Files.copy(file.getInputStream(), savePath);
      } catch (IOException e) {
        e.printStackTrace();
      }

      FileData fileData = fileRepository.save(FileData.builder()
          .fileNm(cleanedFilename)
          .fileGroupNm(fileDataSetup.fileGroupNm())
          .refrnId(fileDataSetup.refrnId())
          .fileSectValue(fileDataSetup.fileSectValue())
          .fileSn(atFileSn.getAndIncrement())
          .fileStreNm(saveFileName)
          .fileStreCours(savePath.getParent().toString())
          .fileExtsnNm(FileUtils.getFileExtension(cleanedFilename))
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
    return fileRepository.findById(fileId).map(FileDataDto::toDto);
  }

  public byte[] getImage(FileDataDto fileDataDto) throws IOException {
    Path imagePath = Paths.get(fileDataDto.fileStreCours()).resolve(fileDataDto.fileStreNm());
    return Files.readAllBytes(imagePath);
  }
}
