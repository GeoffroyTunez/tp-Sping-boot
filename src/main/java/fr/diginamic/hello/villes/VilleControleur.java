package fr.diginamic.hello.villes;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;
    @Autowired
    private RestTemplateAutoConfiguration restTemplateAutoConfiguration;

    // Méthode GET : Récupérer la liste des villes
    @GetMapping
    public List<Ville> listVilles() {
        return villeService.extractVilles();
    }

    @GetMapping("/{id}")
    public Ville getVille(@PathVariable Long id) {
        return villeService.extractVille(id);
    }

    @GetMapping("/{nom}")
    public Ville getVille(@PathVariable String nom) {
        return villeService.extractVille(nom);
    }


    @PutMapping("/edit")
    public ResponseEntity<String> editVille(@Valid @RequestParam Ville ville, Long id, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>("Problème de validation des contraintes !",HttpStatus.BAD_REQUEST);
        }
        return (ResponseEntity<String>) villeService.modifierVille(id, ville);
    }

    @DeleteMapping("/del")
    public ResponseEntity<String> deleteVille(@RequestParam Long id) {
        return (ResponseEntity<String>) villeService.supprimerVille(id);
    }

    // Méthode POST : Ajouter une ville
    @PostMapping("/add")
    public ResponseEntity<String> addVille(@Valid @RequestParam Ville ville,BindingResult result){

        // Vérifie si des erreurs de validation existent
        if (result.hasErrors()) {
            return new ResponseEntity<String>("Problème de validation des contraintes !",HttpStatus.BAD_REQUEST);
        }
        return (ResponseEntity<String>) villeService.insertVille(ville);
    }


}
