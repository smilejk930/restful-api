package kr.app.restfulapi.uga.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import kr.app.restfulapi.uga.file.dto.FileDataDto;
import kr.app.restfulapi.uga.file.entity.FileData;
import kr.app.restfulapi.uga.file.repository.FileRepository;
import kr.app.restfulapi.uga.file.util.FileUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

  private final FileRepository fileRepository;

  @Value("${file.upload-dir}")
  private String uploadDir;

  @Transactional
  public List<FileDataDto> storeFiles(List<MultipartFile> files) {

    List<FileDataDto> uploadedFiles = new ArrayList<>();

    Stream.ofNullable(files).flatMap(List<MultipartFile>::stream).forEach(file -> {

      if (!FileUtils.isAllowedExtension(file)) {
        throw new IllegalArgumentException("File extension not allowed: " + file.getOriginalFilename());
      }

      String cleanedFilename = StringUtils.cleanPath(file.getOriginalFilename());
      String uuid = UUID.randomUUID().toString();
      String saveFileName = uuid + "_" + cleanedFilename;
      Path savePath = Paths.get(uploadDir).resolve(saveFileName);

      try {
        Files.copy(file.getInputStream(), savePath);
      } catch (IOException e) {
        e.printStackTrace();
      }

      FileData fileData = fileRepository
          .save(FileData.builder().finm(cleanedFilename).fileStreNm(saveFileName).flpth(savePath.toString()).filesiz(file.getSize()).fileEventn(file.getContentType()).build());

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
    Path imagePath = Paths.get(fileDataDto.flpth());
    return Files.readAllBytes(imagePath);
  }
}
