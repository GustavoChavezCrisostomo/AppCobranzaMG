package com.tesis.gchavez.appcobranzamg.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.models.Cliente;
import com.tesis.gchavez.appcobranzamg.service.ApiService;
import com.tesis.gchavez.appcobranzamg.service.ApiServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class ClientDetailFragment extends DialogFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "tipe";
    private static final String ARG_PARAM2 = "buscar";

    // TODO: Rename and change types of parameters
    private String mTipe;
    private String mBuscar;

    public ProgressBar progressBar;
    private EditText dateInput;
    private TextView nameText;
    private TextView rucText;
    private TextView deudaText;
    private TextView fchVenText;
    private EditText obsText;


    public ClientDetailFragment() {
        // Required empty public constructor
    }

    public static ClientDetailFragment newInstance(String tipe, String buscar) {
        ClientDetailFragment fragment = new ClientDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, tipe);
        args.putString(ARG_PARAM2, buscar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTipe = getArguments().getString(ARG_PARAM1);
            mBuscar = getArguments().getString(ARG_PARAM2);
        }
    }

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

        progressBar = view.findViewById(R.id.main_progress);
        nameText = view.findViewById(R.id.txt_name);
        rucText = view.findViewById(R.id.txt_ruc);
        deudaText = view.findViewById(R.id.txt_deuda);
        fchVenText = view.findViewById(R.id.txt_fchvenc);
        obsText = view.findViewById(R.id.txt_obs);

        if (mTipe.equals("RUC")){
            resultRuc();
        }else{ //mTipe.equals("Nombre")
            resultName();
        }

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

    private void resultRuc(){
        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Cliente>> call = service.showRuc(mBuscar);

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Cliente> rescliente = response.body();
                        Log.d(TAG, "res_client: " + rescliente);

                        List<String> cliente = new ArrayList<String>();
                        for (Cliente client : rescliente) {
                            cliente.add(client.getId().toString());
                            cliente.add(client.getNombre());
                            cliente.add(client.getRuc());
                            cliente.add(client.getDeuda().toString());
                            cliente.add(client.getFchVenc());
                            cliente.add(client.getObservacion());
                        }

                        nameText.setText(cliente.get(1));
                        rucText.setText(cliente.get(2));
                        deudaText.setText(cliente.get(3));
                        fchVenText.setText(cliente.get(4));
                        obsText.setText(cliente.get(5));

                        // ProgressBar Gone
                        getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);

                    } else {
                        // ProgressBar Gone
                        getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);

                        getView().setVisibility(View.GONE);

                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Registro no encontrado");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                        // ProgressBar Gone
                        getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                // ProgressBar Gone
                getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);
            }
        });
    }

    private void resultName(){
        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Cliente>> call = service.showName(mBuscar);

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Cliente> rescliente = response.body();
                        Log.d(TAG, "res_client: " + rescliente);

                        List<String> cliente = new ArrayList<String>();
                        for (Cliente client : rescliente) {
                            cliente.add(client.getId().toString());
                            cliente.add(client.getNombre());
                            cliente.add(client.getRuc());
                            cliente.add(client.getDeuda().toString());
                            cliente.add(client.getFchVenc());
                            cliente.add(client.getObservacion());
                        }

                        nameText.setText(cliente.get(1));
                        rucText.setText(cliente.get(2));
                        deudaText.setText(cliente.get(3));
                        fchVenText.setText(cliente.get(4));
                        obsText.setText(cliente.get(5));

                        // ProgressBar Gone
                        getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);

                    } else {
                        // ProgressBar Gone
                        getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);

                        getView().setVisibility(View.GONE);

                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Registro no encontrado");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                        // ProgressBar Gone
                        getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                // ProgressBar Gone
                getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);
            }
        });
    }

}

