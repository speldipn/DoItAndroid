package com.tpmn.doitandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class OptionActivity extends AppCompatActivity {

    LinearLayout mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        mainView = findViewById(R.id.mainView);
        registerForContextMenu(mainView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        View view = menu.findItem(R.id.menu_search).getActionView();
        if(view != null) {
            EditText editText = view.findViewById(R.id.editText);
            if(editText != null) {
                editText.setOnEditorActionListener((v, actionId, event) -> {
                    String msg = editText.getText().toString() + " 입력됨";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    return true;
                });
            }
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.one: msg = getString(R.string.one); break;
//            case R.id.two: msg = getString(R.string.two); break;
//            case R.id.three: msg = getString(R.string.three); break;
            default:
        }
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.first: msg = getString(R.string.first); break;
            case R.id.second: msg = getString(R.string.second); break;
            case R.id.third: msg = getString(R.string.third); break;
            default:
        }
        if (!TextUtils.isEmpty(msg)) {
            Snackbar.make(mainView, msg, Snackbar.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}