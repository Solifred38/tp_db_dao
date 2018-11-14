package com.tp.dao;

import java.util.List;

import com.tp.beans.Article;

public interface ArticleDao {
    void ajouter( Article utilisateur );
    List<Article> lister();
}