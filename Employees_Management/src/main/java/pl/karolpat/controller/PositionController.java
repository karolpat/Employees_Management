package pl.karolpat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.karolpat.entity.Position;
import pl.karolpat.exception.ErrorRespone;
import pl.karolpat.exception.PositionExistsException;
import pl.karolpat.service.PositionService;

@RestController
@RequestMapping("/positions")
public class PositionController {

	private PositionService positionService;

	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}

	@ExceptionHandler(PositionExistsException.class)
	public ResponseEntity<ErrorRespone> handleServerErrorException(Exception e) {
		ErrorRespone error = new ErrorRespone();
		error.setMessage(e.getMessage());
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ErrorRespone>(error, HttpStatus.OK);
	}

	@GetMapping("")
	@ResponseBody
	public List<Position> getAllPositions() {
		return positionService.getAll();
	}

	@PostMapping("/add/{name}")
	@ResponseStatus(HttpStatus.CREATED)
	public Position addPosition(@PathVariable("name") String name) throws PositionExistsException {
		return positionService.add(name);
	}

	@GetMapping("/count")
	@ResponseBody
	public Map<String, Integer> getEmployeesOnPositions() {
		return positionService.getPositionsWithEmployeesNumber();
	}
}
