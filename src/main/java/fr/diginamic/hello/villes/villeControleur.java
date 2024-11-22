package fr.diginamic.hello.villes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // Annotation correcte pour indiquer un contrôleur REST
@RequestMapping("/villes")
public class villeControleur {

    @GetMapping
    public List<Ville> listVilles() {
        // Exemple de données
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", 2148000));
        villes.add(new Ville("Lyon", 515695));
        villes.add(new Ville("Marseille", 861635));

        return villes; // Retourne la liste
    }
}
