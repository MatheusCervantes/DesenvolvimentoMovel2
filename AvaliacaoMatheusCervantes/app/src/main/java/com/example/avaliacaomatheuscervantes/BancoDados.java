package com.example.avaliacaomatheuscervantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class BancoDados {

    private final static String TAG = "BancoDados";

    static String KEY_ID = "_id";
    static String KEY_NOME = "nome";
    static String KEY_QTDRIFAS = "qtdrifas";

    String NOME_BANCO = "db_rifas";
    String NOME_TABELA = "rifas";
    int VERSAO_BANCO = 1;

    String SQL_CREATE_TABLE = "CREATE TABLE " + NOME_TABELA + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NOME + " TEXT, " +
            KEY_QTDRIFAS + " INTEGER);";


    final Context contexto;
    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public BancoDados(Context ctx) {
        this.contexto = ctx;
        openHelper = new MeuOpenHelper(contexto);
    }

    private class MeuOpenHelper extends SQLiteOpenHelper {

        MeuOpenHelper (Context contexto) {
            super(contexto, NOME_BANCO, null, VERSAO_BANCO);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(SQL_CREATE_TABLE);
            } catch (SQLException e) {
                Log.e(TAG, "Erro na criação do BD.");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS dividas");
            onCreate(db);
        }
    }

    public BancoDados abrir() throws SQLException {
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void fechar() {
        openHelper.close();
    }

    public void insereRifa(String nome, int qtdrifas) {
        abrir();
        ContentValues campos = new ContentValues();
        campos.put(KEY_NOME, nome);
        campos.put(KEY_QTDRIFAS, qtdrifas);
        db.insert(NOME_TABELA, null, campos);
        Toast.makeText(contexto, nome + " foi inserido com sucesso.", Toast.LENGTH_SHORT).show();
    }

    public Cursor calcular() {
        String[] colunas = {KEY_QTDRIFAS};
        return db.query(NOME_TABELA, colunas, null, null, null, null, null);
    }

}