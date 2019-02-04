package com.example.cristian.winedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataSourceVi {

    private SQLiteDatabase database;
    private HelperVi dbAjuda; //CLASSE AJUDA

    private String[] allColumnsVi = {HelperVi.COLUMN_ID, HelperVi.COLUMN_NOMVI, HelperVi.COLUMN_ANADA,
            HelperVi.COLUMN_LLOC, HelperVi.COLUMN_GRADUACIO, HelperVi.COLUMN_DATA,
            HelperVi.COLUMN_COMENTARI, HelperVi.COLUMN_IDBODEGA,
            HelperVi.COLUMN_IDDENOMINACIO, HelperVi.COLUMN_PREU,
            HelperVi.COLUMN_VALOLFATIVA, HelperVi.COLUMN_VALGUSTATIVA,
            HelperVi.COLUMN_VALVISUAL, HelperVi.COLUMN_NOTA, HelperVi.COLUMN_FOTO,
            HelperVi.COLUMN_TIPUS};

    private String[] allColumnsBodega = {
            HelperVi.COLUMN__IDBODEGA,
            HelperVi.COLUMN_NOMBODEGA
    };

    private String[] allColumnsDenominacio = {
            HelperVi.COLUMN__IDDENOMINACIO,
            HelperVi.COLUMN_NOMDENOMINACIO
    };

    private String[] allColumnsTipus = {
            HelperVi.COLUMN__TIPUS,
    };

    public DataSourceVi(Context context) { //CONSTRUCTOR
        dbAjuda = new HelperVi(context);
    }

    public void open() throws SQLException {
        database = dbAjuda.getWritableDatabase();
    }

    public void close() {
        dbAjuda.close();
    }

    public Vi createVi(Vi vi) {
        // insert d'un nou vi
        ContentValues values = new ContentValues();
        values.put(HelperVi.COLUMN_NOMVI, vi.getNomVi());
        values.put(HelperVi.COLUMN_ANADA, vi.getAnada());
        values.put(HelperVi.COLUMN_TIPUS, vi.getTipus());
        values.put(HelperVi.COLUMN_LLOC, vi.getLloc());
        values.put(HelperVi.COLUMN_GRADUACIO, vi.getGraduacio());
        values.put(HelperVi.COLUMN_DATA, String.valueOf(vi.getData()));
        values.put(HelperVi.COLUMN_COMENTARI, vi.getComentari());
        values.put(HelperVi.COLUMN_IDBODEGA, vi.getIdBodega());
        values.put(HelperVi.COLUMN_IDDENOMINACIO, vi.getIdDenominacio());
        values.put(HelperVi.COLUMN_PREU, vi.getPreu());
        values.put(HelperVi.COLUMN_VALOLFATIVA, vi.getValOlfativa());
        values.put(HelperVi.COLUMN_VALGUSTATIVA, vi.getValGustativa());
        values.put(HelperVi.COLUMN_VALVISUAL, vi.getValVisual());
        values.put(HelperVi.COLUMN_NOTA, vi.getNota());
        values.put(HelperVi.COLUMN_FOTO, vi.getFoto());
        long insertId = database.insert(HelperVi.TABLE_VI, null, values);
        vi.setId(insertId);
        return vi;
    }

    public boolean updateVi(Vi vi) {
        // update vi
        ContentValues values = new ContentValues();
        long id = vi.getId();
        values.put(HelperVi.COLUMN_NOMVI, vi.getNomVi());
        values.put(HelperVi.COLUMN_ANADA, vi.getAnada());
        values.put(HelperVi.COLUMN_LLOC, vi.getLloc());
        values.put(HelperVi.COLUMN_TIPUS, vi.getTipus());
        values.put(HelperVi.COLUMN_GRADUACIO, vi.getGraduacio());
        values.put(HelperVi.COLUMN_DATA, String.valueOf(vi.getData()));
        values.put(HelperVi.COLUMN_COMENTARI, vi.getComentari());
        values.put(HelperVi.COLUMN_IDBODEGA, vi.getIdBodega());
        values.put(HelperVi.COLUMN_IDDENOMINACIO, vi.getIdDenominacio());
        values.put(HelperVi.COLUMN_PREU, vi.getPreu());
        values.put(HelperVi.COLUMN_VALOLFATIVA, vi.getValOlfativa());
        values.put(HelperVi.COLUMN_VALGUSTATIVA, vi.getValGustativa());
        values.put(HelperVi.COLUMN_VALVISUAL, vi.getValVisual());
        values.put(HelperVi.COLUMN_NOTA, vi.getNota());
        values.put(HelperVi.COLUMN_FOTO, vi.getFoto());
        return database.update(HelperVi.TABLE_VI, values, HelperVi.COLUMN_ID + "=" + id, null) > 0;
    }

    public void deleteVi(Vi vi) {
        long id = vi.getId();
        database.delete(HelperVi.TABLE_VI, HelperVi.COLUMN_ID + " = " + id, null);
    }

    public Vi getVi(long id) {
        Vi vi;
        Cursor cursor = database.query(HelperVi.TABLE_VI,
                allColumnsVi, HelperVi.COLUMN_ID + " = " + id, null,
                null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            vi = cursorToVi(cursor);
        } else {
            vi = new Vi();
        } // id=-1 no trobat
        cursor.close();
        return vi;
    }

    public Bodega getBodega(long id) {
        Bodega bodega;

        try{
            Cursor cursor = database.query(HelperVi.TABLE_BODEGA,
                    allColumnsBodega, HelperVi.COLUMN_IDBODEGA + " = " + id, null,
                    null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                bodega = cursorToBodega(cursor);
            } else {
                bodega = new Bodega();
            } // id=-1 no trobat
            cursor.close();
        }catch(Exception e){
            bodega = new Bodega();
        }

        return bodega;
    }

    public Long findInsertBodegaPerNom(String nomBodega){
        Bodega bodega;

            Cursor cursor = database.query(HelperVi.TABLE_BODEGA,
                    allColumnsBodega, HelperVi.COLUMN_NOMBODEGA + " = '" + nomBodega + "'", null,
                    null, null, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                bodega = cursorToBodega(cursor);
            } else {
                bodega = new Bodega();
                bodega.setNombodega(nomBodega);
            } // id=-1 no trobat
            cursor.close();

            return bodega.getIdbodega();
    }

    public Denominacio getDenominacio(long id) {
        Denominacio denominacio;
        try{
        Cursor cursor = database.query(HelperVi.TABLE_DENOMINACIO,
                allColumnsDenominacio, HelperVi.COLUMN_IDDENOMINACIO + " = " + id, null,
                null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            denominacio = cursorToDenominacio(cursor);
        } else {
            denominacio = new Denominacio();
        } // id=-1 no trobat
        cursor.close();
        }catch(Exception e){
            denominacio = new Denominacio();
        }
        return denominacio;
    }

    public Long findInsertgetDenominacioPerNom(String nomDenominacio){
        Denominacio denominacio;

        Cursor cursor = database.query(HelperVi.TABLE_DENOMINACIO,
                allColumnsDenominacio, HelperVi.COLUMN_NOMDENOMINACIO + " = " + nomDenominacio, null,
                null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            denominacio = cursorToDenominacio(cursor);
        } else {
            denominacio = new Denominacio();
            denominacio.setNomdenominacio(nomDenominacio);
        }
        cursor.close();

        return denominacio.getIddenominacio();
    }

    public List<Vi> getAllVi() {
        List<Vi> vins = new ArrayList<Vi>();
        Cursor cursor = database.query(HelperVi.TABLE_VI, allColumnsVi, null, null, null, null,
                HelperVi.COLUMN_DATA + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Vi vi = cursorToVi(cursor);
            vins.add(vi);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return vins;
    }

    public List<String> getLlistaBodegues() {
        List<String> bodegas = new ArrayList<String>();
        Cursor cursor = database.query(HelperVi.TABLE_BODEGA,
                allColumnsBodega, null, null,
                null, null, HelperVi.COLUMN_NOMBODEGA + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Bodega bodega = cursorToBodega(cursor);
            bodegas.add(bodega.getNombodega());
            cursor.moveToNext();
        }
        cursor.close();
        return bodegas;
    }

    public List<String> getLlistaDenominacio(){
        List<String> denominacions = new ArrayList<>();
        Cursor cursor = database.query(HelperVi.TABLE_DENOMINACIO,
                allColumnsDenominacio, null, null,
                null, null, HelperVi.COLUMN_NOMDENOMINACIO + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Denominacio denominacio = cursorToDenominacio(cursor);
            denominacions.add(denominacio.getNomdenominacio());
            cursor.moveToNext();
        }
        cursor.close();
        return denominacions;
    }

    public List<String> getAllTipus() {
        List<String> tipus = new ArrayList<>();
        Cursor cursor = database.query(HelperVi.TABLE_TIPUS, allColumnsTipus, null, null, null, null,
                HelperVi.COLUMN__TIPUS + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tipus tipo = cursorToTipus(cursor);
            tipus.add(tipo.getTipus());
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return tipus;
    }

    private Bodega cursorToBodega(Cursor cursor) {
        Bodega b = new Bodega();
        b.setIdbodega(cursor.getLong(0));
        b.setNombodega(cursor.getString(1));
        return b;
    }

    private Denominacio cursorToDenominacio(Cursor cursor) {
        Denominacio d= new Denominacio();
        d.setIddenominacio(cursor.getLong(0));
        d.setNomdenominacio(cursor.getString(1));
        return d;
    }

    private Tipus cursorToTipus(Cursor cursor) {
        Tipus t= new Tipus();
        t.setTipus(cursor.getString(0));
        return t;
    }

    private Vi cursorToVi(Cursor cursor) {
        Vi v = new Vi();
        v.setId(cursor.getLong(0));
        v.setNomVi(cursor.getString(1));
        v.setAnada(cursor.getString(2));
        v.setLloc(cursor.getString(3));
        v.setGraduacio(cursor.getString(4));
        v.setData(cursor.getString(5));
        v.setComentari(cursor.getString(6));
        v.setIdBodega(cursor.getLong(7));
        v.setIdBodega(cursor.getLong(8));
        v.setPreu(cursor.getFloat(9));
        v.setValOlfativa(cursor.getString(10));
        v.setValGustativa(cursor.getString(11));
        v.setValVisual(cursor.getString(12));
        v.setNota(cursor.getInt(13));
        v.setFoto(cursor.getString(14));
        return v;
    }

}
