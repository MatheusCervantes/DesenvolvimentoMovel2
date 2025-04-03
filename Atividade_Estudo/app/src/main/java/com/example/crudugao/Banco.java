package com.example.crudugao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Banco {
    private final static String TAG = "Banco";
    static String KEY_ID = "_id";
    static String KEY_NOME = "nome";
    String NOME_BANCO = "db_nomes";
    String NOME_TABELA = "tabela_nomes";
    int VERSAO_BANCO = 1;

    String SQL_CREATE_TABLE = "create table "+ NOME_TABELA +" (" +
            KEY_ID +" integer primary key autoincrement, " +
            KEY_NOME +" text);";

    final Context contexto;

    MeuOpenHelper openHelper;
    SQLiteDatabase db;

    public Banco (Context ctx) {
        this.contexto = ctx;
        openHelper = new MeuOpenHelper(contexto);
    }

    private class MeuOpenHelper extends SQLiteOpenHelper{
        MeuOpenHelper (Context contexto){
            super(contexto, NOME_BANCO, null, VERSAO_BANCO);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(SQL_CREATE_TABLE);
            }
            catch (SQLException e){
                Log.e(TAG, "Erro na Criação do BD");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ NOME_TABELA);
        }
    }

    public Banco abrir () throws SQLException {
        db = openHelper.getWritableDatabase();
        return this;
    }


    public void fechar (){
        openHelper.close();
    }

    public void insereNome (String nome){
        abrir();
        ContentValues campos = new ContentValues();
        campos.put(KEY_NOME, nome);
        db.insert(NOME_TABELA, null, campos);
        db.close();
    }

    public Cursor listarNomes () {
        abrir();
        String[] colunas = {KEY_NOME};
        Cursor cursor = db.query(NOME_TABELA, colunas, null, null, null, null, null);
        return cursor;
    }

}
