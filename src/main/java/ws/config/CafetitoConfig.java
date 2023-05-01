/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.config;

import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configuración de la base de datos postgreSQL para los modelos 
 * del Beneficio de Cafe. 
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "ws.cafetito.repository",
        entityManagerFactoryRef = "postgresqlEntityManagerFactory",
        transactionManagerRef = "postgresqlTransactionManager"
)
@EnableAutoConfiguration
public class CafetitoConfig {
    
    // obteniendo los valores de propiedad de la base de datos
    // postgreSQL a configurar. 
    
    @Value("${postgresql.url}")
    private String url;

    @Value("${postgresql.username}")
    private String username;

    @Value("${postgresql.password}")
    private String password;

    /**
     * Configuración de la fuente de datos para postgreSQL
     * @return fuente de datos para postgreSQL
     */
    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * Configuración del administrador de entidades para postgreSQL.
     *
     * @param builder Constructor de administradores de entidades
     * @return administrador de entidades de postgreSQL
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
            @Qualifier("postgresql") EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(postgresDataSource())
                .packages("ws.cafetito.model")
                .persistenceUnit("postgresql")
                .build();
    } 


    /**
     * Configuración del administrador de transacciones para postgreSQL.
     *
     * @param entityManagerFactory Administrador de entidades de postgreSQL
     * @return administrador de transacciones de postgreSQL
     */
    @Bean
    public PlatformTransactionManager postgresqlTransactionManager(
            @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
   

    /**
     * Constructor del administrador de entidades para postgreSQL.
     * @return constructor del administrador de entidades de postgreSQL
     */
    @Bean
    @Qualifier("postgresql")
    public EntityManagerFactoryBuilder postgresqlEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}
