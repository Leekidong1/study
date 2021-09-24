package bit.com.a;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration	// spring boot가 먼저읽는다. member.xml 설정
public class DatabaseConfig {	//DB설정
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		System.out.println("DatabaseConfig sqlSessionFactory");
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);	//외부에서 들어오는 Data 설정
		
		Resource[] arrResource = new PathMatchingResourcePatternResolver().getResources("classpath:sqls/*.xml");	// sqls폴더 .xml파일들은 Mybatis로 취급설정
		sqlSessionFactoryBean.setMapperLocations(arrResource);
		sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		
		return (SqlSessionFactory)sqlSessionFactoryBean.getObject();
		
	}
	
	@Bean
	public SqlSessionTemplate SqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
