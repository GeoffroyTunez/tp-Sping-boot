package fr.diginamic.hello.villes;

import fr.diginamic.hello.departements.Departement;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "ville")
public class Ville {

    private static Long idCounter = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @NotNull
    @Size(min = 2)
    private String nom;

    @Min(1)
    private Integer nbHabitants;

    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    // Constructeurs
    public Ville(String nom, Integer nbHabitants) {
        this.id = idCounter;
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    public Ville() {
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

    public Integer getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(Integer nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ville{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", nbHabitants=").append(nbHabitants);
        sb.append(", departement=").append(departement != null ? departement.getNom() : "null");
        sb.append('}');
        return sb.toString();
    }
}
