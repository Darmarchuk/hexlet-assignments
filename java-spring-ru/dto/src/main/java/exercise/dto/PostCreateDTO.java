package exercise.dto;


import java.util.List;

import exercise.model.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDTO {
    @NotNull
    private Long authorId;
    private JsonNullable<String> title;
    private JsonNullable<String> body;
}