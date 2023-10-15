package exercise.controller;

import exercise.dto.ArticleCreateDto;
import exercise.dto.ArticleDto;
import exercise.mapper.ArticleMapper;
import exercise.model.Article;
import exercise.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    @Autowired
    private final ArticleRepository articleRepository;

    @Autowired
    private final ArticleMapper mapper;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleRepository.deleteById(id);
    }

    @GetMapping(path="/{id}")
    public ArticleDto show(@PathVariable Long id){
        return mapper.toDto(articleRepository.findByArticleId(id).orElseThrow(()->new RuntimeException("article not found")));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ArticleDto create(@RequestBody ArticleCreateDto dto) {
        return mapper.toDto( articleRepository.save(mapper.toEntity(dto)));
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ArticleCreateDto dto) {
        Article article=articleRepository.findByArticleId(id).orElseThrow(()->new RuntimeException("Not found"));
        mapper.toEntity(dto,article);
        articleRepository.save(article);
    }

}
