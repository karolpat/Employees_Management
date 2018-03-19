package pl.karolpat.service;

import pl.karolpat.entity.Position;
import pl.karolpat.exception.PositionNotFoundException;

public interface PositionService {

	Position findOneById(long id) throws PositionNotFoundException;
}
