package fr.diginamic.hello.dto;

import fr.diginamic.hello.model.Department;
import fr.diginamic.hello.model.Town;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VilleMapper {

    // Convertir Town en VilleDto
    public VilleDto toDto(Town town){
        VilleDto dto = new VilleDto();
        dto.setNom(town.getName());
        dto.setCodeVille(town.getId());
        dto.setNbHabitants(town.getNbInhabitants());
        dto.setDepartement(town.getDepartment().getName());
        dto.setCodeDepartement(town.getDepartment().getId());
        return dto;
    }

    // Méthodes inverse
    public Town toTown(VilleDto dto){
        Town town = new Town();
        town.setName(dto.getNom());
        town.setNbInhabitants(dto.getNbHabitants());
        Department department = new Department();
        department.setName(dto.getDepartement());
        department.setId(dto.getCodeDepartement());
        town.setDepartment(department);
        return town;
    }
}