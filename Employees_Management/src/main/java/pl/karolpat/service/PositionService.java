package pl.karolpat.service;

import java.util.List;
import java.util.Map;

import pl.karolpat.entity.Position;
import pl.karolpat.exception.PositionExistsException;
import pl.karolpat.exception.PositionNotFoundException;

public interface PositionService {

	Position findOneById(long id) throws PositionNotFoundException;
	
	List<Position> getAll();
	
	Position add(String name) throws PositionExistsException;
	
	Map<String, Integer> getPositionsWithEmployeesNumber();
}