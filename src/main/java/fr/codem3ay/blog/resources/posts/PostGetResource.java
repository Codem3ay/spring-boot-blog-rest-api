package fr.codem3ay.blog.resources.posts;

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
 *     <p>Created 14 Nov 2020
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostGetResource {
  private String id;
  private String title;
  private String content;
  private LocalDate date;
  private AuthorResource author;
}
