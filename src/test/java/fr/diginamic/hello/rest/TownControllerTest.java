package fr.diginamic.hello.rest;

import fr.diginamic.hello.Repository.DepartmentRepository;
import fr.diginamic.hello.Repository.VilleRepository;
import fr.diginamic.hello.model.Department;
import fr.diginamic.hello.model.Town;
import fr.diginamic.hello.service.TownService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TownControllerTest {


    @MockitoBean
    private TownService townService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllTown() throws Exception {
        Department department = new Department();
        department.setName("Department 1");
        department.setCode("99");

        Town town = new Town();
        town.setName("Town 1");
        town.setNbInhabitants(999);
        town.setDepartment(department);

        Mockito.when(townService.getAllTowns()).thenReturn(List.of(town));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/town"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Town 1"))
                .andExpect(jsonPath("$[0].nbInhabitants").value(999))
                .andExpect(jsonPath("$[0].department.name").value("Department 1"));
    }



    @Test
    void createTown() throws Exception {
        // Création d'un département
        Department department = new Department();
        department.setName("Department 1");
        department.setCode("99");

        // Création d'une ville
        Town town = new Town();
        town.setName("Town 1");
        town.setNbInhabitants(999);
        town.setDepartment(department);

        // Simulation du service (si nécessaire)
        Mockito.when(townService.addTown(town)).thenReturn(true);

        // Envoi de la requête POST au contrôleur
        this.mockMvc.perform(MockMvcRequestBuilders.post("/town")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Town 1"))
                .andExpect(jsonPath("$.nbInhabitants").value(999))
                .andExpect(jsonPath("$.department.name").value("Department 1"))
                .andExpect(jsonPath("$.department.code").value("99"));
    }


}