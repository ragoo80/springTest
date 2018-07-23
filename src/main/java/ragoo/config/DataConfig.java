package ragoo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

import org.springframework.core.env.Environment;

/*
아래와 같이 톰캣/bin/catalina.sh에 수정 할 경우 아래 값이 다른 값들보다 우선순위로 실행
JAVA_OPTS="$JAVA_OPTS -Djava.protocol.handler.pkgs=org.apache.catalina.webresources"
JAVA_OPTS="$JAVA_OPTS -Djava.protocol.handler.pkgs=org.apache.catalina.webresources -Dspring.profiles.active=dev"
*/
@Configuration
@PropertySource(value = {
        "classpath:ragoo/config/config-${spring.profiles.active:local}.properties"
})
public class DataConfig {


    @Autowired
    Environment env;

    @Autowired
    ApplicationContext applicationcontext;


    /*
    org.apache.commons.configuration.PropertiesConfiguration로 사용할 경우
    applicationContext.xml에 <constructor-arg type="java.lang.String" value="classpath:/resources/ragoo/config/DB_config.properties"/> 선언
    @Value("dataSource.getStringArray('db.url')")
    private String DBurl;
    @Value("dataSource.getStringArray('db.user')")
    private String DBuser;
    @Value("dataSource.getStringArray('db.password')")
    private String DBpw;

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(DBurl);
        dataSource.setUsername(DBuser);
        dataSource.setPassword(DBpw);
        return dataSource;
    }
    */

    //hardcoding으로 사용 할 경우
    /*@Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // 하드 코딩시 setDriver는 안써야 함.
        //dataSource.setDriverClassName("com.mysql.jdbc.Dirver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/BookTest");
        dataSource.setUsername("root");
        dataSource.setPassword("tt43uu22");
        return dataSource;
    }*/




    @Bean
    public DataSource dataSource()
    {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("db.driverClassName"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, ApplicationContext applicationContext)
            throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/ragoo/config/mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/ragoo/sql/mapper/*.xml"));
        return factoryBean;
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
