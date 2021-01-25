package com.tpmn.doitandroid;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTableLockedException;
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
    public static final String DB_NAME = "neo.db";
    public static final String TABLE_NAME = "neo";

    EditText titleEditText;
    EditText authorEditText;
    EditText contentEditText;
    Button saveButton;

    TextView textView;

    SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        contentEditText = findViewById(R.id.contentEditText);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            saveData();
        });
        textView = findViewById(R.id.debugTextView);

        createDdatabase();
    }

    private void saveData() {
        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if (database == null) {
            printMsg("Database is not exist");
            return;
        }

        String sql = "insert (title, author, content) from neo values (" +
                title + ", " +
                author + ", " +
                content +
                ")";

        database.execSQL(sql);
    }

    private void getData() {
        // TODO : read books all
    }

    private void printMsg(String msg) {
        new Handler(Looper.getMainLooper()).post(() -> {
            textView.append(msg + "\n");
        });
    }

    private void createDdatabase() {
        String sql = "create table if not exists " + TABLE_NAME + " (" +
                "title text, " +
                "author text, " +
                "content text" +
                ")";


        database = SQLiteDatabase.openOrCreateDatabase(DB_NAME, null);
        database.execSQL(sql);
    }
}

