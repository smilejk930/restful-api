package kr.app.restfulapi.domain.sample.post.file.controller;

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
import kr.app.restfulapi.domain.common.file.dto.FileReqstDto;
import kr.app.restfulapi.domain.common.file.dto.FileRspnsDto;
import kr.app.restfulapi.domain.sample.post.file.entity.PostFile;
import kr.app.restfulapi.domain.sample.post.file.service.PostFileService;
import kr.app.restfulapi.global.response.error.exception.BusinessException;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import kr.app.restfulapi.global.util.CustomFileUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sample/posts/files")
public class PostFileController {

  private final PostFileService fileService;

  @GetMapping
  public ResponseEntity<SuccessResponse> getAllFiles(@ModelAttribute FileReqstDto<PostFile> fileReqstDto) {

    List<FileRspnsDto> fileRspnsDtoList = fileService.getAllFiles(fileReqstDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(fileRspnsDtoList).build());
  }

  @PostMapping
  public ResponseEntity<SuccessResponse> uploadFiles(@RequestParam("files") List<MultipartFile> files,
      @ModelAttribute FileReqstDto<PostFile> fileReqstDto) {
    List<FileRspnsDto> uploadedFiles = fileService.storeFiles(files, fileReqstDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder().status(SuccessStatus.CREATED).data(uploadedFiles).build());
  }

  @GetMapping("/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId, HttpServletRequest request) throws IOException {
    Optional<FileRspnsDto> optFileRspnsDto = fileService.getFileDownload(fileId);

    FileRspnsDto fileRspnsDto = optFileRspnsDto.get();
    Resource resource = new PathResource(fileRspnsDto.fileStreCours() + File.separator + fileRspnsDto.fileStreNm());

    String contentType = request.getServletContext().getMimeType(fileRspnsDto.fileNm());
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    String encodedFileName = URLEncoder.encode(fileRspnsDto.fileNm(), StandardCharsets.UTF_8.toString());
    encodedFileName = encodedFileName.replaceAll("\\+", "%20");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(contentType));
    headers.setContentDispositionFormData("attachment", encodedFileName);

    return ResponseEntity.ok().headers(headers).body(resource);
  }

  @GetMapping("/image/{fileId}")
  public ResponseEntity<Resource> showImage(@PathVariable String fileId) {
    Optional<FileRspnsDto> optFileRspnsDto = fileService.getFile(fileId);

    FileRspnsDto fileRspnsDto = optFileRspnsDto.get();

    try {
      byte[] imageData = fileService.getImage(fileRspnsDto); // FileService에서 이미지 데이터 가져오기

      // 이미지 타입에 따라 적절한 MediaType 설정
      String contentType = CustomFileUtils.determineImageContentType(fileRspnsDto.fileNm());
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
