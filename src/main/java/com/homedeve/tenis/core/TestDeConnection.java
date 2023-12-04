package com.homedeve.tenis.core;

//import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {

            //utilisation du pool de connexion
            //BasicDataSource dataSource =  new BasicDataSource();

            /*MysqlDataSource dataSource =  new MysqlDataSource();

            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?serverTimezone=GMT");
            dataSource.setUser("homedeve");
            dataSource.setPassword("root");
             */

            //conn = dataSource.getConnection();

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=GMT","homedeve","root");
            conn.setAutoCommit(false);

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
            /*
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
            //fin requette preparés
            */



            //Modification en BD
            /*
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=? WHERE ID=?");
            long identifiant = 1L;
            final String nom = "Djoulako";
            final String prenom = "Camel";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setLong(3, identifiant);

           int  nombreEnregistrementModifier = preparedStatement.executeUpdate(); //retourne le nombre d'enregistrement modifier

            System.out.println(nombreEnregistrementModifier);
            //Modif 2
            String sql = "UPDATE JOUEUR SET SEXE=? WHERE ID=?";
            PreparedStatement st2 = conn.prepareStatement(sql);
            st2.setString(1, "M");
            st2.setLong(2, 2);
            st2.executeUpdate();

            */

            //Les transactions
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES(?, ?, ?)");
            String nom = "Donald";
            String prenom = "Zankia";
            String sexe = "M";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();


            nom = "Emanuel";
            prenom = "Roland";
            sexe = "M";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            //on essaye de faire echouer
            //preparedStatement.setNull(3, Types.CHAR);


            preparedStatement.executeUpdate();

            conn.commit();


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

                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

