package fr.codem3ay.blog.controllers;

import fr.codem3ay.blog.mappers.CommentMapper;
import fr.codem3ay.blog.resources.common.AuthorResource;
import fr.codem3ay.blog.resources.comments.CommentPostBodyResource;
import fr.codem3ay.blog.resources.comments.CommentGetResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 *     <p>Created 15 Nov 2020
 */
@RestController
@RequiredArgsConstructor
public class CommentController {

  private List<CommentGetResource> commentGetResources;
  private final CommentMapper commentMapper;

  @PostConstruct
  private void initializeData() {
    commentGetResources = new ArrayList<>();
    AuthorResource authorResource = new AuthorResource("Ricardo", "LOPEZ");
    AuthorResource anotherAuthorResource = new AuthorResource("James", "RICHARDSON");
    commentGetResources.add(
        new CommentGetResource("1", "This is a nice comment", authorResource, LocalDate.now(), "1"));
    commentGetResources.add(
        new CommentGetResource("2", "This is a rude comment", authorResource, LocalDate.now(), "2"));
    commentGetResources.add(
        new CommentGetResource("3", "This is a comment", authorResource, LocalDate.now(), "3"));

    commentGetResources.add(
        new CommentGetResource(
            "4", "This is a nice comment", anotherAuthorResource, LocalDate.now(), "1"));
    commentGetResources.add(
        new CommentGetResource(
            "5", "This is a rude comment", anotherAuthorResource, LocalDate.now(), "2"));
    commentGetResources.add(
        new CommentGetResource("6", "This is a comment", anotherAuthorResource, LocalDate.now(), "3"));
  }

  @GetMapping("/posts/{postId}/comments")
  public ResponseEntity<List<CommentGetResource>> getAllCommentsByPostId(@PathVariable String postId) {
    return ResponseEntity.ok(
        commentGetResources.stream()
            .filter(commentGetResource -> commentGetResource.getPostId().equalsIgnoreCase(postId))
            .collect(Collectors.toList()));
  }

  @PostMapping("/posts/{postId}/comments")
  public ResponseEntity<CommentGetResource> postComment(
      @PathVariable String postId, @RequestBody CommentPostBodyResource commentPostBodyResource) {
    CommentGetResource commentGetResource =
        commentMapper.commentpostBodyResourceToCommentResource(commentPostBodyResource);
    commentGetResource.setPostId(postId);
    commentGetResource.setId(UUID.randomUUID().toString());
    commentGetResources.add(commentGetResource);
    return new ResponseEntity<>(commentGetResource, HttpStatus.CREATED);
  }
}
