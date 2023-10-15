package exercise.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class ArticleCreateDto {

    private String name;
    private String body;
    @NonNull
    private Long categoryId;
}
