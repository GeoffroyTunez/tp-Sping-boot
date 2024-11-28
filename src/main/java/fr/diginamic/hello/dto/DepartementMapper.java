package fr.diginamic.hello.dto;

import fr.diginamic.hello.model.Department;
import fr.diginamic.hello.model.Town;
import org.springframework.stereotype.Component;

@Component
public class DepartementMapper {


    // Convertir Department en DepartementDto
    public DepartementDto toDto(Department department) {
        DepartementDto departementDto = new DepartementDto();
        departementDto.setId(department.getId());
        departementDto.setNom(department.getName());
        departementDto.setCodeDepartement(department.getCode());
        Integer habitnatants =0;
        for (Town town : department.getTowns()) {
            habitnatants += town.getNbInhabitants();
        }
        departementDto.setNbHabitants(habitnatants);
        return departementDto;
    }

    // Méthodes inverse
    public Department toEntity(DepartementDto departementDto) {
        Department department = new Department();
        department.setId(departementDto.getId());
        department.setName(departementDto.getNom());
        department.setCode(departementDto.getCodeDepartement());
        return department;
    }

}
