package br.com.mv.breakfast.dbconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile("dev")
public class ConfigurationDeveloper {

	@Bean
    public DataSource dataSource(@Value("${spring.datasource.url}") String url,
								 @Value("${spring.datasource.username}") String username,
								 @Value("${spring.datasource.password}") String password,
								 @Value("${spring.datasource.driver-class-name}") String driverClassName) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

	@Bean
	public JpaVendorAdapter jpaVendorAdapter(@Value("${spring.jpa.database-platform}") String databasePlatform) {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform(databasePlatform);
		adapter.setPrepareConnection(true);
		return adapter;
	}

}
