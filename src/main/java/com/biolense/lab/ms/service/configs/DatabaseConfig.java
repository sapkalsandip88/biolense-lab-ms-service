/*
package com.biolense.lab.ms.service.configs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

@Data
@Slf4j
@NoArgsConstructor
@ConditionalOnProperty(value = "db.enabled", havingValue = "true", matchIfMissing = true)
@Configuration
@PropertySource("databaseConfig.properties")
public class DatabaseConfig {

    @Value("${db.url}")
    protected String url;
    @Value("${db.username}")
    protected String username;
    @Value("${db.password}")
    protected String password;
    @Value("${db.driverClassName}")
    protected String driverClassName;

    @Bean
    @Primary
    DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        log.info("url : {}", url);
        log.info("username : {}", username);
        log.info("password : {}", password);
        if(!StringUtils.isEmpty(url)) {
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
        }
        return dataSource;
    }
}
*/
