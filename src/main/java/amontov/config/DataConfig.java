package amontov.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:app.properties")
@EnableJpaRepositories(basePackages = {"amontov.repository"},
        entityManagerFactoryRef = "entityManager",
        transactionManagerRef = "jpaTransactionManager")
public class DataConfig {
    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    public DataConfig() {
        super();
    }
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        return dataSource;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation () {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties hibernateProp() {
        Properties hibernateProp = new Properties();
        hibernateProp.setProperty("db.hibernate.dialect", env.getRequiredProperty("db.hibernate.dialect"));
        hibernateProp.setProperty("db.hibernate.show_sql", env.getRequiredProperty("db.hibernate.show_sql"));
        hibernateProp.setProperty("db.hibernate.hbm2ddl.auto", env.getRequiredProperty("db.hibernate.hbm2ddl.auto"));

        return hibernateProp;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManager () {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManager.setPackagesToScan(env.getRequiredProperty("db.entitymanager.packages.to.scan"));
        entityManager.setJpaProperties(hibernateProp());

        return entityManager;
    }
    @Bean
    public JpaTransactionManager jpaTransactionManager () {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManager().getObject());
        return jpaTransactionManager;
    }
}
