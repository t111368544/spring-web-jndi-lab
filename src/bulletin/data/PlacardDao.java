package bulletin.data;

import java.util.List;

import bulletin.Placard;

public interface PlacardDao {

	List<Placard> findRecentPlacards();

	List<Placard> findPlacards(int max);

	Placard findBy(int id);

	void save(Placard placard);

}
