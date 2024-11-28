package fr.diginamic.hello.dto;

public class VilleDto {

    private String nom;
    private Long codeVille;
    private Integer nbHabitants;
    private Long codeDepartement;
    private String departement;

    public VilleDto() {
    }

    public VilleDto(String nom, Long codeVille, Integer nbHabitants, Long codeDepartement, String departement) {
        this.nom = nom;
        this.codeVille = codeVille;
        this.nbHabitants = nbHabitants;
        this.codeDepartement = codeDepartement;
        this.departement = departement;
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
     * Getter for getcodeVille
     *
     * @return codeVille
     */

    public Long getCodeVille() {
        return codeVille;
    }

    /**
     * Setter for getcodeVille
     *
     * @return codeVille
     */

    public void setCodeVille(Long codeVille) {
        this.codeVille = codeVille;
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

    /**
     * Getter for getcodeDepartement
     *
     * @return codeDepartement
     */

    public Long getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Setter for getcodeDepartement
     *
     * @return codeDepartement
     */

    public void setCodeDepartement(Long codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    /**
     * Getter for getdepartement
     *
     * @return departement
     */

    public String getDepartement() {
        return departement;
    }

    /**
     * Setter for getdepartement
     *
     * @return departement
     */

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
