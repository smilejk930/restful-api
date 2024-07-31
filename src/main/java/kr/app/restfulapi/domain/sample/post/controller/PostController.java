package kr.app.restfulapi.domain.sample.post.controller;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.app.restfulapi.domain.sample.post.dto.PostReqstDto;
import kr.app.restfulapi.domain.sample.post.dto.PostRspnsDto;
import kr.app.restfulapi.domain.sample.post.dto.PostSrchDto;
import kr.app.restfulapi.domain.sample.post.service.PostService;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import kr.app.restfulapi.global.validation.ValidationGroups.FinalSubmit;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sample/posts")
public class PostController {

  private final PostService postService;

  // TODO 게시글 등록 파일등록도 같이, 수정 시에는 파일 삭제가 됐다면 삭제될 파일들 리스트를 가지고 파일 삭제해야함
  // TODO 게시글 삭제 시 파일들도 삭제

  @GetMapping
  public ResponseEntity<SuccessResponse> getAllPost(@ModelAttribute PostSrchDto srchDto,
      @PageableDefault(size = 10, sort = "regDt", direction = Sort.Direction.DESC) Pageable pageable) {

    Page<PostRspnsDto> postRspnsDtoList = postService.getAllPost(srchDto, pageable);
    /*
    List<EntityModel<PostDto>> models = postDtoList.stream().map(data -> {
      Link detailLink = null;
      detailLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPostById(data.postTsid(), userDetails)).withRel("detailLink");
      return EntityModel.of(data, detailLink);
    }).toList();*/

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(postRspnsDtoList).build());
  }

  @GetMapping("/{postTsid}")
  public ResponseEntity<SuccessResponse> getPostById(@PathVariable String postTsid) {

    Optional<PostRspnsDto> optPostRspnsDto = postService.getPostById(postTsid);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(optPostRspnsDto).build());

    /*return optPostDto.map(data -> {
       EntityModel<PostDto> model = EntityModel.of(data);
      model.add(linkTo(methodOn(this.getClass()).getAllPost(data, Pageable.unpaged(), userDetails)).withRel("allPosts"));
      return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(data).build());
    }).orElseThrow(ResourceNotFoundException::new);*/
  }

  @PostMapping
  public ResponseEntity<SuccessResponse> createPost(@Validated @RequestBody PostReqstDto postReqstDto) {

    return processCreatPost(postReqstDto, "N");
  }

  @PostMapping("/submit")
  public ResponseEntity<SuccessResponse> submitCreatePost(@Validated(FinalSubmit.class) @RequestBody PostReqstDto postReqstDto) {

    return processCreatPost(postReqstDto, "Y");
  }

  private ResponseEntity<SuccessResponse> processCreatPost(PostReqstDto postReqstDto, String sbmsnYn) {

    PostRspnsDto postRspnsDto = postService.createPost(postReqstDto, sbmsnYn);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.CREATED).data(postRspnsDto).build());
  }

  @PutMapping("/{postTsid}")
  public ResponseEntity<SuccessResponse> updatePost(@PathVariable String postTsid, @Validated @RequestBody PostReqstDto postReqstDto) {

    return processUpdatePost(postTsid, postReqstDto, "N");
  }

  @PutMapping("/submit/{postTsid}")
  public ResponseEntity<SuccessResponse> submitUpdatePost(@PathVariable String postTsid,
      @Validated(FinalSubmit.class) @RequestBody PostReqstDto postReqstDto) {

    return processUpdatePost(postTsid, postReqstDto, "Y");
  }

  private ResponseEntity<SuccessResponse> processUpdatePost(String postTsid, PostReqstDto postReqstDto, String sbmsnYn) {

    Optional<PostRspnsDto> optPostRspnsDto = postService.updatePost(postTsid, postReqstDto, sbmsnYn);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.UPDATED).data(optPostRspnsDto).build());
  }

  @DeleteMapping("/{postTsid}")
  public ResponseEntity<SuccessResponse> deletePost(@PathVariable String postTsid) {

    postService.deletePost(postTsid);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.DELETED).build());
  }
}
