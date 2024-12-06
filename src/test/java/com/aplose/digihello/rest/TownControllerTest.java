package com.aplose.digihello.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.aplose.digihello.dto.TownDto;
import com.aplose.digihello.model.Department;
import com.aplose.digihello.model.Town;
import com.aplose.digihello.service.TownService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TownControllerTest {

	@MockitoBean
	TownService townService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void testGetAllTown() throws Exception{
		Department d = new Department();
		d.setName("Truc");
		d.setCode("99");
		Town town = new Town("Chose", 123445567, d);
		d.getTowns().add(town);
//		System.out.println(objectMapper.writeValueAsString(town));
		Mockito.when(townService.getAllTowns()).thenReturn(List.of(town));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/town")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].townName", is("Chose")))
		.andExpect(jsonPath("$[0].departmentName", is("Truc")))
		.andExpect(jsonPath("$[0].departmentCode", is("99")));
	}
	
}
