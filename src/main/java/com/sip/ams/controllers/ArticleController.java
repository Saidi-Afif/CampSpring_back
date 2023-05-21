package com.sip.ams.controllers;
import com.sip.ams.entities.Provider;
import com.sip.ams.services.IArticleService;
import com.sip.ams.services.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sip.ams.entities.Article;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;
import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping({"/articles"})
public class ArticleController {

    private final ArticleRepository articleRepository;

    private final ProviderRepository providerRepository;

    private final IArticleService articleService;

    public ArticleController(ArticleRepository articleRepository, ProviderRepository providerRepository,IArticleService articleService) {
        this.articleRepository = articleRepository;
        this.providerRepository = providerRepository;
        this.articleService = articleService;
    }
	/*@Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProviderRepository providerRepository;*/
//    @Autowired



    @GetMapping("/list")
    public List<Article> getAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    @PostMapping("/add/{providerId}")
    Article createArticle(@PathVariable (value = "providerId")Long providerId,
                          @Valid @RequestBody Article article) {
        return articleService.saveArticle(article,providerId);
    }

    @PutMapping("/update/{providerId}/{articleId}")
    public Article updateArticle( @PathVariable (value = "providerId") Long providerId,@PathVariable (value = "articleId")  Long articleId,@Valid @RequestBody Article articleRequest) {

        /*
        if(!providerRepository.existsById(providerId)) {
            throw new IllegalArgumentException("ProviderId " + providerId + " not found");
        }

        return articleRepository.findById(articleId).map(article -> {
            article.setPrice(articleRequest.getPrice());
            article.setLabel(articleRequest.getLabel());
            article.setPicture(articleRequest.getPicture());
            return articleRepository.save(article);
        }).orElseThrow(() -> new IllegalArgumentException("ArticleId " + articleId + "not found"));

         */
        return articleService.updateArticle(providerId,articleId,articleRequest);
    }

    @DeleteMapping("/delete/{articleId}")
    public Article deleteArticle(@PathVariable (value = "articleId") Long articleId) {
        return articleService.deleteArticle(articleId);
    }

    @GetMapping("/{articleId}")
    public Article getOneArticleById(@PathVariable Long articleId) {

        return articleService.getOneArticleById(articleId);
    }
}
