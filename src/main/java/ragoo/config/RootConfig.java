package ragoo.config;

/*
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
*/
import org.springframework.context.annotation.Configuration;

/**
 * 루트 설정용 클래스.
 * 이 클래스는 스프링의 root-context.xml 의 역할을 대신한다.
 * @author mj
 *
 */
@Configuration
public class RootConfig {

    /*
    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    private static final String APP_CONFIG_FILE_PATH = "application.xml";
    private static final String MYBATIS_CONFIG_FILE_PATH = "mybatis-ragoo.config.xml";
    */

    /**
     * 프로퍼티 홀더는 다른 빈들이 사용하는 프로퍼티들을 로딩하기 때문에, static 메소드로 실행된다.
     * 다른 일반 빈들이 만들어지기전에 먼저 만들어져야 한다.
     * @return
     */
    /*
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer()
    {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(new Resource[] { new ClassPathResource(APP_CONFIG_FILE_PATH) });
        return ppc;
    }

    @Bean
    public DataSource dataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.jdbcDriverClassName);
        dataSource.setUrl(this.jdbcUrl);
        dataSource.setUsername(this.jdbcUsername);
        dataSource.setPassword(this.jdbcPassword);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception
    {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG_FILE_PATH));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSession sqlSession() throws Exception
    {
        SqlSession sqlSession = new SqlSessionTemplate(this.sqlSessionFactory());
        return sqlSession;
    }
    */

    /**
     * MyBatis 를 사용하지 않고, 쌩 jdbcTemplate 를 이용해서 데이터베이스를 조회하는 DAO 를 만들기 위한 Bean.
     * 실전에서는 사용할 일이 거의 없다. 예제를 위해서 넣은 코드.
     * @return
     */
    /*
    @Bean
    public JdbcTemplate jdbcTemplate()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(this.dataSource());
        return jdbcTemplate;
    }
    */
}











