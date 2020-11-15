package fr.codem3ay.blog.resources.authors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO: add your documentation
 *
 * @author Codem3ay
 *     <p>Created 15 Nov 2020
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorGetResource {
  private String id;
  private String firstName;
  private String lastName;
}
