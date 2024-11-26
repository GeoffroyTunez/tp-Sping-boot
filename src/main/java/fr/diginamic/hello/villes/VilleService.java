package fr.diginamic.hello.villes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    private VilleDAO villeDao;

    // Extrait toutes les villes
    public List<Ville> extractVilles() {
        return villeDao.getAllVilles();
    }

    // Extrait une ville par son ID
    public Ville extractVille(Long idVille) {
        return villeDao.getVilleById(idVille);
    }

    // Extrait une ville par son nom
    public Ville extractVille(String nom) {
        return villeDao.getVilleByName(nom);
    }

    // Insère une nouvelle ville en base
    public List<Ville> insertVille(Ville ville) {
        if (villeDao.getVilleByName(ville.getNom()) != null) {
            throw new IllegalArgumentException("Une ville avec ce nom existe déjà !");
        }
        villeDao.addVille(ville);
        return extractVilles();
    }

    // Modifie une ville existante
    public List<Ville> modifierVille(Long idVille, Ville villeModifiee) {
        Ville villeExistante = villeDao.getVilleById(idVille);
        if (villeExistante == null) {
            throw new IllegalArgumentException("La ville avec l'ID " + idVille + " n'existe pas !");
        }

        villeExistante.setNom(villeModifiee.getNom());
        villeExistante.setNbHabitants(villeModifiee.getNbHabitants());
        villeDao.updateVille(villeExistante);

        return extractVilles();
    }

    // Supprime une ville
    public List<Ville> supprimerVille(Long idVille) {
        Ville villeExistante = villeDao.getVilleById(idVille);
        if (villeExistante == null) {
            throw new IllegalArgumentException("La ville avec l'ID " + idVille + " n'existe pas !");
        }

        villeDao.deleteVille(idVille);
        return extractVilles();
    }
}
