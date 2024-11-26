package fr.diginamic.hello.villes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VilleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Récupérer toutes les villes
    public List<Ville> getAllVilles() {
        TypedQuery<Ville> query = entityManager.createQuery("SELECT v FROM ville v", Ville.class);
        return query.getResultList();
    }

    // Récupérer une ville par son ID
    public Ville getVilleById(Long id) {
        return entityManager.find(Ville.class, id);
    }

    // Récupérer une ville par son nom
    public Ville getVilleByName(String nom) {
        TypedQuery<Ville> query = entityManager.createQuery("SELECT v FROM ville v WHERE v.nom = :nom", Ville.class);
        query.setParameter("nom", nom);
        return query.getResultStream().findFirst().orElse(null);
    }

    // Ajouter une nouvelle ville
    public void addVille(Ville ville) {
        entityManager.persist(ville);
    }

    // Mettre à jour une ville
    public Ville updateVille(Ville ville) {
        return entityManager.merge(ville);
    }

    // Supprimer une ville
    public void deleteVille(Long id) {
        Ville ville = getVilleById(id);
        if (ville != null) {
            entityManager.remove(ville);
        }
    }
}
