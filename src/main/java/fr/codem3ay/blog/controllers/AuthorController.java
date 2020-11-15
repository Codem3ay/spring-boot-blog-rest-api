package fr.codem3ay.blog.controllers;

import fr.codem3ay.blog.exceptions.ResourceNotFoundException;
import fr.codem3ay.blog.mappers.AuthorMapper;
import fr.codem3ay.blog.resources.AuthorPatchBodyResource;
import fr.codem3ay.blog.resources.authors.AuthorGetResource;
import fr.codem3ay.blog.resources.authors.AuthorPostBodyResource;
import fr.codem3ay.blog.resources.authors.AuthorPutBodyResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * TODO: add your documentation
 *
 * @author Codem3ay
 *     <p>Created 15 Nov 2020
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthorController {

  private List<AuthorGetResource> authorGetResources;

  private final AuthorMapper authorMapper;

  @PostConstruct
  private void initializeData() {
    authorGetResources = new ArrayList<>();
    authorGetResources.add(new AuthorGetResource("1", "Jhon", "DOE"));
    authorGetResources.add(new AuthorGetResource("2", "Jane", "DOE"));
    authorGetResources.add(new AuthorGetResource("3", "Pablo", "LOPEZ"));
  }

  @GetMapping("/authors")
  public ResponseEntity<List<AuthorGetResource>> getAllAuthors() {
    return ResponseEntity.ok(authorGetResources);
  }

  @GetMapping("/authors/{authorId}")
  public ResponseEntity<AuthorGetResource> getAuthorById(@PathVariable String authorId) {
    return ResponseEntity.ok(
        authorGetResources.stream()
            .filter(authorGetResource -> authorGetResource.getId().equalsIgnoreCase(authorId))
            .findAny()
            .orElseThrow(ResourceNotFoundException::new));
  }

  @PostMapping("/authors")
  public ResponseEntity<AuthorGetResource> postAuthor(
      @RequestBody AuthorPostBodyResource authorPostBodyResource) {
    AuthorGetResource authorGetResource =
        authorMapper.authorPostBodyResourceToAuthorGetResource(authorPostBodyResource);
    authorGetResource.setId(UUID.randomUUID().toString());
    authorGetResources.add(authorGetResource);
    return new ResponseEntity<>(authorGetResource, HttpStatus.CREATED);
  }

  @PutMapping("/authors/{authorId}")
  public ResponseEntity<AuthorGetResource> putAuthorById(
      @PathVariable String authorId, @RequestBody AuthorPutBodyResource authorPutBodyResource) {
    AuthorGetResource existingAuthorGetResource =
        authorGetResources.stream()
            .filter(authorGetResource -> authorGetResource.getId().equalsIgnoreCase(authorId))
            .findAny()
            .orElseThrow(ResourceNotFoundException::new);
    authorGetResources.remove(existingAuthorGetResource);
    AuthorGetResource authorGetResource =
        authorMapper.putBodyResourceToAuthorResource(
            authorPutBodyResource, existingAuthorGetResource);
    authorGetResources.add(authorGetResource);
    return ResponseEntity.ok(authorGetResource);
  }

  @PutMapping("/authors/{authorId}")
  public ResponseEntity<AuthorGetResource> patchAuthorById(
      @PathVariable String authorId, @RequestBody AuthorPatchBodyResource authorPatchBodyResource) {
    AuthorGetResource existingAuthorGetResource =
        authorGetResources.stream()
            .filter(authorGetResource -> authorGetResource.getId().equalsIgnoreCase(authorId))
            .findAny()
            .orElseThrow(ResourceNotFoundException::new);
    authorGetResources.remove(existingAuthorGetResource);
    AuthorGetResource authorGetResource =
        authorMapper.patchBodyResourceToAuthorResource(
            authorPatchBodyResource, existingAuthorGetResource);
    authorGetResources.add(authorGetResource);
    return ResponseEntity.ok(authorGetResource);
  }

  @DeleteMapping("/authors/{authorId}")
  public ResponseEntity<Void> deleteAuthorById(@PathVariable String authorId) {
    AuthorGetResource existingAuthorGetResource =
        authorGetResources.stream()
            .filter(authorGetResource -> authorGetResource.getId().equalsIgnoreCase(authorId))
            .findAny()
            .orElseThrow(ResourceNotFoundException::new);
    authorGetResources.remove(existingAuthorGetResource);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
