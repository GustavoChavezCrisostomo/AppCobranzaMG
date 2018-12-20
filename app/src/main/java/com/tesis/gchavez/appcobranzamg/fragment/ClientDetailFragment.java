package com.tesis.gchavez.appcobranzamg.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;

import java.util.Date;

public class ClientDetailFragment extends DialogFragment implements View.OnClickListener {

    public ClientDetailFragment() {
        // Required empty public constructor
    }

    public static ClientDetailFragment newInstance() {
        ClientDetailFragment fragment = new ClientDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private EditText dateInput;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_client_detail, container, false);

        dateInput = view.findViewById(R.id.date_input);
        ImageButton datePicker = view.findViewById(R.id.btn_calender);
        datePicker.setOnClickListener(this);

        Button program = view.findViewById(R.id.btn_program);
        program.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calender:
                showDatePickerDialog();
                break;
            case R.id.btn_program:
                programDate();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                dateInput.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void programDate() {
        String date = dateInput.getText().toString();

        if(date.isEmpty()){
            Toast.makeText(getActivity(), "Completar el campo fecha", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getActivity(), "Se programó cobranza para la fecha " + date, Toast.LENGTH_SHORT).show();
    }

}

