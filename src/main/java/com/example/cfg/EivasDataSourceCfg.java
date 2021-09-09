package com.example.cfg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class EivasDataSourceCfg {

    @Bean(name = "eivasDataSource")
    @ConfigurationProperties(prefix = "eivas.datasource")
    public DataSource dataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    public TokenStore tokenStore(@Qualifier("eivasDataSource") DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }
}
