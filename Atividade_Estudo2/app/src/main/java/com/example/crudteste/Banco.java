package com.example.crudteste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Banco extends SQLiteOpenHelper {
    Principal principal = new Principal();
    private static final String DATABASE_NAME = "nomes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOMES = "nomes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    public Banco (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "CREATE TABLE " + TABLE_NOMES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT NOT NULL);";
            db.execSQL(sql);
        } catch (SQLException erro){
            principal.mostrarAvisos("Erro ao criar a tabela" + erro);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOMES);
            onCreate(db);
        } catch (SQLException erro) {
            principal.mostrarAvisos("Erro ao deletar a tabela" + erro);
        }
    }

    public void adicionarNome(String nome){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NOME, nome);
            db.insert(TABLE_NOMES, null, values);
            db.close();
        } catch (SQLException erro){
            principal.mostrarAvisos("Erro ao deletar a tabela" + erro);
        }
    }

    public ArrayList<String> listarNomes() {
        try {
            ArrayList<String> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NOMES, null);

            if (cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(1));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return list;
        } catch (SQLException erro){
            principal.mostrarAvisos("Erro ao inserir nome na tabela" + erro);
        }
        return null;
    }
}
