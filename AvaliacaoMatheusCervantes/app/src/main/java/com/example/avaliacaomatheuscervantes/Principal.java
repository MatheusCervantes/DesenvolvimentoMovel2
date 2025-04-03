package com.example.avaliacaomatheuscervantes;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Principal extends Activity {
    EditText editNome, editQtdrifas;
    BancoDados bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);
        bancoDados = new BancoDados(this);
        editNome = findViewById(R.id.editNome);
        editQtdrifas = findViewById(R.id.editQtdrifas);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnCalcular = findViewById(R.id.btnCalcular);

        btnAdd.setOnClickListener(v -> {
            String nome = editNome.getText().toString();
            String qtdrifas = editQtdrifas.getText().toString();

            if (nome.isEmpty() || qtdrifas.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                bancoDados.insereRifa(editNome.getText().toString(), Integer.parseInt(editQtdrifas.getText().toString()));
                bancoDados.fechar();
            }
        });

        btnCalcular.setOnClickListener(v -> {
            bancoDados.abrir();
            Cursor cursor= bancoDados.calcular();
            int total_rifas = 0;
            while (cursor.moveToNext()) {
                int qtdrifas = cursor.getInt(cursor.getColumnIndex(BancoDados.KEY_QTDRIFAS));
                total_rifas += qtdrifas;
            }
            Toast.makeText(this, "Valor total das rifas " + total_rifas*10, Toast.LENGTH_SHORT).show();
            cursor.close();
        });
    }
}
