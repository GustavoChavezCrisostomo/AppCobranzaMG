package com.tesis.gchavez.appcobranzamg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.activity.InformeActivity;
import com.tesis.gchavez.appcobranzamg.util.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.fragment.app.DialogFragment;

public class FormFragment extends DialogFragment implements View.OnClickListener {

    private TextView fchAct;
    private TextView nameText;

    public FormFragment() {
        // Required empty public constructor
    }

    public static FormFragment newInstance() {
        FormFragment fragment = new FormFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_form, container, false);

        Date d=new Date();
        nameText = view.findViewById(R.id.txt_name);
        fchAct = view.findViewById(R.id.txt_fchAct);
        Button agregar = view.findViewById(R.id.btn_agregar);

        SimpleDateFormat fecc=new SimpleDateFormat("dd/MM/yyyy");
        String fechacComplString = fecc.format(d);
        fchAct.setText(fechacComplString);

        String name = PreferencesManager.getInstance().get(PreferencesManager.PREF_FULLNAME);
        nameText.setText(name);

        agregar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), InformeActivity.class);
        getActivity().startActivity(intent);
    }
}
