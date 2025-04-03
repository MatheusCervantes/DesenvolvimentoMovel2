package com.example.crudteste;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class Principal extends Activity {
    private Banco banco;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> nomes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        banco = new Banco(this);
        nomes = banco.listarNomes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomes);
        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        EditText edtNome = findViewById(R.id.edtNome);
        ListView ltNomes = findViewById(R.id.listaNomes);
        ltNomes.setAdapter(adapter);

        btnAdicionar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString().trim();
            if (!nome.isEmpty()){
                banco.adicionarNome(nome);
                nomes.add(nome);
                adapter.notifyDataSetChanged();
                edtNome.setText("");
                mostrarAvisos("Nome adicionado com sucesso");
            } else {
                mostrarAvisos("Preencha o campo nome");
            }
        });
    }

    public void mostrarAvisos (String aviso){
        Toast.makeText(this, aviso, Toast.LENGTH_LONG).show();
    }
}
