package com.tp.dao;

import java.util.List;

import com.tp.beans.Article;

public interface ArticleDao {
    void ajouter( Article article, String nomMagasin );
    List<Article> lister();
    List<Article> filtrer(String magasin);
    void supprimer(Article article);
}