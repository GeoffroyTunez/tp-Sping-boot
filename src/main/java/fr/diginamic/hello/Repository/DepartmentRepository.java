package fr.diginamic.hello.Repository;

import fr.diginamic.hello.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByCode(String code);
    public void deleteByCode(String code);
}

