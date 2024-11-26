package fr.diginamic.hello.departements;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartementDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Récupérer tous les départements
    public List<Departement> getAllDepartements() {
        TypedQuery<Departement> query = entityManager.createQuery("SELECT d FROM departement d", Departement.class);
        return query.getResultList();
    }

    // Récupérer un département par son ID
    public Departement getDepartementById(Long id) {
        return entityManager.find(Departement.class, id);
    }

    // Ajouter un nouveau département
    public void addDepartement(Departement departement) {
        entityManager.persist(departement);
    }

    // Mettre à jour un département
    public Departement updateDepartement(Departement departement) {
        return entityManager.merge(departement);
    }

    // Supprimer un département
    public void deleteDepartement(Long id) {
        Departement departement = getDepartementById(id);
        if (departement != null) {
            entityManager.remove(departement);
        }
    }
}
