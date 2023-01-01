package com.mte.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
public class MTEDataSourceConfig {
    private static MTEDataSourceConfig instance;

    private static final String driver = "com.mysql.cj.jdbc.Driver";

    @Value("${jdbc.mysql.t.port}")
    private String port;

    @Value("${jdbc.mysql.t.url}")
    private String url;

    @Value("${jdbc.mysql.t.username}")
    private String username;

    @Value("${jdbc.mysql.t.password}")
    private String password;

    @Bean(name="mteDataSource")
    @Primary
    public DataSource mteDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String jdbcURL = String.format("jdbc:mysql://%s:%s/MTE?autoReconnect=true&useSSL=false&allowMultiQueries=true&useUnicode=true&characterEncoding=utf8", url, port);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(jdbcURL);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate (){
        return new NamedParameterJdbcTemplate(mteDataSource());
    };

    @Bean (name = "mteSessionFactory")
    @Primary
    public SqlSessionFactoryBean mteSessionFactory(ApplicationContext applicationContext) throws IOException {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(mteDataSource());
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:config/mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:sql/**/manager-mapper.xml"));

        return factoryBean;
    }

    @Bean (name="coreTransactionMteManager")
    @Primary
    public PlatformTransactionManager coreTransactionMteManager() {
        return new DataSourceTransactionManager(mteDataSource());
    }

    @Bean (name="sqlCoreMteSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlCoreMteSessionTemplate(SqlSessionFactory targetSessionFactory) {
        return new SqlSessionTemplate(targetSessionFactory);
    }

}
