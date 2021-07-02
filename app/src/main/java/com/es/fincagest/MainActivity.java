package com.es.fincagest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button botonCrearParcela;
    Button botonListaParcela;
    Button botonCerrarAplicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonCrearParcela = findViewById(R.id.btn_nueva_parcela);
        botonCrearParcela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,crearParcela.class);
                startActivity(i);
            }
        });
        botonListaParcela = findViewById(R.id.btn_ver_parcelas);
        botonListaParcela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this,listaParcelas.class);
                startActivity(i2);
            }
        });

        botonCerrarAplicacion = findViewById(R.id.btn_salir);

        botonCerrarAplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });




        }
}