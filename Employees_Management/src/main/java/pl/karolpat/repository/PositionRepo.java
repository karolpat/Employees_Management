package pl.karolpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.Position;

public interface PositionRepo extends JpaRepository<Position, Long> {

	
	Position findOneByName(String name);
	
}
