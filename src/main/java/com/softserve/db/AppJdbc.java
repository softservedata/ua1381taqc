package com.softserve.db;

import java.sql.*;

public class AppJdbc {

    public static void main(String[] args) throws SQLException {
        System.out.println("Start...");
        //
        Connection con = null;
        String username = "pmp2025"; //"pmi2024";
        String password = "Pmp#2025"; //"Pmi#2024";
        //String url = "jdbc:mysql://192.168.6.183:3306/";
        String url = "jdbc:mysql://192.168.6.183:3306/ua1381";
        //
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //
        System.out.println("Connect...");
        con = DriverManager.getConnection(url, username, password);
        if (con != null) {
            System.out.println("Connection Successful! \n");
        } else {
            System.out.println("Connection ERROR \n");
            System.exit(1);
        }
        //
        Statement st = con.createStatement();
        //st.execute("CREATE DATABASE ua1381;");
        //st.execute("create database ua1381 character set utf8 collate utf8_bin;");
        //
        //st.execute("use ua1381;");
        //
        /* DDL Create
        String query = "CREATE TABLE temp"
                //+ "( id int unsigned not null auto_increment primary key, "
                + "( id integer NOT NULL,"
                + "name character varying(255),"
                + "login character varying(255),"
                + "password character varying(255),"
                + "age integer,"
                + "CONSTRAINT id_key PRIMARY KEY (id),"
                + "CONSTRAINT uniq UNIQUE (id));";
        st.execute(query);
        */
        /* DML Insert Data
        //String query = "INSERT INTO temp (id,name,login,password,age) VALUES (1,'Petro','pet','123456',22);";
        //String query = "INSERT INTO temp (id,name,login,password,age) VALUES (2,'Petro2','pet2','123456',23);";
        String query = "INSERT INTO temp (id,name,login,password,age) VALUES (3,'Stepan3','step','123456',21);";
        //
        // Update Data
        //String query = "UPDATE temp SET name='Ira' WHERE id=1;";
        //String query = "UPDATE temp SET name='Tolik' WHERE login LIKE 'st%';";
        st.execute(query);
        */
        /* Delete Data
        //boolean res = st.execute("DELETE FROM temp WHERE name='Tolik';");
        //System.out.println("res = " + res); // false; ResultSet not found;
        int res2 = st.executeUpdate("DELETE FROM temp WHERE name='Ira';");
        System.out.println("res2 = " + res2); // count
        */
        // Read Data
        //ResultSet rs = st.executeQuery("select id,name,login,password,age from temp;");
        ResultSet rs = st.executeQuery("select * from temp;");
        /*
        ResultSet rs = null;
        boolean res = st.execute("select id,name,login,password,age from temp;");
        System.out.println("res = " + res);
        if (res) {
            rs = st.getResultSet();
        }
        */
        //
        int columnCount = rs.getMetaData().getColumnCount();
        // Resultset.getMetaData() get the information
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(rs.getMetaData().getColumnName(i) + "\t");
            //System.out.print(rs.getMetaData().getColumnLabel(i) + "\t");
        }
        System.out.println();
        //
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
        System.out.println("before close");
        //
        if (rs != null) {
            rs.close();
        }
        //
        if (st != null) {
            st.close();
        }
        if (con != null) {
            con.close();
        }
        System.out.println("DONE");
    }
}  