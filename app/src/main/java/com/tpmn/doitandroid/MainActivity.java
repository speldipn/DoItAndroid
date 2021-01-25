package com.tpmn.doitandroid;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    EditText dbNameEditText;
    Button createDbButton;
    EditText tableNameEditText;
    Button createTableButton;
    TextView infoTextView;

    SQLiteDatabase database;
    String dbName;
    String tableName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        dbNameEditText = findViewById(R.id.dbNameEditText);
        createDbButton = findViewById(R.id.createDbButton);

        tableNameEditText = findViewById(R.id.tableNameEditText);
        createTableButton = findViewById(R.id.createTableButton);

        infoTextView = findViewById(R.id.infoTextView);

        createDbButton.setOnClickListener(v -> {
            printLogMsg("create db called.");
            dbName = dbNameEditText.getText().toString();
            database = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
            printLogMsg("DB created. " + dbName);
        });

        createTableButton.setOnClickListener(v -> {
            printLogMsg("create table called.");
            tableName = tableNameEditText.getText().toString();

            if(database == null) {
                printLogMsg("Must create db");
                return;
            }

            database.execSQL("create table if not exists " + tableName + "(" +
                    " _id integer PRIMARY KEY autoincrement, " +
                    " name text, " +
                    " age integer, " +
                    " mobile text)");

            printLogMsg("table created");

            insertRecord();
        });
    }

    private void printLogMsg(String msg) {
        new Handler(Looper.getMainLooper()).post(() -> {
            infoTextView.append(msg + "\n");
        });
    }

    private void insertRecord() {
        printLogMsg("insertRecord called");

        if(database == null) {
            printLogMsg("database is null");
            return;
        }

        if(tableName == null) {
            printLogMsg("must create table");
            return;
        }

        database.execSQL("insert into " + tableName +
                        "(name, age, mobile) " +
                         "  values" +
                " ('Neo', 34, '010-7319-5025')");

        printLogMsg("insertRecord done");
    }
}