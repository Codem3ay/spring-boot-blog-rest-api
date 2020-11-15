package fr.codem3ay.blog.resources.comments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.codem3ay.blog.resources.common.AuthorResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * TODO: add your documentation
 *
 * @author Codem3ay
 *     <p>Created 15 Nov 2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentGetResource {
  private String id;
  private String comment;
  private AuthorResource author;
  private LocalDate date;
  @JsonIgnore private String postId;
}
