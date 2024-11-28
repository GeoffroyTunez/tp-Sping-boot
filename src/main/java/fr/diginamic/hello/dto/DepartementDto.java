package fr.diginamic.hello.dto;

public class DepartementDto {

    private Long id;
    private String codeDepartement;
    private String nom;
    private Integer nbHabitants;

    public DepartementDto() {
    }

    public DepartementDto(Long id,String codeDepartement, String nom, Integer nbHabitants) {
        this.id = id;
        this.codeDepartement = codeDepartement;
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    /**
     * Getter for getid
     *
     * @return id
     */

    public Long getId() {
        return id;
    }

    /**
     * Setter for getid
     *
     * @return id
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for getcodeDepartement
     *
     * @return codeDepartement
     */

    public String getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Setter for getcodeDepartement
     *
     * @return codeDepartement
     */

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    /**
     * Getter for getnom
     *
     * @return nom
     */

    public String getNom() {
        return nom;
    }

    /**
     * Setter for getnom
     *
     * @return nom
     */

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter for getnbHabitants
     *
     * @return nbHabitants
     */

    public Integer getNbHabitants() {
        return nbHabitants;
    }

    /**
     * Setter for getnbHabitants
     *
     * @return nbHabitants
     */

    public void setNbHabitants(Integer nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}
