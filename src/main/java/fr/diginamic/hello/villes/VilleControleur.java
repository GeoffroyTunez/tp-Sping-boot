package fr.diginamic.hello.villes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    // Liste pour stocker les villes
    private final List<Ville> villes = new ArrayList<>();

    // Constructeur pour initialiser quelques données
    public VilleControleur() {
        villes.add(new Ville("Paris", 2148000));
        villes.add(new Ville("Lyon", 515695));
        villes.add(new Ville("Marseille", 861635));
    }

    // Méthode GET : Récupérer la liste des villes
    @GetMapping
    public List<Ville> listVilles() {
        return villes;
    }

    // Méthode POST : Ajouter une ville
    @PostMapping("/add")
    public ResponseEntity<String> addVille(@RequestParam String nom, @RequestParam Integer nbHabitants) {
        // Vérification si la ville existe déjà
        for (Ville ville : villes) {
            if (ville.getNom().equalsIgnoreCase(nom)) {
                return new ResponseEntity<>("La ville existe déjà", HttpStatus.BAD_REQUEST);
            }
        }

        // Si elle n'existe pas, on l'ajoute
        villes.add(new Ville(nom, nbHabitants));
        return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.OK);
    }
}
