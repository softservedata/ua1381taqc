package com.softserve.db;

import org.junit.jupiter.api.*;

import java.sql.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DbTest {

    private Connection con = null;
    private String username = "pmp2025"; //"pmi2024";
    private String password = "Pmp#2025"; //"Pmi#2024";
    private String url = "jdbc:mysql://192.168.6.183:3306/";
    private Statement st = null;
    private ResultSet rs = null;

    @BeforeAll
    public void setup() throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        if (con == null) {
            System.out.println("Connection ERROR \n");
            throw new RuntimeException("Connection ERROR \n");
        }
        System.out.println("Connection Successful! \n");
        st = con.createStatement();
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public void tear() throws SQLException {
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
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() {
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() {
        System.out.println("\t@AfterEach executed");
    }

    @Test
    public void isDbExist() throws SQLException {
        System.out.println("\t\t@Test isDbExist()");
        boolean isExist = false;
        ResultSet rs = st.executeQuery("show databases;");
        /*
        ResultSet rs = null;
        boolean isRes = st.execute("show databases;");
        System.out.println("isRes = " + isRes);
        if (isRes) {
            rs = st.getResultSet();
        }
        */
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next() && !isExist) {
            for (int i = 1; i <= columnCount && !isExist; i++) {
                //System.out.print(rs.getString(i) + "\t");
                //isExist = rs.getString(i).toLowerCase().contains("ua1381");
                isExist = rs.getString(i).equalsIgnoreCase("ua1381");
            }
        }
        Assertions.assertTrue(isExist);
    }

    @Test
    public void isTableExist() throws SQLException {
        System.out.println("\t\t@Test isTableExist()");
        boolean isExist = false;
        st.execute("use ua1381;");
        ResultSet rs = st.executeQuery("show tables;"); // DDL + DML = SQL
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next() && !isExist) {
            for (int i = 1; i <= columnCount && !isExist; i++) {
                isExist = rs.getString(i).equalsIgnoreCase("temp");
            }
        }
        Assertions.assertTrue(isExist);
    }

    @Test
    public void isNameExist() throws SQLException {
        System.out.println("\t\t@Test isNameExist()");
        boolean isExist = false;
        st.execute("use ua1381;");
        ResultSet rs = st.executeQuery("select * from temp;");
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next() && !isExist) {
            for (int i = 1; i <= columnCount && !isExist; i++) {
                isExist = rs.getString(i).equalsIgnoreCase("Stepan3");
            }
        }
        Assertions.assertTrue(isExist);
    }

    @Test
    public void checkRowsCount() throws SQLException {
        System.out.println("\t\t@Test checkRowsCount()");
        int count = 0;
        st.execute("use ua1381;");
        ResultSet rs = st.executeQuery("select * from temp;");
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            count++;
        }
        Assertions.assertEquals(3, count);
    }

    @Test
    public void checkIdUnique() throws SQLException {
        System.out.println("\t\t@Test checkIdUnique()");
        st.execute("use ua1381;");
        String query = "INSERT INTO temp (id,name,login,password,age) VALUES (4,'Stepan4','step','123456',21);";
        int res = st.executeUpdate(query);
        Assertions.assertEquals(1, res);
        //
        SQLException thrown = Assertions.assertThrows(SQLException.class, () -> {
            st.executeUpdate(query);
        }, "SQLException was expected");
        System.out.println("\t\tMessage = " + thrown.getMessage());
        Assertions.assertEquals("Duplicate entry '4' for key 'temp.PRIMARY'", thrown.getMessage());
        //
        st.execute( "DELETE FROM temp WHERE id=4;"); // Move AfterEach
    }
}