package com.test.spring.services;

import com.test.spring.data.Article;
import com.test.spring.data.ArticleRepository;
import com.test.spring.responses.ArticleResponse;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final EntityManager entityManager;

    public ArticleResponse getAll() {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().toString();
        String response = null;
        
        if(role.equals("ADMIN")) {
            response = articleRepository.findAll().toString();
        } else if(role.equals("USER")) {
            Session session = entityManager.unwrap(Session.class);
            Filter filter = session.enableFilter("deletedArticleFilter");
            filter.setParameter("isDeleted", false);

            response = articleRepository.findAll().toString();

            session.disableFilter("deletedArticleFilter");
        }

        return new ArticleResponse(response);
    }

    public ArticleResponse add(Article article) {
        articleRepository.save(article);
        return new ArticleResponse("Article added successfully");
    }

    public ArticleResponse get(Integer id) {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().toString();
        String response = null;

        if(role.equals("ADMIN")) {
            response = articleRepository.findById(id).toString();
        } else if(role.equals("USER")) {
            Session session = entityManager.unwrap(Session.class);
            Filter filter = session.enableFilter("deletedArticleFilter");
            filter.setParameter("isDeleted", false);

            response = articleRepository.findById(id).toString();

            session.disableFilter("deletedArticleFilter");
        }

        return new ArticleResponse(response);
    }

    public ArticleResponse delete(Integer id) {
        articleRepository.deleteById(id);
        return new ArticleResponse("Article deleted successfully");
    }

}
