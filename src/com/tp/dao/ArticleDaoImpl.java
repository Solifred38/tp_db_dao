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
	public void ajouter(Article article,String nomMagasin) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("INSERT INTO articles(nom, description, prix,nommagasin) VALUES(?, ?,?,?);");
			preparedStatement.setString(1, article.getNom());
			preparedStatement.setString(2, article.getDescription());
			preparedStatement.setFloat(3, article.getPrix());
			preparedStatement.setString(4, nomMagasin);

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
			resultat = statement.executeQuery("SELECT idarticles, nom, description, prix, nommagasin FROM articles;");

			while (resultat.next()) {
				Article article = new Article();
				article.setIdArticle(resultat.getInt("idarticles"));
				article.setNom(resultat.getString("nom"));
				article.setDescription(resultat.getString("description"));
				article.setPrix(resultat.getFloat("prix"));
				article.setNomMagasin(resultat.getString("nommagasin"));
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
      PreparedStatement preparedstatement = null;
        try {
            connexion = daoFactory.getConnection();
            String deleteStr="DELETE FROM articles WHERE idarticles=?";
            preparedstatement = connexion.prepareStatement(deleteStr);
            preparedstatement.setInt(1, article.getIdArticle());
            preparedstatement.executeUpdate();
    		System.out.println("mon article "+article.getNom()+" est supprim�");
        }
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}