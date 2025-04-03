package com.example.crudugao;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class Principal extends Activity {
    Banco banco;
    List<String> Lista;
    ListView listItem;
    EditText editText;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        banco = new Banco(this);
        editText = findViewById(R.id.editNome);
        btnAdd = findViewById(R.id.btnAdd);
        listItem = findViewById(R.id.listItem);
        Lista = new ArrayList<>();

        carregarLista();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editText.getText().toString().trim();
                if (!nome.isEmpty()) {
                    banco.insereNome(nome);
                    editText.setText("");
                    carregarLista();
                }
            }
        });
    }

    private void carregarLista() {
        Lista.clear();

        Cursor cursor = banco.listarNomes();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(Banco.KEY_NOME));
                Lista.add(nome);
            } while (cursor.moveToNext());
            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Lista);
        listItem.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        banco.fechar();
    }
}
