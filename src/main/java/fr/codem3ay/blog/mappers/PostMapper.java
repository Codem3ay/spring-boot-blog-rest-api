package fr.codem3ay.blog.mappers;

import fr.codem3ay.blog.resources.posts.*;
import fr.codem3ay.blog.resources.posts.PostPostBodyResource;
import fr.codem3ay.blog.resources.posts.PostPutBodyResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * TODO: add your documentation
 *
 * @author Codem3ay
 *     <p>Created 14 Nov 2020
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {

  @Mapping(target = "id", ignore = true)
  PostGetResource postBodyResourceToPostResource(PostPostBodyResource postPostBodyResource);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "author", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
  @Mapping(target = "content", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
  @Mapping(target = "date", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
  @Mapping(target = "title", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
  PostGetResource putBodyResourceToPostResource(
          PostPutBodyResource putBodyResource, @MappingTarget PostGetResource existingPostGetResource);

  @Mapping(target = "id", ignore = true)
  PostGetResource patchBodyResourceToPostResource(PostPostPatchBodyResource postPatchBodyResource, @MappingTarget PostGetResource existingPostGetResource);
}
