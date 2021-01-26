package com.tpmn.doitandroid;

import android.Manifest;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

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

    TabLayout tabLayout;
    RecyclerView recyclerView;
    EditText titleEditText;
    EditText authorEditText;
    EditText contentEditText;
    Button saveButton;
    TextView debugTextView;

    View selectView;

    SQLiteDatabase database;

    List<Book> bookList = new ArrayList<>();
    BookAdapter bookAdapter;

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
        tabLayout = findViewById(R.id.tabLayout);
        recyclerView = findViewById(R.id.recyclerView);
        selectView = findViewById(R.id.selectView);

        bookAdapter = new BookAdapter();
        recyclerView.setAdapter(bookAdapter);

        setTabs();
        createDdatabase();
        getData();
    }

    private void setTabs() {
        tabLayout.addTab(tabLayout.newTab()
                .setTag("input")
                .setText("입력")
        );

        tabLayout.addTab(tabLayout.newTab()
                .setTag("select")
                .setText("조회")
        );

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tag = (String) tab.getTag();
                switch (tag) {
                    case "select": selectView.setVisibility(View.VISIBLE); break;
                    default: selectView.setVisibility(View.GONE); break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

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
        getData();
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

        bookList.clear();
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

        if (bookList.size() > 0) {
            Book book = bookList.get(bookList.size() - 1);
            titleEditText.setText(book.title);
            authorEditText.setText(book.author);
            contentEditText.setText(book.content);
        }

        bookAdapter.setDataAndRefresh(bookList);

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

