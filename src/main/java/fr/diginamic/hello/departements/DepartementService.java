package fr.diginamic.hello.departements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementDAO departementDao;

    // Récupérer tous les départements
    public List<Departement> extractDepartements() {
        return departementDao.getAllDepartements();
    }

    // Récupérer un département par ID
    public Departement extractDepartement(Long id) {
        return departementDao.getDepartementById(id);
    }

    // Ajouter un nouveau département
    public List<Departement> insertDepartement(Departement departement) {
        departementDao.addDepartement(departement);
        return extractDepartements();
    }

    // Modifier un département existant
    public List<Departement> modifierDepartement(Long id, Departement departementModifie) {
        Departement departementExistant = departementDao.getDepartementById(id);
        if (departementExistant == null) {
            throw new IllegalArgumentException("Le département avec l'ID " + id + " n'existe pas !");
        }

        departementExistant.setNom(departementModifie.getNom());
        departementDao.updateDepartement(departementExistant);

        return extractDepartements();
    }

    // Supprimer un département
    public List<Departement> supprimerDepartement(Long id) {
        departementDao.deleteDepartement(id);
        return extractDepartements();
    }
}
