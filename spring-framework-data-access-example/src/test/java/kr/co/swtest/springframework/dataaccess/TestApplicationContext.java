package kr.co.swtest.springframework.dataaccess;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * 테스트 애플리케이션 컨텍스트 
 * 
 * @author <a href="mailto:scroogy@swtest.co.kr">최영목</a>
 */
@Configuration
public class TestApplicationContext {

    @Bean(name = "dataSource")
    @Scope("prototype")
    public EmbeddedDatabase embeddedDatabase() {
        return new EmbeddedDatabaseBuilder() //
                .setType(EmbeddedDatabaseType.HSQL) //
                .addScript("/script/schema.sql") //
                .addScript("/script/test-data.sql") //
                .build();
    }

}
