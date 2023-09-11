package com.homedeve.tenis.core;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis","homedeve","root");

            //Oracle Driver officiel OJDBC Thin
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","homedeve","root");

            //Postgres Driver officiel
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","homedeve","root");

            /*
            Statement statement = conn.createStatement();

            /transmission d'une requette au serveur
            ResultSet rs = statement.executeQuery("SELECT NOM, PRENOM, ID FROM JOUEUR");

             while (rs.next()) {
                final String nom = rs.getString("NOM");
                final String prenom = rs.getString("PRENOM");
                final long id = rs.getLong("ID");

                 System.out.println("Joueur" + id + " : " + prenom + " " + nom);
             }



             rs.close();
             statement.close();*/

            //Requettes préparés

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM, PRENOM, ID FROM JOUEUR WHERE ID=?");
            long identifiant = 78;
            preparedStatement.setLong(1, identifiant);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                final String nom = rs.getString("NOM");
                final String prenom = rs.getString("PRENOM");
                final long id = rs.getLong("ID");

                System.out.println("Joueur" + id + " : " + prenom + " " + nom);
            } else {
                System.out.println("Il y'a pa d'enregidtrement d'ID 128");
            }





            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

