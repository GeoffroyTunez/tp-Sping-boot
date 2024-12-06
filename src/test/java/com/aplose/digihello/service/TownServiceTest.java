package com.aplose.digihello.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.aplose.digihello.exception.DigiHelloException;
import com.aplose.digihello.model.Department;
import com.aplose.digihello.model.Town;
import com.aplose.digihello.repository.TownRepository;

@SpringBootTest
@ActiveProfiles("test")
public class TownServiceTest {

	@Autowired
	private TownService townService;

	@MockitoBean
	TownRepository townRepository;

	@Test
	public void testGetTown() {		
		Department d = new Department();
		d.setName("Truc");
		d.setCode("99");
		Town town = new Town("Chose", 123445567, d);
		Mockito.when(townRepository.findAll()).thenReturn(List.of(town));
		Iterable<Town> towns = townService.getAllTowns();
		try {
			Town t = townService.getTown(13324L);
			assertNotNull(t);
			assertEquals(t.getName(), "Nice");
		} catch (Exception e) {
			assert(false);
		}		
	}
	
	@Test
	public void testVilleNotFound() {
		try {
			Town t = townService.getTown(9999999L);
			assertNotNull(t);
		} catch (Exception e) {
			assertThatExceptionOfType(DigiHelloException.class);
			assertEquals(e.getMessage(), "Ville non trouvée pour id = 9999999");
		}		
	}
	
	
	
}
