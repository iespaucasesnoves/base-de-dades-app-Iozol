package com.example.cristian.winedb;

public class Denominacio {
    private long iddenominacio;
    private String nomdenominacio;

    public Denominacio() {
        iddenominacio = -1;
    }

    public long getIddenominacio() {
        return iddenominacio;
    }

    public void setIddenominacio(long iddenominacio) {
        this.iddenominacio = iddenominacio;
    }

    public String getNomdenominacio() {
        return nomdenominacio;
    }

    public void setNomdenominacio(String nomdenominacio) {
        this.nomdenominacio = nomdenominacio;
    }
}
