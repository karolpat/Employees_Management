package pl.karolpat.service;

import java.util.List;
import java.util.Map;

import pl.karolpat.entity.Position;
import pl.karolpat.exception.PositionExistsException;
import pl.karolpat.exception.PositionNotFoundException;

public interface PositionService {

	Position findOneById(long id) throws PositionNotFoundException;

	List<Position> getAll();

	/**
	 * Creates new Position with given name.
	 * 
	 * @param name
	 *            name of new position.
	 * @return Just added position,
	 * @throws PositionExistsException
	 *             in case position with given name already exists.
	 */
	Position add(String name) throws PositionExistsException;

	/**
	 * Counts active employees on positions.
	 * 
	 * @return Map with position as String and number of employees as Long.
	 */
	Map<String, Long> getPositionsWithEmployeesNumber();
}