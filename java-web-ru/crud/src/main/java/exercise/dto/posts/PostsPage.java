package exercise.dto.posts;

import java.util.List;

import exercise.model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PostsPage {
    List<Post> posts;
    private Integer pageNum;
}



