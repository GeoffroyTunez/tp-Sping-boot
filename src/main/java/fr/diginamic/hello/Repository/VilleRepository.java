package fr.diginamic.hello.Repository;

import fr.diginamic.hello.model.Town;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends CrudRepository<Town, Long> {
    public Town findByName(String name);
    public List<Town> findByNameStartingWith(String name);
    public List<Town> findByNbInhabitantsGreaterThan(Integer nb);
    public List<Town> findByNbInhabitantsBetween(Integer nbMin, Integer nbMax);
    public List<Town> findByDepartmentCodeAndNbInhabitantsGreaterThan(String departmentCode, Integer nb);
    public List<Town> findByDepartmentCodeAndNbInhabitantsBetween(String departmentCode, Integer nbMin, Integer nbMax);
    public Page<Town> findByDepartmentCodeOrderByNbInhabitantsDesc(String departmentCode, Pageable pageable);
}
