package com.javadatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import com.javadatabase.connection.ConnectionUtil;

public class StatementTest {
    
    @Test
    void statementTest()throws SQLException {
        //ini untuk mengkoneksikan java dengan mysql 
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        //imi untuk kita membuat query sql nanti DML(Database Manipulation Langguage)
        Statement statement = connection.createStatement();



        //jangan lupa meng close koneksi dan statement
        statement.close();
        connection.close();
    }

    @Test
    void testInsertData()throws SQLException{
        Connection connecttion = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connecttion.createStatement();
        String query = """
                INSERT INTO costumer(id,name,email) VALUES ('0001','alliano','alliano@gmail.com');
                """;
        int resultInsert =  statement.executeUpdate(query);
        System.out.println(resultInsert);

        statement.close();
        connecttion.close();
    }
}
