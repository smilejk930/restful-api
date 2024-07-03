package kr.app.restfulapi.uga.file.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import kr.app.restfulapi.response.error.exception.BusinessException;
import kr.app.restfulapi.response.error.exception.ResourceNotFoundException;
import kr.app.restfulapi.response.success.SuccessResponse;
import kr.app.restfulapi.response.success.SuccessStatus;
import kr.app.restfulapi.uga.common.util.CustomFileUtils;
import kr.app.restfulapi.uga.file.dto.FileDataDto;
import kr.app.restfulapi.uga.file.dto.FileDataInit;
import kr.app.restfulapi.uga.file.service.FileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

  private final FileService fileService;

  @GetMapping
  public ResponseEntity<SuccessResponse> getAllFiles(@ModelAttribute FileDataInit fileDataInit) {

    List<FileDataDto> fileDataDtoList = fileService.getAllFiles(fileDataInit);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(fileDataDtoList).build());
  }

  @PostMapping
  public ResponseEntity<SuccessResponse> uploadFiles(@RequestParam("files") List<MultipartFile> files, @ModelAttribute FileDataInit fileDataInit) {
    List<FileDataDto> uploadedFiles = fileService.storeFiles(files, fileDataInit);
    return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder().status(SuccessStatus.CREATED).data(uploadedFiles).build());
  }

  @GetMapping("/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId, HttpServletRequest request) throws IOException {
    Optional<FileDataDto> optFileDataDto = fileService.getFileDownload(fileId);

    if (optFileDataDto.isEmpty()) {
      throw new ResourceNotFoundException();
    }

    FileDataDto fileDataDto = optFileDataDto.get();
    Resource resource = new PathResource(fileDataDto.fileStreCours() + File.separator + fileDataDto.fileStreNm());

    String contentType = request.getServletContext().getMimeType(fileDataDto.fileNm());
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    String encodedFileName = URLEncoder.encode(fileDataDto.fileNm(), StandardCharsets.UTF_8.toString());
    encodedFileName = encodedFileName.replaceAll("\\+", "%20");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(contentType));
    headers.setContentDispositionFormData("attachment", encodedFileName);

    return ResponseEntity.ok().headers(headers).body(resource);
  }

  @GetMapping("/image/{fileId}")
  public ResponseEntity<Resource> showImage(@PathVariable String fileId) {
    Optional<FileDataDto> optFileDataDto = fileService.getFile(fileId);

    if (optFileDataDto.isEmpty()) {
      throw new ResourceNotFoundException();
    }

    FileDataDto fileDataDto = optFileDataDto.get();
    try {
      byte[] imageData = fileService.getImage(fileDataDto); // FileService에서 이미지 데이터 가져오기

      // 이미지 타입에 따라 적절한 MediaType 설정
      String contentType = CustomFileUtils.determineImageContentType(fileDataDto.fileNm());
      if (contentType == null) {
        contentType = MediaType.IMAGE_JPEG_VALUE; // 기본적으로 JPEG로 설정
      }

      // 이미지 데이터를 ByteArrayResource로 변환하여 반환
      ByteArrayResource resource = new ByteArrayResource(imageData);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.parseMediaType(contentType));

      return ResponseEntity.ok().headers(headers).body(resource);
    } catch (IOException e) {
      throw new BusinessException(e);
    }
  }
}
