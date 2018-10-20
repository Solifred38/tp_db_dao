package com.tp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tp.beans.Subtitlor;

public class SubtitlorDaoImpl implements SubtitlorDao {
    private DaoFactory daoFactory;

    public SubtitlorDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouter(Subtitlor subtitlor) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO lines(ligne,lignetraduite) VALUES(?,?);");
            preparedStatement.setString(1, subtitlor.getLigne());
            preparedStatement.setString(2, subtitlor.getLignetraduite());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Subtitlor> lister() {
System.out.println("Entree dans Lister");
        List<Subtitlor> subtitlors = new ArrayList<Subtitlor>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
System.out.println("avant connexion");
            connexion = daoFactory.getConnection();
System.out.println("connexion effectuï¿½e");
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT ligne, lignetraduite FROM lignes;");
System.out.println("requete executï¿½e");

            while (resultat.next()) {
                String ligne = resultat.getString("ligne");
                String lignetraduite = resultat.getString("lignetraduite");

                Subtitlor subtitlor = new Subtitlor();
                subtitlor.setLigne(ligne);
                subtitlor.setLangue(lignetraduite);

                subtitlors.add(subtitlor);
            }
System.out.println("liste des lignes chargés");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subtitlors;
    }



}