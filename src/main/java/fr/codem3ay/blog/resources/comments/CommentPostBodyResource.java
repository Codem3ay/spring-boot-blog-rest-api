package fr.codem3ay.blog.resources.comments;

import fr.codem3ay.blog.resources.common.AuthorResource;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * TODO: add your documentation
 *
 * @author Codem3ay
 *     <p>Created 15 Nov 2020
 */
@Data
@ToString
public class CommentPostBodyResource {
  private String comment;
  private LocalDate date;
  private AuthorResource author;
}
