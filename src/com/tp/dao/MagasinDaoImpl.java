package com.tp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tp.beans.Article;
import com.tp.beans.Magasin;

public class MagasinDaoImpl implements MagasinDao {
	private DaoFactory daoFactory;

	MagasinDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Magasin magasin) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("INSERT INTO articles(nom, description) VALUES(?, ?,?);");
			preparedStatement.setString(1, magasin.getNom());
			preparedStatement.setString(2, magasin.getDescription());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Magasin> lister() {
		List<Magasin> magasins = new ArrayList<Magasin>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT idmagasin, nom, description FROM magasins;");

			while (resultat.next()) {
				int idmagasin = resultat.getInt("idmagasin");
				String nom = resultat.getString("nom");
				String description = resultat.getString("description");

				Magasin magasin = new Magasin();
				magasin.setIdmagasin(idmagasin);
				magasin.setNom(nom);
				magasin.setDescription(description);

				magasins.add(magasin);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return magasins;
	}

	@Override
	public void supprimer(Magasin article) {
		// TODO Auto-generated method stub
      Connection connexion = null;
      PreparedStatement preparedstatement = null;
        try {
            connexion = daoFactory.getConnection();
            String deleteStr="DELETE FROM articles WHERE idarticles=?";
            preparedstatement = connexion.prepareStatement(deleteStr);
            preparedstatement.setInt(1, article.getIdmagasin());
            preparedstatement.executeUpdate();
    		System.out.println("mon article "+article.getNom()+" est supprim�");
        }
        catch (SQLException e) {
			e.printStackTrace();
		}
	}


}