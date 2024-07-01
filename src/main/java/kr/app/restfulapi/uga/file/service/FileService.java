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
  public List<FileData> storeFiles(List<MultipartFile> files) {

    List<FileData> uploadedFiles = new ArrayList<>();

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

      uploadedFiles.add(fileRepository
          .save(FileData.builder().finm(cleanedFilename).fileStreNm(saveFileName).flpth(savePath.toString()).filesiz(file.getSize()).fileEventn(file.getContentType()).build()));
    });

    return uploadedFiles;
  }

  @Transactional(readOnly = true)
  public Optional<FileData> getFile(String fileId) {
    return fileRepository.findById(fileId);
  }

  public byte[] getImage(FileData fileData) throws IOException {
    Path imagePath = Paths.get(fileData.getFlpth());
    return Files.readAllBytes(imagePath);
  }
}
