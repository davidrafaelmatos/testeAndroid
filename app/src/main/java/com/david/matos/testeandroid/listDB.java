package com.david.matos.testeandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.david.matos.testeandroid.DB.Contrato;
import com.david.matos.testeandroid.DB.DB;

public class listDB extends AppCompatActivity {

    ListView listMonumentos;
    DB mDbHelper;
    SQLiteDatabase db;
    Cursor c, c_pessoas;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_db);

        listMonumentos = (ListView) findViewById(R.id.listMonumentos);

        mDbHelper = new DB(getApplicationContext());
        db = mDbHelper.getReadableDatabase();

        preencheLista();

    }

    private void preencheLista() {

        c = db.query(false, Contrato.Monumentos.TABLE_NAME, Contrato.Monumentos.PROJECTION, null, null, null, null, null, null);

        adapter = new SimpleCursorAdapter(getApplicationContext(),
                android.R.layout.two_line_list_item,
                c,
                new String[]{Contrato.Monumentos.COLUMN_DESCR, Contrato.Monumentos.COLUMN_TIPO},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listMonumentos.setAdapter(adapter);
    }
}
