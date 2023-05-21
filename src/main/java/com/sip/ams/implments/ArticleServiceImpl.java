package com.sip.ams.implments;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;
import com.sip.ams.services.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ProviderRepository providerRepository;

    @Override
    public List<Article> getAllArticles() {

        return articleRepository.findAll();
    }

    @Override
    public Article saveArticle(Article article , long providerId) {
        return providerRepository.findById(providerId).map(provider -> {
            article.setProvider(provider);
            return articleRepository.save(article);
        }).orElseThrow(() -> new IllegalArgumentException("ProviderId " + providerId + " not found"));
    }



    @Override
    public Article getOneArticleById(long idArticle) {
        return articleRepository.findById(idArticle).orElseThrow(() -> new IllegalArgumentException("ArticleId " + idArticle + " not found"));
    }

    @Override
    public Article deleteArticle(long articleId) {
        Article temp=null;
        Optional<Article> opt = articleRepository.findById(articleId);
        if(opt.isPresent())
        {
            temp = opt.get();
            articleRepository.delete(temp);
            return temp;
        }
        if(temp == null) throw new IllegalArgumentException("Article with id = "+ articleId+"not Found");
        return temp;
    }

    @Override
    public Article updateArticle( long providerId, long articleId, Article article) {

        Provider provider=null;

        if(!providerRepository.existsById(providerId)) {
            throw new IllegalArgumentException("ProviderId " + providerId + " not found");
        }
        else {
            Optional<Provider> opt1 = providerRepository.findById(providerId);
            provider  = opt1.get();
        }

        Article temp = null;
        Optional<Article> opt = articleRepository.findById(articleId);
        if ((opt.isPresent()) || (!articleRepository.existsById(articleId)))
        {
            temp = opt.get();
            temp.setLabel(article.getLabel());
            temp.setPrice(article.getPrice());
            temp.setPicture(article.getPicture());
            temp.setProvider(provider);
            articleRepository.save(temp);
            return temp;
        }
        if(temp == null) throw new IllegalArgumentException("Article with id = "+ articleId +" not Found");
        return temp;



/*
        Article temp = null;
        Optional<Article> opt = articleRepository.findById(article.getId());
        if(opt.isPresent())
        {
            temp = opt.get();
            temp.setLabel(article.getLabel());
            temp.setPrice(article.getPrice());
            temp.setPicture(article.getPicture());
           // temp.setProvider(article.getProvider());
            articleRepository.save(temp);
            return temp;
        }
        if(temp == null) throw new IllegalArgumentException("Article with id = "+ articleId +"not Found");
        return temp;

 */
    }
}
