package fr.codem3ay.blog.controllers;

import fr.codem3ay.blog.exceptions.ResourceNotFoundException;
import fr.codem3ay.blog.mappers.PostMapper;
import fr.codem3ay.blog.resources.common.AuthorResource;
import fr.codem3ay.blog.resources.posts.*;
import fr.codem3ay.blog.resources.posts.PostPostBodyResource;
import fr.codem3ay.blog.resources.posts.PostPutBodyResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * TODO: add your documentation
 *
 * @author Codem3ay
 *     <p>Created 14 Nov 2020
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

  private List<PostGetResource> postGetResources;

  private final PostMapper postMapper;

  @PostConstruct
  private void initializeData() {
    AuthorResource authorResource = new AuthorResource("Jhon", "DOE");
    postGetResources = new ArrayList<>();
    postGetResources.add(
        new PostGetResource(
            "1", "Spring boot pour les nuls", "Some content", LocalDate.now(), authorResource));
    postGetResources.add(
        new PostGetResource(
            "2", "Angular tour hero", "Some content", LocalDate.now(), authorResource));
    postGetResources.add(
        new PostGetResource("3", "React from 0", "Some content", LocalDate.now(), authorResource));
  }

  @GetMapping("/posts")
  public ResponseEntity<List<PostGetResource>> getAllPosts(
      @RequestParam(required = false) String search) {
    if(ObjectUtils.isEmpty(search)){
      return ResponseEntity.ok(postGetResources);
    }else{
      return ResponseEntity.ok(postGetResources.stream().filter(postGetResource -> postGetResource.getTitle().contains(search)).collect(Collectors.toList()));
    }
  }

  @GetMapping("/posts/{postId}")
  public ResponseEntity<PostGetResource> getPostById(@PathVariable String postId) {
    log.info("Your post ID: {}", postId);
    return ResponseEntity.ok(
        postGetResources.stream()
            .filter(postGetResource -> postGetResource.getId().equalsIgnoreCase(postId))
            .findAny()
            .orElseThrow(ResourceNotFoundException::new));
  }

  @PostMapping("/posts")
  public ResponseEntity<PostGetResource> postPost(@RequestBody PostPostBodyResource postPostBodyResource) {
    log.info("your post body is {}", postPostBodyResource);
    PostGetResource postGetResource = postMapper.postBodyResourceToPostResource(postPostBodyResource);
    postGetResource.setId(UUID.randomUUID().toString());
    postGetResources.add(postGetResource);
    return new ResponseEntity<>(postGetResource, HttpStatus.CREATED);
  }

  @PutMapping("/posts/{postId}")
  public ResponseEntity<PostGetResource> putPost(
          @RequestBody PostPutBodyResource putBodyResource, @PathVariable String postId) {
    PostGetResource existingPostGetResource =
        postGetResources.stream()
            .filter(postGetResource -> postGetResource.getId().equalsIgnoreCase(postId))
            .findAny()
            .orElseThrow(ResourceNotFoundException::new);
    postGetResources.remove(existingPostGetResource);
    PostGetResource postGetResource =
        postMapper.putBodyResourceToPostResource(putBodyResource, existingPostGetResource);
    postGetResources.add(postGetResource);
    return ResponseEntity.ok(postGetResource);
  }

  @PatchMapping("/posts/{postId}")
  public ResponseEntity<PostGetResource> patchPost(
          @RequestBody PostPostPatchBodyResource postPatchBodyResource, @PathVariable String postId) {
    PostGetResource existingPostGetResource =
        postGetResources.stream()
            .filter(postGetResource -> postGetResource.getId().equalsIgnoreCase(postId))
            .findAny()
            .orElseThrow(ResourceNotFoundException::new);
    postGetResources.remove(existingPostGetResource);
    PostGetResource postGetResource =
        postMapper.patchBodyResourceToPostResource(postPatchBodyResource, existingPostGetResource);
    postGetResources.add(postGetResource);
    return ResponseEntity.ok(postGetResource);
  }

  @DeleteMapping("/posts/{postId}")
  public ResponseEntity<Void> deletePostById(@PathVariable String postId) {
    PostGetResource existingPostGetResource =
        postGetResources.stream()
            .filter(postGetResource -> postGetResource.getId().equalsIgnoreCase(postId))
            .findAny()
            .orElseThrow(ResourceNotFoundException::new);
    postGetResources.remove(existingPostGetResource);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
