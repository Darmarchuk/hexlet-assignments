package exercise.dto;

import java.util.List;

import exercise.model.Comment;
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
 String title;
 String body;
 List<CommentDTO> comments;
}