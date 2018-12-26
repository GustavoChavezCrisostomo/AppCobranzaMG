package com.tesis.gchavez.appcobranzamg.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.models.Cliente;
import com.tesis.gchavez.appcobranzamg.service.ApiService;
import com.tesis.gchavez.appcobranzamg.service.ApiServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class SelectclienteFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM2 = "buscar";

    // TODO: Rename and change types of parameters
    private String mBuscar;

    public ProgressBar progressBar;
    private TextView nameText;
    private TextView direcText;
    private TextView distriText;
    private TextView deudaText;
    private TextView fchVenText;
    private EditText obsText;


    public SelectclienteFragment() {
        // Required empty public constructor
    }

    public static SelectclienteFragment newInstance(String buscar) {
        SelectclienteFragment fragment = new SelectclienteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, buscar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBuscar = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_selectcliente, container, false);

        progressBar = view.findViewById(R.id.main_progress);
        nameText = view.findViewById(R.id.txt_name);
        direcText = view.findViewById(R.id.txt_direccion);
        distriText = view.findViewById(R.id.txt_distrito);
        deudaText = view.findViewById(R.id.txt_deuda);
        fchVenText = view.findViewById(R.id.txt_fchvenc);
        obsText = view.findViewById(R.id.txt_obs);

        resultRuc();

        return view;
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
                            cliente.add(client.getDireccion());
                            cliente.add(client.getDistrito());
                            cliente.add(client.getDeuda().toString());
                            cliente.add(client.getFchVenc());
                            cliente.add(client.getObservacion());
                        }

                        nameText.setText(cliente.get(1));
                        direcText.setText(cliente.get(2));
                        distriText.setText(cliente.get(3));
                        deudaText.setText(cliente.get(4));
                        fchVenText.setText(cliente.get(5));
                        obsText.setText(cliente.get(6));

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
