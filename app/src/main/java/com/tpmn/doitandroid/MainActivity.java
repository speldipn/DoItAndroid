package com.tpmn.doitandroid;

import android.Manifest;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    public static final String DB_NAME = "book.db";
    public static final String TABLE_NAME = "book";

    public static final String BOOK_ID = "_id";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_CONTENT = "content";

    EditText titleEditText;
    EditText authorEditText;
    EditText contentEditText;
    Button saveButton;
    TextView debugTextView;

    SQLiteDatabase database;

    List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
    }

    private void setup() {
        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        contentEditText = findViewById(R.id.contentEditText);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            saveData();
        });
        debugTextView = findViewById(R.id.debugTextView);

        createDdatabase();
        getData();
    }

    private void saveData() {
        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if (database == null) {
            printMsg("Database is not exist");
            return;
        }

        String sql = "INSERT INTO " + TABLE_NAME +
                "(" +
                BOOK_TITLE + ", " +
                BOOK_AUTHOR + ", " +
                BOOK_CONTENT +
                ") " +
                "VALUES(" +
                "'" + title + "', " +
                "'" + author + "', " +
                "'" + content + "'" +
                ")";

        database.execSQL(sql);
        printMsg(title + ", " + author + ", " + content + " saved.");
    }

    private void getData() {
        Cursor cursor;

        if (database == null) {
            printMsg("Database is not exist");
            return;
        }

        cursor = database.query(TABLE_NAME,
                new String[]{BOOK_TITLE, BOOK_AUTHOR, BOOK_CONTENT},
                null,
                null,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.title = cursor.getString(cursor.getColumnIndex(BOOK_TITLE));
                book.author = cursor.getString(cursor.getColumnIndex(BOOK_AUTHOR));
                book.content = cursor.getString(cursor.getColumnIndex(BOOK_CONTENT));
                bookList.add(book);
            } while (cursor.moveToNext());

            cursor.close();
        }

        if(bookList.size() > 0) {
            Book book = bookList.get(bookList.size() - 1);
            titleEditText.setText(book.title);
            authorEditText.setText(book.author);
            contentEditText.setText(book.content);
        }

        printMsg("Get books all " + bookList.size());
    }

    private void printMsg(String msg) {
        new Handler(Looper.getMainLooper()).post(() -> {
            debugTextView.append(msg + "\n");
        });
    }

    private void createDdatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BOOK_TITLE + " TEXT, " +
                BOOK_AUTHOR + " TEXT, " +
                BOOK_CONTENT + " TEXT" +
                ")";


        database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        database.execSQL(sql);
    }

    class Book {
        public String title;
        public String author;
        public String content;
    }
}

