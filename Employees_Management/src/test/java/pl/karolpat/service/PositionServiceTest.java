package pl.karolpat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.karolpat.entity.Position;
import pl.karolpat.exception.PositionNotFoundException;
import pl.karolpat.repository.PositionRepo;
import pl.karolpat.serviceImpl.PositionServiceImpl;

public class PositionServiceTest {
	
	private PositionRepo positionRepo;
	private PositionService positionService;
	private Position position;
	private Position position2;
	private List<Position> list;

	@Before
	public void setUp() throws Exception {
		positionRepo=mock(PositionRepo.class);
		positionService=new PositionServiceImpl(positionRepo);
		
		position = new Position("Director",1);
		position2=new Position("Killer",2);
		list=Arrays.asList(position,position2);
	}

	@Test
	public final void testFindOneById() {
		
		when(positionRepo.findOne(1l)).thenReturn(position);
		
		Position result = positionService.findOneById(1);
		
		assertEquals("Director", result.getName());
	}
	
	@Test
	public final void testFindOneById_throw() {
		
		when(positionRepo.findOne(2l)).thenReturn(null);
		assertThatThrownBy(() -> positionService.findOneById(2)).isInstanceOf(PositionNotFoundException.class);
	}

	@Test
	public final void testGetAll() {
		
		when(positionRepo.findAll()).thenReturn(list);
		
		List<Position> result = positionService.getAll();
		
		assertThat(result).containsExactly(position,position2);
	}

}
