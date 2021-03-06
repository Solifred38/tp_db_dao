package com.tp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tp.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
    private DaoFactory daoFactory;

    UtilisateurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Utilisateur utilisateur) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Utilisateur> lister() {
System.out.println("Entree dans Lister");
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
System.out.println("avant connexion");
            connexion = daoFactory.getConnection();
System.out.println("connexion effectu�e");
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");
System.out.println("requete execut�e");

            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");

                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);

                utilisateurs.add(utilisateur);
            }
System.out.println("liste des utilisateurs charg�e");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

}