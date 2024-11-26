package fr.diginamic.hello.departements;

import fr.diginamic.hello.villes.Ville;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "departement")
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, message = "Le nom du département doit contenir au moins 2 caractères.")
    private String nom;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ville> villes = new ArrayList<>();

    // Constructeurs
    public Departement() {
    }

    public Departement(String nom) {
        this.nom = nom;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public void addVille(Ville ville) {
        villes.add(ville);
        ville.setDepartement(this); // Associer le département à la ville
    }

    public void removeVille(Ville ville) {
        villes.remove(ville);
        ville.setDepartement(null); // Dissocier le département de la ville
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", villes=" + villes +
                '}';
    }
}
