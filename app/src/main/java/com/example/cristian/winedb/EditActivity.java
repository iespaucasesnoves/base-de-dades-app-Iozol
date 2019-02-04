package com.example.cristian.winedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    Vi vi;
    Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        id = Long.parseLong(intent.getStringExtra("ID"));

        if(id != -1) {
            cargarInfo(id);
        } else{
            montaSpinners("");
            montaAutocompletar("", true);
            montaAutocompletar("",false);
        }

    }

    public void borrar(View v){
        if(id != -1){
            DataSourceVi bd;
            bd = new DataSourceVi(this);
            bd.open();
            bd.deleteVi(vi);
            bd.close();
            this.finish();
        }
    }

    public void insereix(View v){
        DataSourceVi bd;
        bd = new DataSourceVi(this);


        TextView nomVi = findViewById(R.id.nomVi);
        TextView date = findViewById(R.id.dade);
        TextView anyada = findViewById(R.id.anyada);
        TextView graduacio = findViewById(R.id.graduacio);
        TextView comentari = findViewById(R.id.comentari);
        RatingBar olor = findViewById(R.id.olor);
        RatingBar visual = findViewById(R.id.visual);
        RatingBar gust = findViewById(R.id.gust);
        AutoCompleteTextView bodega = (AutoCompleteTextView) findViewById(R.id.bodega);
        AutoCompleteTextView denominacio = findViewById(R.id.denominacio);

        if(id == -1)vi = new Vi();

        vi.setNomVi(nomVi.getText().toString());
        vi.setData(date.getText().toString());
        vi.setAnada(anyada.getText().toString());
        vi.setGraduacio(graduacio.getText().toString());
        vi.setComentari(comentari.getText().toString());
        vi.setIdBodega();
        vi.setIdDenominacio();
        vi.setValGustativa(gust.getRating());
        vi.setValOlfativa(olor.getText().toString());
        vi.setValVisual(visual.getText().toString());
        vi.setTipus();

        bd.open();
        if(id != -1){
            bd.updateVi(vi);
        } else{
            bd.createVi(vi);
        }
        bd.close();
        this.finish();
    }

    private void montaSpinners(String t) {
        List<String> llista;
        DataSourceVi bd;
        bd = new DataSourceVi(this);
        bd.open();
        llista = bd.getAllTipus();
        bd.close();
        Spinner spinner = findViewById(R.id.tipus);
        // Crear adapter
        ArrayAdapter<String> dataAdapter = new
                ArrayAdapter<>(this,android.R.layout.simple_spinner_item, llista);
        // Drop down estil – llista amb radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // assignar adapter
        spinner.setAdapter(dataAdapter);
        if (t!=null && !t.equals("")) {
            for (int i = 0; i < spinner.getCount(); i++) {
                if (spinner.getItemAtPosition(i).equals(t)) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    private void montaAutocompletar(String b, boolean booBodega){
        List<String> llista;
        DataSourceVi bd;
        bd = new DataSourceVi(this);

        bd.open();
        if(booBodega){
            llista=bd.getLlistaBodegues();
        }else{
            llista=bd.getLlistaDenominacio();
        }
        bd.close();

        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, llista);

        if(booBodega) {
            AutoCompleteTextView bodega = (AutoCompleteTextView) findViewById(R.id.bodega);
            bodega.setThreshold(0);
            bodega.setAdapter(adapter);
            if (b!=null && !b.equals("")) {
                bodega.setText(b,true);
            }
        } else{
            AutoCompleteTextView denominacio = findViewById(R.id.denominacio);
            denominacio.setThreshold(0);
            denominacio.setAdapter(adapter);
            if (b!=null && !b.equals("")) {
                denominacio.setText(b,true);
            }
        }
    }

    public void cargarInfo(Long id) {
        // Obrim la base de dades
        DataSourceVi bd;
        bd = new DataSourceVi(this);
        bd.open();
        vi = bd.getVi(id);
        bd.close();

        Bodega bod = bd.getBodega(vi.getIdBodega());
        Denominacio deno = bd.getDenominacio(vi.getIdDenominacio());
        TextView nomVi = findViewById(R.id.nomVi);
        TextView date = findViewById(R.id.dade);
        TextView anyada = findViewById(R.id.anyada);
        TextView graduacio = findViewById(R.id.graduacio);
        TextView comentari = findViewById(R.id.comentari);
        RatingBar olor = findViewById(R.id.olor);
        RatingBar visual = findViewById(R.id.visual);
        RatingBar gust = findViewById(R.id.gust);
        montaSpinners(vi.getTipus());

        nomVi.setText(vi.getNomVi());
        date.setText(vi.getData());
        anyada.setText(vi.getAnada());
        graduacio.setText(vi.getGraduacio());
        montaAutocompletar(deno.getNomdenominacio(), false);
        montaAutocompletar(bod.getNombodega(), true);
        if(vi.getComentari() != null)comentari.setText(vi.getNomVi());
        if(vi.getValOlfativa() != null)olor.setRating(Float.parseFloat(vi.getValOlfativa()));
        if(vi.getValVisual() != null)visual.setRating(Float.parseFloat(vi.getValVisual()));
        if(vi.getValVisual() != null)gust.setRating(Float.parseFloat(vi.getValGustativa()));

    }
}
