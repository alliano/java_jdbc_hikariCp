package com.javadatabase;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * untuk menbgkoneksikan database kita terlebih dahulu meregistrasikan driver
 * setelah itu kita buat koneksi
 */
public class TestConnection {
    
    /**
     * BeforeAll maksudnya adalah : ketika App pertmakali runing maka method
     * yang di anotasi dengan @BeforeAll akan di jalankan dan dijalankan hanya 1x
     */
    @BeforeAll
    static void registrationDatabase(){
        try {
            Driver driverSql = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driverSql);
        } catch (SQLException Sqlexception) {
            Assertions.fail(Sqlexception);
        }
    }

    @Test
    void testConnection(){
        /**
         * untuk membuat koneksi kita bisa menggunakan interface abstrac Connection dari java.sql.Connection
         * dan setelah itu untuk megkoneksikan java ke mysql kita menggunakan Class DriverManager
         * dan memanggil method getConnetion method ini menerima 3 parameter :
         * 1-> jdbcUrl
         * 2-> username Mysql
         * 3-> password mysql
         * 
         * untuk jdbc url jika bisa menambahkan parameter spesific kepada timezone kita dengan cara
         * menambahkan tanda ? setelah nama database nya dandiikuti dengan serverTimezone="Lokasi anda"
         * disini saya mencontohkan dengan menggunakan waktu wib maka saya bisa meng set timezone nya menjadi
         * Asia/Jakarta 
         * 
         * menambahkan parameter serverTimezone bisa menghindari error timezone di java
         */
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database?serverTimezone=Asia/Jakarta";
        String user = "alliano-dev";
        String password = "alliano361**";
        try{
            Connection connectionSql = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("berhasi terkoneksi");
            /**
             * setelah kita membuka koneksi atau mengkoneksikan database kita maka kita wajib untuk
             * meng close koneksi kita dikarnakan jikalau kita tidak menutup koneksinya
             * maka nanti bisa terjadi error karna kebanyakan koneksi 
             * karna setiap user nanti nya yang reques database maka akan membuat koneksi baru 
             * jikalau kita tidak meng close koneksi kita 
             * bayangkan jikalau applikasi kita ada 100.000.000 user udah otomatis itu bakalan error
             * karna kebanyakan koneksi 
             * dan batas atau limit connection di mysqll adalah 151 connection
             */
            connectionSql.close();

        }catch(SQLException exception){
            Assertions.fail(exception);
        }
        
    }

    /**
     * melakukan koneksi secara menual tidaklah baik dan bijak karna untuk 
     * melakukan koneksi secara manual tentusaja akan memakan banyak biaya dan 
     * resource memory yang sangat besar
     * untuk mengatasi masalah ini kita bisa mengunakan connection pool
     */
}
