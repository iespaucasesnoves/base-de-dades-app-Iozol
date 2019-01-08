package com.example.cristian.winedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HelperVi extends SQLiteOpenHelper {
    public static final String TABLE_VI = "vi";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMVI = "nomvi";
    public static final String COLUMN_ANADA = "anada";
    public static final String COLUMN_TIPUS = "tipus";
    public static final String COLUMN_LLOC = "lloc";
    public static final String COLUMN_GRADUACIO = "graduacio";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_COMENTARI = "comentari";
    public static final String COLUMN_IDBODEGA = "idbodega";
    public static final String COLUMN_IDDENOMINACIO = "iddenominacio";
    public static final String COLUMN_PREU = "preu";
    public static final String COLUMN_VALOLFATIVA = "valolfativa";
    public static final String COLUMN_VALGUSTATIVA = "valgustativa";
    public static final String COLUMN_VALVISUAL = "valvisual";
    public static final String COLUMN_NOTA = "nota";
    public static final String COLUMN_FOTO = "foto";
    public static final String TABLE_BODEGA = "bodega";
    public static final String COLUMN__IDBODEGA = "idbodega";
    public static final String COLUMN_NOMBODEGA = "nombodega";
    public static final String TABLE_DENOMINACIO = "denominacio";
    public static final String COLUMN__IDDENOMINACIO = "iddenominacio";
    public static final String COLUMN_NOMDENOMINACIO = "nomdenominacio";
    public static final String TABLE_TIPUS = "tipus";
    public static final String COLUMN__TIPUS = "tipus";
    private static final String DATABASE_NAME = "wineapp";
    private static final int DATABASE_VERSION = 1; // Controla la versió de la base de dades

    // Setències de creació de la base de dades
    private static final String DATABASE_CREATE_VI = "create table "
            + TABLE_VI + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NOMVI + " text not null, "
            + COLUMN_ANADA + " text, "
            + COLUMN_TIPUS + " text, "
            + COLUMN_LLOC + " text, "
            + COLUMN_GRADUACIO + " text, "
            + COLUMN_DATA + " text, "
            + COLUMN_COMENTARI + " text, "
            + COLUMN_IDBODEGA + " integer, "
            + COLUMN_IDDENOMINACIO + " integer, "
            + COLUMN_PREU + " float, "
            + COLUMN_VALOLFATIVA + " text, "
            + COLUMN_VALGUSTATIVA + " text, "
            + COLUMN_VALVISUAL + " text, "
            + COLUMN_NOTA + " integer, "
            + COLUMN_FOTO + " text );";

    private static final String DATABASE_CREATE_BODEGA = "create table "
            + TABLE_BODEGA + "( "
            + COLUMN__IDBODEGA + " integer primary key autoincrement, "
            + COLUMN_NOMBODEGA + " text not null);";

    private static final String DATABASE_CREATE_DENOMINACIO = "create table "
            + TABLE_DENOMINACIO + "( "
            + COLUMN__IDDENOMINACIO + " integer primary key autoincrement, "
            + COLUMN_NOMDENOMINACIO + " text not null);";

    private static final String DATABASE_CREATE_TIPUS = "create table "
            + TABLE_TIPUS + "( "
            + COLUMN__TIPUS + " text not null primary key);";

    public HelperVi(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // CREAM LA BASE DE DADES
        database.execSQL(DATABASE_CREATE_VI);
        database.execSQL(DATABASE_CREATE_BODEGA);
        database.execSQL(DATABASE_CREATE_DENOMINACIO);
        database.execSQL(DATABASE_CREATE_TIPUS);
        database.execSQL(" insert into "+TABLE_TIPUS+"(tipus) values(('Tinto'))");
        database.execSQL(" insert into "+TABLE_TIPUS+"(tipus) values(('Rosat'))");
        database.execSQL(" insert into "+TABLE_TIPUS+"(tipus) values(('Blanc'))");
        database.execSQL(" insert into "+TABLE_TIPUS+"(tipus) values(('Dolç'))");
        database.execSQL(" insert into "+TABLE_TIPUS+"(tipus) values(('Espumós'))");
        database.execSQL(" insert into "+TABLE_TIPUS+"(tipus) values(('Cervesa'))");
        database.execSQL(" insert into "+TABLE_TIPUS+"(tipus) values(('Altres'))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Detecta si hi ha una canvi a DATABASE_VERSION i recrea la base de dades
        Log.w(HelperVi.class.getName(),
                "Modificant desde la versió " + oldVersion + " a "+ newVersion );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DENOMINACIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BODEGA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VI);
        onCreate(db);
    }
}
