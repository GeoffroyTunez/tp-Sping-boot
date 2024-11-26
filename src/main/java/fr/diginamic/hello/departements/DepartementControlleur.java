package fr.diginamic.hello.departements;

import fr.diginamic.hello.departements.Departement;
import fr.diginamic.hello.villes.Ville;
import fr.diginamic.hello.villes.VilleDAO;
import fr.diginamic.hello.villes.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departements")
public class DepartementControlleur {


    private final DepartementDAO departementDAO;
    private final VilleDAO villeDAO;
    private final VilleService villeService;

    public DepartementControlleur(DepartementDAO departementDAO, VilleDAO villeDAO, VilleService villeService) {
        this.departementDAO = departementDAO;
        this.villeDAO = villeDAO;
        this.villeService = villeService;
    }

    // CRUD : Ajouter un département
    @PostMapping
    public ResponseEntity<String> addDepartement(@RequestBody Departement departement) {
                departementDAO.addDepartement(departement);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    // CRUD : Lire un département par son ID
    @GetMapping("/{id}")
    public Departement getDepartement(@PathVariable Long id) {
        return departementDAO.getDepartementById(id);
    }

    // CRUD : Modifier un département
    @PutMapping("/{id}")
    public Departement updateDepartement(@PathVariable Long id, @RequestBody Departement departement) {
        return departementDAO.getDepartementById(id);
    }

    // CRUD : Supprimer un département
    @DeleteMapping("/{id}")
    public void deleteDepartement(@PathVariable Long id) {
        departementDAO.deleteDepartement(id);
    }

    // Lister les n plus grandes villes d’un département
    @GetMapping("/{id}/villes/top/{n}")
    public List<Ville> getTopNVilles(@PathVariable Long id, @PathVariable int n) {
        // Récupérer les villes du département correspondant à l'id
        List<Ville> villes = departementDAO.getDepartementById(id).getVilles();

        // Trier les villes par population (ou autre critère que tu choisis)
        List<Ville> villesSelectionner = villes.stream()
                .sorted(Comparator.comparingLong(Ville::getNbHabitants).reversed())  // Trier par population décroissante
                .limit(n)  // Prendre les n premières villes
                .collect(Collectors.toList());

        // Retourner les n plus grandes villes
        return villesSelectionner;
    }


    // Lister les villes ayant une population comprise entre min et max
    @GetMapping("/villes/population")
    public List<Ville> getVillesByPopulation(@RequestParam long min, @RequestParam long max) {
        // Supposons que tu utilises un service ou une méthode pour récupérer toutes les villes d'un département
        return departementDAO.getAllDepartements().stream()  // Récupérer tous les départements
                .flatMap(departement -> departement.getVilles().stream())  // Récupérer toutes les villes de chaque département
                .filter(ville -> ville.getNbHabitants() >= min && ville.getNbHabitants() <= max)  // Filtrer par population
                .collect(Collectors.toList());  // Collecter dans une liste
    }

}
