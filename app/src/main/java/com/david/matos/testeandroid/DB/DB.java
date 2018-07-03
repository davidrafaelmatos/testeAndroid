package com.david.matos.testeandroid.DB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by slimmy on 12/04/2018.
 */

public class DB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "monumentos.db";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contrato.Tipos.SQL_CREATE_ENTRIES);
        db.execSQL(Contrato.Monumentos.SQL_CREATE_ENTRIES);

        db.execSQL("insert into " + Contrato.Tipos.TABLE_NAME + " Values (1, 'Castelo');");
        db.execSQL("insert into " + Contrato.Tipos.TABLE_NAME + " Values (2, 'Museu');");
        db.execSQL("insert into " + Contrato.Tipos.TABLE_NAME + " Values (3, 'Santuarios');");

        db.execSQL("insert into " + Contrato.Monumentos.TABLE_NAME + " Values (1, 'Santuario de Santa Luzia', '41.701779', '-8.835202', 3);");
        db.execSQL("insert into " + Contrato.Monumentos.TABLE_NAME + " Values (2, 'Forte de Santiago da Barra', '41.689124', '-8.838104', 1);");
        db.execSQL("insert into " + Contrato.Monumentos.TABLE_NAME + " Values (3, 'Museu do Traje', '41.693854', ' -8.828763', 2);");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Contrato.Tipos.SQL_DROP_ENTRIES);
        db.execSQL(Contrato.Monumentos.SQL_DROP_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }



}
