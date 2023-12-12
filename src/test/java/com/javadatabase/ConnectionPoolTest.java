package com.javadatabase;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.javadatabase.connection.ConnectionUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * HikariCp adalah libelary yg sangat populer untuk mengimplementasikan connection pool
 * 
 */
public class ConnectionPoolTest {
    
    @Test
    void testHikariCp(){
        /**
         * untuk menggunakan hikaricp kita terlebi dahulu konfigurasi hikaricp nya
         * dengan cara implementasi class HikariConfig
         */
        HikariConfig configuration = new HikariConfig();
        //disini kita menulisakan nama class driver yang mau kita gunakan 
        configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //jdbcUrl kita
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/java_database?serverTimezone=Asia/Jakarta");
        //user mysql kita
        configuration.setUsername("alliano-dev");
        //password mysql kita
        configuration.setPassword("{mysql.password}");

        /**
         * setelah selesai meng set identitas database/mysql kita
         * kita bisa melakukan configuration pool
         */

         /**
          * ini artinya kita membuat koneksi sebanyak 50 koneksi
          * jadi sesibuk apapun server msyql kita nanti atau sesibuk apapun nanti requst user
          * koneksinya tidaklebih dari 50 connection 
          */
        configuration.setMaximumPoolSize(50);
        /**
         * setMinimumIdle maksutnya adalah jikalau nantu connection pool kita itu tidak terpakai dan 
         * si connection pool nya masuk semua ke dalam pool
         * maka HikariCp akan meng close koneksi nya agar tidak membuang2 resource 
         */
        configuration.setMinimumIdle(5);
        /**
         * ini maksudnya jikalau nanti connection kita tidak terpaka selama 60 rubu detik atau damadengan
         * 60 detik maka secara otomatis HikariCp akan meng close connection
         * tapi tenang saja karnya kita telah meng set minimumIdle
         */
        configuration.setIdleTimeout(60_000);
        /**
         * setMaxLifeTime adalah jikalau nanti ada reques dari clien dan request itu
         * tidak resolve selama 10 * 60_000/ 10 menit maka HikariCp akan secara otomatis 
         * restart connection atau meng koneksikan kembali
         * 
         * ini juga untuk menghandle jikalau request client timeOut
         */
        configuration.setMaxLifetime(10 * 60_000);

        //Membuat connection pool

        try {
            /**
             * untuk membuat datasource kita bisa implementasikan class
             * HikariDatasource yang menerima parameter configuraion yang kita buat
             */
            HikariDataSource dataSource = new HikariDataSource(configuration);
            Connection connection = dataSource.getConnection();
            /**disini kita close koneksi tapi sebenarnya yang dilakukan java adalah
             * menggembalikan connection ke HikariCp
             */
            connection.close();
            /**
             * setelah kita selesai maka kita akan meng close datasource nya 
             * untuk meng close connection yang ada di pool nya karna sudah tidak di gunakan 
             */
            dataSource.close();
        } catch (SQLException E) {
            Assertions.fail(E);
        }  
    }
    @Test
    void testUtil()throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.close();
    }
}
