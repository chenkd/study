package com.chenkeda.transaction;

import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Data
public class TransactionDemo {
    AnnotationConfigApplicationContext applicationContext;

    public static Logger log = Logger.getLogger(TransactionDemo.class);
    public static void main(String[] args) {
        TransactionDemo transactionDemo = new TransactionDemo();
        transactionDemo.init();
        A a = transactionDemo.getApplicationContext().getBean(A.class);
        a.a();
    }
    public void init() {
        applicationContext = new AnnotationConfigApplicationContext(Config.class);
    }

}
@Configuration
@EnableTransactionManagement
@ComponentScan("com.chenkeda.transaction")
class Config {
    @SneakyThrows
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClass();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

}
