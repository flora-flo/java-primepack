/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Timestamp;

/**
 *
 * @author Nessim Melliti
 */
public class Operation {

    private int id;
    private Maladie maladie;
    private int animaleId;

    private Timestamp dateOperation;
    private String typeOperation;
    private String nomMedecin;
    private float coutOperation;
    private String noteOperation;

    public Operation() {
    }

    public Operation(int id, Maladie maladie, int animaleId, Timestamp dateOperation, String typeOperation, String nomMedecin, float coutOperation, String noteOperation) {
        this.id = id;
        this.maladie = maladie;
        this.animaleId = animaleId;
        this.dateOperation = dateOperation;
        this.typeOperation = typeOperation;
        this.nomMedecin = nomMedecin;
        this.coutOperation = coutOperation;
        this.noteOperation = noteOperation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }

    public int getAnimaleId() {
        return animaleId;
    }

    public void setAnimaleId(int animaleId) {
        this.animaleId = animaleId;
    }

    public Timestamp getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Timestamp dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    public float getCoutOperation() {
        return coutOperation;
    }

    public void setCoutOperation(float coutOperation) {
        this.coutOperation = coutOperation;
    }

    public String getNoteOperation() {
        return noteOperation;
    }

    public void setNoteOperation(String noteOperation) {
        this.noteOperation = noteOperation;
    }

    @Override
    public String toString() {
        return "Operation{" + "id=" + id + ", maladie=" + maladie + ", animaleId=" + animaleId + ", dateOperation=" + dateOperation + ", typeOperation=" + typeOperation + ", nomMedecin=" + nomMedecin + ", coutOperation=" + coutOperation + ", noteOperation=" + noteOperation + '}';
    }

}
