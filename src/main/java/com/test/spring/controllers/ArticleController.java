package com.test.spring.controllers;

import com.test.spring.data.Article;
import com.test.spring.responses.ArticleResponse;
import com.test.spring.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping("/getAll")
    public ResponseEntity<ArticleResponse> getAll() {
        return ResponseEntity.ok(articleService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<ArticleResponse> add(@RequestBody Article article) {
        return ResponseEntity.ok(articleService.add(article));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ArticleResponse> get(@PathVariable Integer id) {
        return ResponseEntity.ok(articleService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ArticleResponse> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(articleService.delete(id));
    }
}
