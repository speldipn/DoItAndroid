package com.tpmn.doitandroid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class UserInfoFragment extends Fragment {

    EditText nameEditText;
    EditText ageEditText;
    Button birthButton;
    Button saveButton;

    public UserInfoFragment() { /* Required empty public constructor */ }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup();
    }

    private void setup() {
        View view = getView();
        assert view != null;
        nameEditText = view.findViewById(R.id.nameEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
        birthButton = view.findViewById(R.id.birthButtton);
        saveButton = view.findViewById(R.id.saveButtton);

        birthButton.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    (view1, year, month, dayOfMonth) -> {
                        String currentDate = String.format("%d-%d-%d", year, month+1, dayOfMonth);
                        Toast.makeText(getContext(), currentDate, Toast.LENGTH_SHORT).show();
                        birthButton.setText(currentDate);
                    },
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        saveButton.setOnClickListener(v -> {
            String infoString = String.format("%s\n%s\n%s", nameEditText.getText().toString(), ageEditText.getText().toString(), birthButton.getText().toString());
            Toast.makeText(getContext(), infoString, Toast.LENGTH_SHORT).show();
        });
    }
}