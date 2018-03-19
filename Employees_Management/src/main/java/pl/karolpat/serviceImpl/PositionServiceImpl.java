package pl.karolpat.serviceImpl;

import org.springframework.stereotype.Service;

import pl.karolpat.entity.Position;
import pl.karolpat.exception.PositionNotFoundException;
import pl.karolpat.repository.PositionRepo;
import pl.karolpat.service.PositionService;
@Service
public class PositionServiceImpl implements PositionService {

	private static final String EXCEPTION_MESSAGE = "There is no position with a such ID.";

	private PositionRepo positionRepo;

	public PositionServiceImpl(PositionRepo positionRepo) {
		this.positionRepo = positionRepo;
	}

	@Override
	public Position findOneById(long id) throws PositionNotFoundException {

		Position position = positionRepo.findOne(id);
		if (position == null) {
			throw new PositionNotFoundException(EXCEPTION_MESSAGE);
		} else {
			return position;
		}

	}

}
