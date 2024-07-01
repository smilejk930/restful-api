package kr.app.restfulapi.uga.file.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import kr.app.restfulapi.uga.file.entity.FileData;
import kr.app.restfulapi.uga.file.service.FileService;
import kr.app.restfulapi.uga.file.util.FileUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

  private final FileService fileService;

  @PostMapping("/upload")
  public ResponseEntity<List<FileData>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
    List<FileData> uploadedFiles = fileService.storeFiles(files);
    return ResponseEntity.status(HttpStatus.CREATED).body(uploadedFiles);
  }

  @GetMapping("/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId, HttpServletRequest request) throws IOException {
    Optional<FileData> optFileData = fileService.getFile(fileId);

    if (optFileData.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    FileData fileData = optFileData.get();
    Resource resource = new PathResource(fileData.getFlpth());

    String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    String encodedFileName = URLEncoder.encode(fileData.getFinm(), StandardCharsets.UTF_8.toString());
    encodedFileName = encodedFileName.replaceAll("\\+", "%20");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(contentType));
    headers.setContentDispositionFormData("attachment", encodedFileName);

    return ResponseEntity.ok().headers(headers).body(resource);
  }

  @GetMapping("/image/{fileId}")
  public ResponseEntity<Resource> showImage(@PathVariable String fileId) {
    Optional<FileData> optFileData = fileService.getFile(fileId);

    if (optFileData.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    FileData fileData = optFileData.get();
    try {
      byte[] imageData = fileService.getImage(fileData); // FileService에서 이미지 데이터 가져오기

      // 이미지 타입에 따라 적절한 MediaType 설정
      String contentType = FileUtils.determineImageContentType(fileData.getFinm());
      if (contentType == null) {
        contentType = MediaType.IMAGE_JPEG_VALUE; // 기본적으로 JPEG로 설정
      }

      // 이미지 데이터를 ByteArrayResource로 변환하여 반환
      ByteArrayResource resource = new ByteArrayResource(imageData);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.parseMediaType(contentType));

      return ResponseEntity.ok().headers(headers).body(resource);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
