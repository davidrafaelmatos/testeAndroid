package com.david.matos.testeandroid.DB;

import android.provider.BaseColumns;

/**
 * Created by slimmy on 12/04/2018.
 */

public class Contrato {

    private static final String TEXT_TYPE = " TEXT ";
    private static final String INT_TYPE = " INTEGER ";
    private static final String FLOAT_TYPE = " REAL ";

    public Contrato(){

    }

    public static abstract class Monumentos implements BaseColumns {
        public static final String TABLE_NAME = "monumentos";
        public static final String COLUMN_DESCR = "descr";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_TIPO = "tipo";

        public static final String[] PROJECTION = {Monumentos._ID, Monumentos.COLUMN_DESCR, Monumentos.COLUMN_LATITUDE, Monumentos.COLUMN_LONGITUDE, Monumentos.COLUMN_TIPO};

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Monumentos.TABLE_NAME + "(" +
                        Monumentos._ID + INT_TYPE + " PRIMARY KEY, " +
                        Monumentos.COLUMN_DESCR + TEXT_TYPE + ", " +
                        Monumentos.COLUMN_LATITUDE + TEXT_TYPE + ", " +
                        Monumentos.COLUMN_LONGITUDE + TEXT_TYPE + "," +
                        Monumentos.COLUMN_TIPO + INT_TYPE + "," +
                        "FOREIGN KEY(" + Monumentos.COLUMN_TIPO + ") REFERENCES " + Tipos.TABLE_NAME + "(" + Tipos._ID + "));";

        public static final String SQL_DROP_ENTRIES =
                "DROP TABLE " + Monumentos.TABLE_NAME + ";";
    }

    public static abstract class Tipos implements BaseColumns {
        public static final String TABLE_NAME = "tipos";
        public static final String COLUMN_DESCR = "descr";

        public static final String[] PROJECTION = {Monumentos._ID, Monumentos.COLUMN_DESCR};

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Tipos.TABLE_NAME + "(" +
                        Tipos._ID + INT_TYPE + " PRIMARY KEY, " +
                        Tipos.COLUMN_DESCR + TEXT_TYPE + ");";

        public static final String SQL_DROP_ENTRIES =
                "DROP TABLE " + Tipos.TABLE_NAME + ";";
    }

}
