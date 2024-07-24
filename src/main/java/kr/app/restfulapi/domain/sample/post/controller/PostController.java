package kr.app.restfulapi.domain.sample.post.controller;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import kr.app.restfulapi.domain.sample.post.dto.PostDto;
import kr.app.restfulapi.domain.sample.post.dto.PostSearchDto;
import kr.app.restfulapi.domain.sample.post.service.PostService;
import kr.app.restfulapi.global.response.success.SuccessResponse;
import kr.app.restfulapi.global.response.success.SuccessStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  // TODO 게시글 등록 파일등록도 같이, 수정 시에는 파일 삭제가 됐다면 삭제될 파일들 리스트를 가지고 파일 삭제해야함
  // TODO 게시글 삭제 시 파일들도 삭제
  // TODO 임시저장 만들기

  @GetMapping
  public ResponseEntity<SuccessResponse> getAllPost(@ModelAttribute PostDto postDto, @ModelAttribute PostSearchDto searchDto,
      @PageableDefault(size = 10, sort = "registDt", direction = Sort.Direction.DESC) Pageable pageable) {

    Page<PostDto> postDtoList = postService.getAllPost(postDto, pageable);
    /*
    List<EntityModel<PostDto>> models = postDtoList.stream().map(data -> {
      Link detailLink = null;
      detailLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPostById(data.postId(), userDetails)).withRel("detailLink");
      return EntityModel.of(data, detailLink);
    }).toList();*/

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(postDtoList).build());
  }

  @GetMapping("/{postId}")
  public ResponseEntity<SuccessResponse> getPostById(@PathVariable String postId) {

    Optional<PostDto> optPostDto = postService.getPostById(postId);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(optPostDto).build());

    /*return optPostDto.map(data -> {
       EntityModel<PostDto> model = EntityModel.of(data);
      model.add(linkTo(methodOn(this.getClass()).getAllPost(data, Pageable.unpaged(), userDetails)).withRel("allPosts"));
      return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.OK).data(data).build());
    }).orElseThrow(ResourceNotFoundException::new);*/
  }

  @PostMapping
  public ResponseEntity<SuccessResponse> createPost(@Valid @RequestBody PostDto postDto) {

    PostDto createdPostDto = postService.createPost(postDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder().status(SuccessStatus.CREATED).data(createdPostDto).build());
  }

  @PutMapping("/{postId}")
  public ResponseEntity<SuccessResponse> updatePost(@PathVariable String postId, @Valid @RequestBody PostDto postDto) {

    Optional<PostDto> updatedPostDto = postService.updatePost(postId, postDto);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.UPDATED).data(updatedPostDto).build());
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<SuccessResponse> deletePost(@PathVariable String postId) {

    postService.deletePost(postId);

    return ResponseEntity.ok(SuccessResponse.builder().status(SuccessStatus.DELETED).build());
  }
}
