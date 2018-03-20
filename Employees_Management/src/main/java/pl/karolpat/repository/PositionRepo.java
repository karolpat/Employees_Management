package pl.karolpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.Position;

public interface PositionRepo extends JpaRepository<Position, Long> {

	/**
	 * @param name
	 *            name of the position to be found.
	 * @return
	 */
	Position findOneByName(String name);

}
