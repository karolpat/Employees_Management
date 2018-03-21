package pl.karolpat.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolpat.entity.Position;
import pl.karolpat.exception.PositionExistsException;
import pl.karolpat.exception.PositionNotFoundException;
import pl.karolpat.repository.PositionRepo;
import pl.karolpat.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {

	private PositionRepo positionRepo;

	public PositionServiceImpl(PositionRepo positionRepo) {
		this.positionRepo = positionRepo;
	}

	@Override
	public Position findOneById(long id) throws PositionNotFoundException {

		Position position = positionRepo.findOne(id);
		if (position == null) {
			throw new PositionNotFoundException();
		} else {
			return position;
		}

	}

	@Override
	public List<Position> getAll() {
		return positionRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=PositionExistsException.class)
	public Position add(String name) throws PositionExistsException {
		return positionRepo.save(checkDuplicate(name));
	}

	private Position checkDuplicate(String name) throws PositionExistsException {

		Position position = positionRepo.findOneByName(name);

		if (position != null) {
			throw new PositionExistsException();
		} else {
			position = new Position(name);
			return position;
		}
	}

	@Override
	public Map<String, Long> getPositionsWithEmployeesNumber() {

		Map<String, Long> map = new HashMap<>();
		List<Position> list = positionRepo.findAll();

		for (Position p : list) {
			long count = p.getEmployees().stream().filter(employee -> employee.isActive()).count();
			map.put(p.getName(), count);
		}
		return map;
	}

}
