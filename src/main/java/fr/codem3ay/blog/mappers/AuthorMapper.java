package fr.codem3ay.blog.mappers;

import fr.codem3ay.blog.resources.AuthorPatchBodyResource;
import fr.codem3ay.blog.resources.authors.AuthorGetResource;
import fr.codem3ay.blog.resources.authors.AuthorPostBodyResource;
import fr.codem3ay.blog.resources.authors.AuthorPutBodyResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * TODO: add your documentation
 *
 * @author Codem3ay
 *     <p>Created 15 Nov 2020
 */
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

  @Mapping(target = "id", ignore = true)
  AuthorGetResource authorPostBodyResourceToAuthorGetResource(
      AuthorPostBodyResource authorPostBodyResource);

  @Mapping(target = "id", ignore = true)
  @Mapping(
      target = "firstName",
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
  @Mapping(
      target = "lastName",
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
  AuthorGetResource putBodyResourceToAuthorResource(
      AuthorPutBodyResource authorPutBodyResource,
      @MappingTarget AuthorGetResource existingAuthorGetResource);

  @Mapping(target = "id", ignore = true)
  AuthorGetResource patchBodyResourceToAuthorResource(
      AuthorPatchBodyResource authorPatchBodyResource,
      @MappingTarget AuthorGetResource existingAuthorGetResource);
}
