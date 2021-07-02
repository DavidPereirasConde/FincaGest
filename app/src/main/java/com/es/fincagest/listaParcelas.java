package com.es.fincagest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class listaParcelas extends AppCompatActivity {

    private List<Parcela> listaParcelas = new ArrayList<Parcela>();
    ArrayAdapter<Parcela> arrayAdapterParcela;
    ListView listvListaParcela;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parcelas);
        listvListaParcela = findViewById(R.id.listviewParcela);
        iniciarFirebase();
        listarDatos();

    }

    private void listarDatos() {
        databaseReference.child("parcelas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaParcelas.clear();

                for(DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Parcela p = objSnaptshot.getValue(Parcela.class);
                    listaParcelas.add(p);
                }
                arrayAdapterParcela = new ArrayAdapter<Parcela>(listaParcelas.this, android.R.layout.simple_list_item_1,listaParcelas);
                listvListaParcela.setAdapter(arrayAdapterParcela);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        listvListaParcela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Parcela DatosParcelaClick = arrayAdapterParcela.getItem(i);
                String IdParcelaString = DatosParcelaClick.getIdParcela().toString();
                Intent intentEditar = new Intent(listaParcelas.this,editarParcela.class);
                intentEditar.putExtra("IdParcelaClick",IdParcelaString);
                intentEditar.putExtra("objetoParcelaClick",DatosParcelaClick);
                startActivity(intentEditar);
            }
        });
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}