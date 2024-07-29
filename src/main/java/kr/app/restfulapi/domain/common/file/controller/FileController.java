package kr.app.restfulapi.domain.common.file.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common/files")
public class FileController {
  /*
  private final FileService fileService;
  
  @GetMapping("/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId, HttpServletRequest request) throws IOException {
    Optional<FileRspnsDto> optFileDataDto = fileService.getFileDownload(fileId);
  
    FileRspnsDto fileDataDto = optFileDataDto.get();
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
    Optional<FileRspnsDto> optFileDataDto = fileService.getFile(fileId);
  
    FileRspnsDto fileDataDto = optFileDataDto.get();
  
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
  }*/
}
