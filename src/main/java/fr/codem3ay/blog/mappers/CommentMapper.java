package fr.codem3ay.blog.mappers;

import fr.codem3ay.blog.resources.comments.CommentPostBodyResource;
import fr.codem3ay.blog.resources.comments.CommentGetResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TODO: add your documentation
 *
 * @author Codem3ay
 *     <p>Created 15 Nov 2020
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "postId", ignore = true)
    CommentGetResource commentpostBodyResourceToCommentResource(CommentPostBodyResource commentPostBodyResource);

}
