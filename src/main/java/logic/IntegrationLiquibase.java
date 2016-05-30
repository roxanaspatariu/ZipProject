package logic;


import liquibase.integration.spring.SpringLiquibase;
import org.aspectj.lang.annotation.control.CodeGenerationHint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Created by P3700665 on 5/30/2016.
 */
@Configuration
public class IntegrationLiquibase {

    @Bean
    public DataSource dataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("spring.datasource.CONFIGURATION_PROPERTIES");
    }
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();

        try {
            liquibase.setDataSource(dataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
        liquibase.setChangeLog("classpath:db-changelog.xml");
        liquibase.setContexts("test, production");

        return liquibase;
    }
}
