package com.tp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tp.beans.Article;

public class ArticleDaoImpl implements ArticleDao {
    private DaoFactory daoFactory;

    ArticleDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Article article) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO articles(nom, description, prix) VALUES(?, ?,?);");
            preparedStatement.setString(1, article.getNom());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setFloat(3, article.getPrix());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Article> lister() {
System.out.println("Entree dans Lister");
        List<Article> articles = new ArrayList<Article>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
System.out.println("avant connexion");
            connexion = daoFactory.getConnection();
System.out.println("connexion effectu�e");
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT nom, description, prix FROM articles;");
System.out.println("requete execut�e");

            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String description = resultat.getString("description");
                Float prix = resultat.getFloat("prix");

                Article article = new Article();
                article.setNom(nom);
                article.setDescription(description);
                article.setPrix(prix);

                articles.add(article);
            }
System.out.println("liste des articles chargés");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

}