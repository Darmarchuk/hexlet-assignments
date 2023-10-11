package exercise.dto;

import java.util.List;

import exercise.model.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

 Long id;
 @NotEmpty
 @Size(min = 2)
 String title;
 @Size(min = 7)
 String body;
 List<CommentDTO> comments;
}