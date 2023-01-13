package bulletin.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataConfig {
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
			//	.setName(databaseName)
				.addScript("schema.sql")
				.build();
	}
	
	@Bean
	public JdbcOperations jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@PostConstruct
	public void startDBManager() {
		DatabaseManagerSwing.main(
				new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" }
		);
	}
	
}
