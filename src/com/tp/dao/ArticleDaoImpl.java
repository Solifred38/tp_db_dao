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
			preparedStatement = connexion
					.prepareStatement("INSERT INTO articles(nom, description, prix) VALUES(?, ?,?);");
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
		List<Article> articles = new ArrayList<Article>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT idarticles, nom, description, prix FROM articles;");

			while (resultat.next()) {
				int idarticle = resultat.getInt("idarticles");
				String nom = resultat.getString("nom");
				String description = resultat.getString("description");
				Float prix = resultat.getFloat("prix");

				Article article = new Article();
				article.setIdarticle(idarticle);
				article.setNom(nom);
				article.setDescription(description);
				article.setPrix(prix);

				articles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public void supprimer(Article article) {
		// TODO Auto-generated method stub
	      Connection connexion = null;
	        Statement statement = null;
	        ResultSet resultat = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            String deleteStr="DELETE FROM articles WHERE idarticles="+article.getIdarticle()+";";
            resultat = statement.executeQuery(deleteStr);
    		System.out.println("mon article "+article.getNom()+" est supprimé");
        }
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}