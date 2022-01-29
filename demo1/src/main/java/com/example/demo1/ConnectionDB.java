package com.example.demo1;

import java.sql.*;
import java.util.LinkedList;


public class ConnectionDB {
    String ModelName;
    int RemovalEfficiency;
    String NewModelName;
    int NewRemovalEfficiency;
    String Query;

    public static Connection main () {

        Connection con = null;
        try {
            ConnectionDB test = new ConnectionDB();
            String databaseName = "javafx_db";
            String user = "root";
            String pass = "root";
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            //test.insert("ab",80,stmt);
            //test.edit("ab", 80, "Banan", 6, stmt);
            //LinkedList<Test> extract = new LinkedList();
           //extract = test.extraction(stmt);

            if (con != null) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void insert(String Name, int Efficiency,Statement stmt) throws SQLException {
        ModelName= Name;
        RemovalEfficiency=Efficiency;
        Query= "INSERT INTO model (ModelName,RemovalEfficiency ) VALUES('" + ModelName+"',"+RemovalEfficiency+");";
        stmt.executeUpdate(Query);
    }

    public void delete(String Name,int Efficiency,Statement stmt) throws SQLException {
        ModelName= Name;
        RemovalEfficiency=Efficiency;
        Query= "DELETE FROM model WHERE ModelName = '"+ModelName+"'"+"AND RemovalEfficiency ="+RemovalEfficiency+";";
        stmt.executeUpdate(Query);
    }

    public void edit(String Name,int Efficiency,String NewName,int NewEfficiency,Statement stmt) throws SQLException {
        ModelName= Name;
        RemovalEfficiency=Efficiency;
        NewModelName= NewName;
        NewRemovalEfficiency=NewEfficiency;
        Query= "UPDATE model SET ModelName = '"+NewModelName+"' , RemovalEfficiency ="+ NewRemovalEfficiency+" WHERE ModelName = '"  +
                ModelName+"' AND RemovalEfficiency= "+RemovalEfficiency;
        stmt.executeUpdate(Query);

    }

    public LinkedList <Test> extraction(Statement stmt){
        LinkedList<Test> extract = new LinkedList();
        try {
            ResultSet rs = stmt.executeQuery("SELECT ModelName, RemovalEfficiency FROM model;");
            while (rs.next()) {
                Test newTest = new Test( rs.getString("ModelName"),rs.getInt("RemovalEfficiency"));
                extract.add(newTest);
            }

            for(Test print : extract){
                System.out.println(print.modelName);
                System.out.println(print.removal);
            }

        } catch (SQLException e) {
           e.printStackTrace();
        }

        return extract;
    }
}

class Test{
    String modelName;
    int removal;
    public Test(String modelName,int removal){
        this.modelName = modelName;
        this.removal = removal;
    }
}