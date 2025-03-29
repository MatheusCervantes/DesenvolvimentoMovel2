package com.example.atividadeavaliativa2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends Activity {

    private BancoDados bancoDeDados;
    private EditText editNome, editForca, editInteligencia, editAgilidade;
    private Spinner spinnerClasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro);

        bancoDeDados = new BancoDados(this);

        editNome = findViewById(R.id.editNome);
        editForca = findViewById(R.id.editForca);
        editInteligencia = findViewById(R.id.editInteligencia);
        editAgilidade = findViewById(R.id.editAgilidade);
        spinnerClasse = findViewById(R.id.spinner_classe);

        String[] classes = {"Bárbaro", "Ladino", "Mago", "Necromante", "Paladino"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClasse.setAdapter(adapter);

        Button btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(v -> salvarPersonagem());

        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void salvarPersonagem() {
        String nome = editNome.getText().toString().trim();
        String forca = editForca.getText().toString().trim();
        String inteligencia = editInteligencia.getText().toString().trim();
        String agilidade = editAgilidade.getText().toString().trim();
        String classe = spinnerClasse.getSelectedItem().toString();

        if (nome.isEmpty() || forca.isEmpty() || inteligencia.isEmpty() || agilidade.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}
