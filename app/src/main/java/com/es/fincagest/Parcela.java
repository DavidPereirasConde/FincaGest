package com.es.fincagest;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Parcela implements Serializable {

    //------ Se define las Variables
    private String idParcela;
    private String nombreParcela;
    private int hectareas;
    private Date ultimoTratamienetoQuimico;
    private Date proximoTratamientoQuimico;
    private String fruta;
    private String variedad;
    private String nombreTratamiento;
    private int diasTratarQuimicamente;

    //-------Constructor vacío.
    public Parcela() {
   }
    //-------  Constructor con todos los valores
    public Parcela(String idParcela, String nombreParcela, int hectareas, Date ultimoTratamienetoQuimico, Date proximoTratamientoQuimico, String fruta, String variedad, String nombreTratamiento, int diasTratarQuimicamente) {
        this.idParcela = idParcela;
        this.nombreParcela = nombreParcela;
        this.hectareas = hectareas;
        this.ultimoTratamienetoQuimico = ultimoTratamienetoQuimico;
        this.proximoTratamientoQuimico = proximoTratamientoQuimico;
        this.fruta = fruta;
        this.variedad = variedad;
        this.nombreTratamiento = nombreTratamiento;
        this.diasTratarQuimicamente = diasTratarQuimicamente;
    }

    //------ GETTERS & SETTERS-----
    public String getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(String idParcela) {
        this.idParcela = idParcela;
    }

    public String getNombreParcela() {
        return nombreParcela;
    }

    public void setNombreParcela(String nombreParcela) {
        this.nombreParcela = nombreParcela;
    }

    public int getHectareas() {
        return hectareas;
    }

    public void setHectareas(int hectareas) {
        this.hectareas = hectareas;
    }

    public Date getUltimoTratamienetoQuimico() {
        return ultimoTratamienetoQuimico;
    }

    public void setUltimoTratamienetoQuimico(Date ultimoTratamienetoQuimico) {
        this.ultimoTratamienetoQuimico = ultimoTratamienetoQuimico;
    }

    public Date getProximoTratamientoQuimico() {
        return proximoTratamientoQuimico;
    }

    public void setProximoTratamientoQuimico(Date proximoTratamientoQuimico) {
        this.proximoTratamientoQuimico = proximoTratamientoQuimico;
    }

    public String getFruta() {
        return fruta;
    }

    public void setFruta(String fruta) {
        this.fruta = fruta;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getNombreTratamiento() {
        return nombreTratamiento;
    }

    public void setNombreTratamiento(String nombreTratamiento) {
        this.nombreTratamiento = nombreTratamiento;
    }

    public int getDiasTratarQuimicamente() {
        return diasTratarQuimicamente;
    }

    public void setDiasTratarQuimicamente(int diasTratarQuimicamente) {
        this.diasTratarQuimicamente = diasTratarQuimicamente;
    }

    //------El metodo toString() permite mostrar el valor retornado en la lista del fichero listaParcelas.java
    @Override
    public String toString(){
        return nombreParcela;
    }

}
