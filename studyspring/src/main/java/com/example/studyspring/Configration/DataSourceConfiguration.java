//package com.example.studyspring.Configration;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
///**
// * com.xinguangnet.tuchao.merchant.manage
// *
// * @Author : Wukn
// * @Date : 2018/2/5
// */
//@Configuration
//public class DataSourceConfiguration {
//
//
//    /**
//     * 第一个数据源
//     * @return
//     */
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//    /**
//     * 第二个数据源
//     * @return
//     */
//    @Bean(name = "secondDatasource")
//    @ConfigurationProperties(prefix = "spring.second-datasource")
//    public DataSource secondDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//
//    @Bean
//    @Primary
//    public JdbcTemplate primaryJdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//
//
//    @Bean(name = "secondJdbcTemplate")
//    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDatasource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
