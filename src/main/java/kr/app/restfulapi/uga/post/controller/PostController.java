package kr.app.restfulapi.uga.post.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import kr.app.restfulapi.uga.post.dto.PostDto;
import kr.app.restfulapi.uga.post.service.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  @GetMapping
  public ResponseEntity<Page<PostDto>> getAllPost(@ModelAttribute PostDto postDto,
      @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
      @AuthenticationPrincipal UserDetails userDetails) {

    Page<PostDto> posts = postService.getAllPosts(postDto, pageable, userDetails);

    return ResponseEntity.ok(posts);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<PostDto>> getPostById(@PathVariable String id,
      @AuthenticationPrincipal UserDetails userDetails) {

    Optional<PostDto> optionalPostDto = postService.getPostById(id, userDetails);

    return optionalPostDto.map(postDto -> {
      EntityModel<PostDto> model = EntityModel.of(postDto);
      model.add(linkTo(methodOn(this.getClass()).getPostById(id, userDetails)).withSelfRel());
      model.add(
          linkTo(methodOn(this.getClass()).getAllPost(postDto, Pageable.unpaged(), userDetails))
              .withRel("allPosts"));

      return ResponseEntity.ok(model);
    }).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto,
      @AuthenticationPrincipal UserDetails userDetails) {

    PostDto createdPost = postService.createPost(postDto, userDetails);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostDto> updatePost(@PathVariable String id,
      @RequestBody @Valid PostDto postDto, @AuthenticationPrincipal UserDetails userDetails) {

    Optional<PostDto> updatedPost = postService.updatePost(id, postDto, userDetails);

    return updatedPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable String id,
      @AuthenticationPrincipal UserDetails userDetails) {

    boolean deleted = postService.deletePost(id, userDetails);

    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
