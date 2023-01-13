package bulletin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bulletin.Placard;

@Repository
public class PlacardDaoImpl implements PlacardDao {

	private JdbcOperations jdbc;

	@Autowired
	public PlacardDaoImpl(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	public List<Placard> findRecentPlacards() {
		return jdbc.query("select id, message, created_at from Placard order by created_at desc",
				new PlacardRowMapper());
	}

	public List<Placard> findPlacards(int max) {
		return jdbc.query("select id, message, created_at from Placard where id < ? order by created_at desc",
				new PlacardRowMapper(), max);
	}

	public Placard findBy(int id) {
		return jdbc.queryForObject("select id, message, created_at from Placard where id = ?", new PlacardRowMapper(),
				id);
	}

	public void save(Placard placard) {
		jdbc.update("insert into Placard (message, created_at) values (?, ?)", placard.getMessage(), placard.getTime());
	}

	private static class PlacardRowMapper implements RowMapper<Placard> {
		public Placard mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Placard(rs.getLong("id"), rs.getString("message"), rs.getTimestamp("created_at"));
		}
	}

}
