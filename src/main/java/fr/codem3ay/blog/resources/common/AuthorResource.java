package fr.codem3ay.blog.resources.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: add your documentation
 *
 * @author Codem3ay
 *     <p>Created 14 Nov 2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResource {
  private String firstName;
  private String lastName;
}
