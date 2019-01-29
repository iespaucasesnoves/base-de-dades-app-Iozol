package com.example.cristian.winedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        Long id = Long.parseLong(intent.getStringExtra("ID"));

        cargarInfo(id);
    }

    public void cargarInfo(Long id) {
        // Obrim la base de dades
        DataSourceVi bd;
        bd = new DataSourceVi(this);
        bd.open();
        Vi vi = bd.getVi(id);
        bd.close();

        Bodega bod = bd.getBodega(vi.getIdBodega());
        Denominacio deno = bd.getDenominacio(vi.getIdDenominacio());
        TextView nomVi = findViewById(R.id.nomVi);
        TextView date = findViewById(R.id.dade);
        TextView anyada = findViewById(R.id.anyada);
        TextView graduacio = findViewById(R.id.graduacio);
        TextView bodega = findViewById(R.id.bodega);
        TextView denominacio = findViewById(R.id.denominacio);
        TextView comentari = findViewById(R.id.comentari);
        RatingBar olor = findViewById(R.id.olor);
        RatingBar visual = findViewById(R.id.visual);

        if()
        nomVi.setText(vi.getNomVi());
        date.setText(vi.getData());
        anyada.setText(vi.getAnada());
        graduacio.setText(vi.getGraduacio());
        bodega.setText(bod.getNombodega());
        denominacio.setText(deno.getNomdenominacio());
        comentari.setText(vi.getNomVi());
        olor.setRating(Float.parseFloat(vi.getValOlfativa()));
        visual.setRating(Float.parseFloat(vi.getValVisual()));
    }
}
