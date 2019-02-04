package com.example.cristian.winedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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

        if(id != -1) {
            cargarInfo(id);
        } else{
            montaSpinners("");
        }

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
            selectValue(spinner,t); // Si hi ha un valor assignat posicionar-se
        }
    }
    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
    private void montaAutocompleta(String b,String d){
        List<String> llista;
        DataSourceVi bd;
        bd = new DataSourceVi(this);
        llista=bd.getLlistaBodegues();
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, llista);
        AutoCompleteTextView bodega = (AutoCompleteTextView) findViewById(R.id.acBodega);
        bodega.setThreshold(0);
        bodega.setAdapter(adapter);
        if (b!=null && !b.equals("")) {
            bodega.setText(b,true);
        }
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
        if(vi.getTipus() != null){
            montaSpinners(vi.getTipus());
        }
        else{
            montaSpinners("");
        }

        nomVi.setText(vi.getNomVi());
        date.setText(vi.getData());
        anyada.setText(vi.getAnada());
        graduacio.setText(vi.getGraduacio());
        if(deno.getNomdenominacio() != null) denominacio.setText(deno.getNomdenominacio());
        if(bod.getNombodega() != null) bodega.setText(bod.getNombodega());
        if(vi.getComentari() != null)comentari.setText(vi.getNomVi());
        if(vi.getValOlfativa() != null)olor.setRating(Float.parseFloat(vi.getValOlfativa()));
        if(vi.getValVisual() != null)visual.setRating(Float.parseFloat(vi.getValVisual()));

    }
}
