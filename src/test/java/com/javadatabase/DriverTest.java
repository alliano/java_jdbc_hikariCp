package com.javadatabase;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DriverTest {
    
    /**
     * untuk megkoneksikan java dengan database kita wajib meregistrasikan driver nya dulu
     * disini saya akan menggunakan mysql untuk database nya
     * 
     */
    @Test
    void testRegistration() {
        /**
         * disini kita menggunakan block try and catch 
         * kita akan menggunakan interface dari Driver dari java.sql.Driver
         * karna interface abstrac Driver dia menghasilkan Exception
         * seterlah kita membuat variable dengan tipe interface dari Driver maka kita akan 
         * menginstansiasi Driver dari sinini new com.mysql.cj.jdbc.Driver() 
         * 
         */
        try{
            Driver driverSql = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driverSql);
        }catch(SQLException exception){
            Assertions.fail(exception);
        }

    }
}
