package com.es.fincagest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class editarParcela extends AppCompatActivity {

    private EditText nombreParcela,variedad,hectareas;
    private Spinner spin;
    TextView textviewCalendario;
    Button guardarParcela ;
    String fechaUltimoTratamiento;
    String idParcela;
    Boolean fechacambiada = false;

    DatabaseReference databaseParcelas;

    Button botonEditarParcela ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_parcela);

        Parcela parcelaEditable = (Parcela) getIntent().getSerializableExtra("objetoParcelaClick");
        databaseParcelas = FirebaseDatabase.getInstance().getReference("parcelas");

        nombreParcela = (EditText) findViewById(R.id.editTextNombreParcelaEditar);
        variedad = (EditText) findViewById(R.id.editTextVariedadEditar);
        hectareas = (EditText) findViewById(R.id.editTextHectareasEditar);

        spin = findViewById(R.id.spinnerFrutaEditar);
        ArrayList<String> frutas = new ArrayList<>();
        frutas.add("Fresas");
        frutas.add("Frambuesas");
        frutas.add("Arandanos");
        frutas.add("Naranjas");
        ArrayAdapter adp = new ArrayAdapter(editarParcela.this, android.R.layout.simple_spinner_dropdown_item,frutas);
        spin.setAdapter(adp);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String fruta=(String) spin.getAdapter().getItem(position);
                //Aqui ya podemos manejar la selección, +info en https://www.youtube.com/watch?v=P55qX1-dsZI min 5:40
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        idParcela = parcelaEditable.getIdParcela();
        nombreParcela.setText(parcelaEditable.getNombreParcela());
        variedad.setText(parcelaEditable.getVariedad());
        int hectareasInt = parcelaEditable.getHectareas();
        hectareas.setText(String.valueOf(hectareasInt));

        int posicion = 0;

        for(int i=0;i<frutas.size();i++)
        {
            String frutaSeleccionada = frutas.get(i);
            if(parcelaEditable.getFruta()== frutaSeleccionada)
            {
                posicion = i;
                break;
            }
        }
        spin.setSelection(posicion);

        botonEditarParcela = (Button) findViewById(R.id.botonGuardarCambios);


    }

    public void guardarCambios(View view) {

        Parcela parcelaEditada = (Parcela) getIntent().getSerializableExtra("objetoParcelaClick");
        parcelaEditada.setNombreParcela(nombreParcela.getText().toString());
        parcelaEditada.setVariedad(variedad.getText().toString());
        int hectareasInt = Integer.parseInt(hectareas.getText().toString());
        parcelaEditada.setHectareas(hectareasInt);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerFrutaEditar);
        String frutaSeleccionadaString = mySpinner.getSelectedItem().toString();
        parcelaEditada.setFruta(frutaSeleccionadaString);

        databaseParcelas.child(parcelaEditada.getIdParcela()).setValue(parcelaEditada);
        Toast.makeText(this,"Parcela Actualizada Correctamente",Toast.LENGTH_SHORT).show();
        


    }
}