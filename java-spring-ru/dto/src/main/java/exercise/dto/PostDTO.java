package exercise.dto;

import java.util.List;

import exercise.model.Author;
import exercise.model.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

 Long id;
 @NotEmpty
 @Size(min = 2)
 private JsonNullable<String> title;

 @Size(min = 7)
 JsonNullable<String> body;
 private List<CommentDTO> comments;

 @NotNull
 private Long authorId;
}