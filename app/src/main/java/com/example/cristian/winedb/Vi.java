package com.example.cristian.winedb;

public class Vi {
    private long id;
    private String nomVi;
    private String anada;
    private String tipus;
    private String lloc;
    private String graduacio;
    private String data;
    private String comentari;
    private long idBodega;
    private long idDenominacio;
    private double preu;
    private String valOlfativa;
    private String valGustativa;
    private String valVisual;
    private int nota;
    private String foto;

    public Vi() {
        this.id = -1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomVi() {
        return nomVi;
    }

    public void setNomVi(String nomVi) {
        this.nomVi = nomVi;
    }

    public String getAnada() {
        return anada;
    }

    public void setAnada(String anada) {
        this.anada = anada;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public String getGraduacio() {
        return graduacio;
    }

    public void setGraduacio(String graduacio) {
        this.graduacio = graduacio;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getComentari() {
        return comentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    public long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(long idBodega) {
        this.idBodega = idBodega;
    }

    public long getIdDenominacio() {
        return idDenominacio;
    }

    public void setIdDenominacio(long idDenominacio) {
        this.idDenominacio = idDenominacio;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public String getValOlfativa() {
        return valOlfativa;
    }

    public void setValOlfativa(String valOlfativa) {
        this.valOlfativa = valOlfativa;
    }

    public String getValGustativa() {
        return valGustativa;
    }

    public void setValGustativa(String valGustativa) {
        this.valGustativa = valGustativa;
    }

    public String getValVisual() {
        return valVisual;
    }

    public void setValVisual(String valVisual) {
        this.valVisual = valVisual;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
