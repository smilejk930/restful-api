package kr.app.restfulapi.domain.sample.post.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.app.restfulapi.domain.common.file.dto.FileReqstDto;
import kr.app.restfulapi.domain.sample.post.entity.PostFile;
import kr.app.restfulapi.domain.sample.post.service.PostFileService;
import kr.app.restfulapi.global.entity.BaseFileEntity;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sample/posts/files")
public class PostFileController<T extends BaseFileEntity> {

  private final PostFileService fileService;

  @GetMapping
  public ResponseEntity<SuccessResponse> getAllFiles(@ModelAttribute FileReqstDto<PostFile> fileReqstDto) {

    List<PostFile> fileRspnsDtoList = fileService.findAll();

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(fileRspnsDtoList).build());
  }

}
