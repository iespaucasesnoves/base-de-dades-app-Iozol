package com.example.cristian.winedb;

public class Bodega {
    private long idbodega;
    private String nombodega;

    public Bodega() {
        this.idbodega = -1;
    }

    public long getIdbodega() {
        return idbodega;
    }

    public String getNombodega() {
        return nombodega;
    }

    public void setIdbodega(long idbodega) {
        this.idbodega = idbodega;
    }

    public void setNombodega(String nombodega) {
        this.nombodega = nombodega;
    }

}
