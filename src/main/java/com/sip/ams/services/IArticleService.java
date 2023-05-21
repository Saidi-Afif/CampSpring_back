package com.sip.ams.services;

import com.sip.ams.entities.Article;


import java.util.List;

public interface IArticleService {
    List<Article> getAllArticles();


    Article saveArticle(Article article , long providerId);

    Article getOneArticleById(long articleId);

    Article deleteArticle(long articleId);

    Article updateArticle( long idArticle,long providerId,Article article);

}
