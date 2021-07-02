package com.es.fincagest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class crearParcela extends AppCompatActivity {


    private EditText nombreParcela,variedad,hectareas,nombreTratamiento;
    private Spinner spin;
    TextView textviewCalendario;
    Button guardarParcela ;
    String fechaUltimoTratamiento;
    Boolean fechacambiada = false;

    DatabaseReference databaseParcelas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_parcela);

        databaseParcelas = FirebaseDatabase.getInstance().getReference("parcelas");

        guardarParcela= (Button) findViewById(R.id.botonGuardarParcela);

        //DATOS DEL FORMULARIO
        nombreParcela = (EditText) findViewById(R.id.editTextNombreParcela);
        variedad = (EditText) findViewById(R.id.editTextVariedad);
        hectareas = (EditText) findViewById(R.id.editTextHectareas);
        nombreTratamiento = (EditText) findViewById(R.id.editTextNombreTratamiento);

        //---------------------------SPINNER DE FRUTA-----------------------------
        spin = findViewById(R.id.spinnerFruta);
        ArrayList<String> frutas = new ArrayList<>();
        frutas.add("Fresas");frutas.add("Frambuesas");frutas.add("Arandanos");frutas.add("Naranjas");
        ArrayAdapter adp = new ArrayAdapter(crearParcela.this, android.R.layout.simple_spinner_dropdown_item,frutas);
        spin.setAdapter(adp);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String fruta=(String) spin.getAdapter().getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //---------------------------------------------------------------------------------------
        //-------------------------------CALENDARIO----------------------------------------------
        //video de ayuda sonbre como trabajar con el calendario https://www.youtube.com/watch?v=quR2UW-VkU0
        textviewCalendario = findViewById(R.id.textViewFechaUltimoTratamiento);
        //



    }
    public void guardarParcela(View view) throws ParseException {
        // VALIDACION DE CAMPOS
        if(nombreParcela.getText().toString().isEmpty()){
            Toast.makeText(this,"Campo Nombre vacío",Toast.LENGTH_SHORT).show();
        }else{
            if(variedad.getText().toString().isEmpty()){
                Toast.makeText(this,"Campo Variedad vacío",Toast.LENGTH_SHORT).show();
            }else{
                if(hectareas.getText().toString().isEmpty()){
                    Toast.makeText(this,"Campo Hectareas vacío",Toast.LENGTH_SHORT).show();
                }else{
                    if(nombreTratamiento.getText().toString().isEmpty()){
                        Toast.makeText(this,"Campo Nombre del Tratamiento vacío",Toast.LENGTH_SHORT).show();
                    }else {
                        fechaUltimoTratamiento = textviewCalendario.getText().toString();
                        if (fechaUltimoTratamiento.equals("Fecha del ultimo tratamiento insecticida")) {
                            Toast.makeText(this, "Campo Fecha incorrecto", Toast.LENGTH_SHORT).show();
                        } else {
                            //fecha almacenada en formato dd/m/año

                            //ALMACENAMOS LOS DATOS EN VARIABLES PARA DESPUES ALMACENARLOS EN UN OBJETO TIPO CADENA

                            // String idParcela = UUID.randomUUID().toString();
                            String nombreParcelaString = nombreParcela.getText().toString();
                            int hectareasInt = Integer.parseInt(hectareas.getText().toString());
                            String variedadString = variedad.getText().toString();
                            String nombreTratamientoString = nombreTratamiento.getText().toString();


                            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar fechaUltimoTratamientoCalendar = Calendar.getInstance();
                            fechaUltimoTratamientoCalendar.setTime(formato.parse(fechaUltimoTratamiento));

                            //PARA ALMACENAR LA PRÓXIMA FECHA DE TRATAMIENTO TENEMOS QUE FILTAR POR EL TIPO DE FRUTA YA QUE CADA FRUTA TIENE UN TIEMPO PROPIO
                            Spinner mySpinner = (Spinner) findViewById(R.id.spinnerFruta);
                            String frutaSeleccionadaString = mySpinner.getSelectedItem().toString();
                            int diasASumar = 0;
                            switch (frutaSeleccionadaString) {
                                case "Fresas":
                                    diasASumar = 7;
                                    break;
                                case "Frambuesas":
                                    diasASumar = 20;
                                    break;
                                case "Arandanos":
                                    diasASumar = 30;
                                    break;
                                case "Naranjas":
                                    diasASumar = 15;
                                    break;
                            }

                            //este objeto calendar se iguala al anterior calendar y según la fruta que sea incrementa una serie de dias.
                            Calendar fechaProximoTratamientoCalendar = Calendar.getInstance();
                            fechaProximoTratamientoCalendar = fechaUltimoTratamientoCalendar;

                            //Recopila toda la información y genera un objeto Parcela, para volcar a la base de datos utilizando como hijo el valor id
                            //el valor id se genera a partir de un getKey de la clase databaseReference.
                            String idParcela = databaseParcelas.push().getKey();

                            Date fechaUltimoTratamientoDate = fechaUltimoTratamientoCalendar.getTime();
                            Date fechaProximoTratamientoDate = fechaProximoTratamientoCalendar.getTime();

                            Parcela parcela1 = new Parcela(idParcela, nombreParcelaString, hectareasInt, fechaUltimoTratamientoDate,
                                    fechaProximoTratamientoDate, frutaSeleccionadaString, variedadString,nombreTratamientoString,diasASumar);
                            databaseParcelas.child(idParcela).setValue(parcela1);
                            Toast.makeText(this, "Parcela: "+nombreParcelaString+" almacenada correctamente", Toast.LENGTH_SHORT).show();
                            // Funciona la inserción en Firebase

                        }
                    }
                }
            }
        }
    }



    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(crearParcela.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month + 1 )+ "/" + year;
                textviewCalendario.setText(fecha);
            }
        },anio,mes,dia);
        dpd.show();
    }
}