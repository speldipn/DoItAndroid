package com.tpmn.doitandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLInput;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";
    public static String NAME = "neo.db";
    public static int VERSION = 1;

    EditText dbNameEditText;
    Button createDbButton;
    EditText tableNameEditText;
    Button createTableButton;
    TextView infoTextView;

    DatabaseHelper dbHelper;
    SQLiteDatabase database;

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
           dbHelper = new DatabaseHelper(this);
           database = dbHelper.getWritableDatabase();
           printLogMsg("db created");
           insertQuery();
        });

        createTableButton.setOnClickListener(v -> {
            executeQuery();
        });
    }

    private void insertQuery() {
        String sql = "insert into emp (name, age, mobile) values ('neo', 34, 'samples...')";
        database.execSQL(sql);
    }

    private void executeQuery() {
        printLogMsg("executeQuery called");

        Cursor cursor = database.rawQuery("select _id, name, age, mobile from emp", null);
        int recordCount = cursor.getCount();
        printLogMsg("recordCount: " + recordCount);

        for(int i = 0; i < recordCount; ++i) {
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);

            printLogMsg(id + ", " + name + ", " + age + ", " + mobile);
        }
        cursor.close();
    }

    private void printLogMsg(String msg) {
        new Handler(Looper.getMainLooper()).post(() -> {
            infoTextView.append(msg + "\n");
        });
    }

    class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, NAME, null, VERSION);
        ㅓ}

        @Override
        public void onCreate(SQLiteDatabase db) {
            printLogMsg("Create db called");
            String sql = "create table if not exists emp(" +
                    " _id integer PRIMARY KEY autoincrement," +
                    " name text," +
                    " age integer," +
                    " mobile text)";

            db.execSQL(sql);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            printLogMsg("onOpen called");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            printLogMsg("onUpgrade called " + oldVersion + " -> " + newVersion);

            if(newVersion > 1) {
                db.execSQL("DROP TABLE IF EXISTS emp");
            }
        }
    }
}

