package com.bootcamp.tp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Table(name = "tp_livrable")
@ApiModel(value = "Livrable",
        description = "représentation d'une resource REST Livrable"
)
public class Livrable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id du livrable", required = true)
    private int idLivrable;

    @Column(nullable = false, length = 45)
    @ApiModelProperty(value = "reference du livrable", required = true)
    private String reference;

    @Column(nullable = false, length = 45)
    @ApiModelProperty(value = "description du livrable", required = true)
    private String description;

    @Column(nullable = false, length = 45)
    @ApiModelProperty(value = "objectifs du livrable", required = true)
    private List<Object> objectifs;

    @Column(nullable = false, length = 45)
    @ApiModelProperty(value = "lieux du livrable", required = true)
    private List<Object> lieux;

    @Column(nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "etat du livrable", required = true)
    private Etat etat;

    public Livrable() {

    }

    public Livrable(int idLivrable, String reference, String description, List<Object> objectifs, List<Object> lieux,
            Etat etat) {
        super();
        this.idLivrable = idLivrable;
        this.reference = reference;
        this.description = description;
        this.objectifs = objectifs;
        this.lieux = lieux;
        this.etat = etat;
    }

    public int getIdLivrable() {
        return idLivrable;
    }

    public void setIdLivrable(int idLivrable) {
        this.idLivrable = idLivrable;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(List<Object> objectifs) {
        this.objectifs = objectifs;
    }

    public List<Object> getLieux() {
        return lieux;
    }

    public void setLieux(List<Object> lieux) {
        this.lieux = lieux;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

}
