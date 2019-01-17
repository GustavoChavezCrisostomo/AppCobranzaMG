package com.tesis.gchavez.appcobranzamg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.activity.InformeActivity;
import com.tesis.gchavez.appcobranzamg.adapters.InfosAdapter;
import com.tesis.gchavez.appcobranzamg.models.Cobranza;
import com.tesis.gchavez.appcobranzamg.service.ApiService;
import com.tesis.gchavez.appcobranzamg.service.ApiServiceGenerator;
import com.tesis.gchavez.appcobranzamg.util.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class FormFragment extends DialogFragment implements View.OnClickListener {

    public String userid;
    public String fechacComplString;
    public String serie = "12564";

    private TextView fchAct;
    private TextView nameText;
    private RecyclerView infosList;

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

        //fecha actual
        SimpleDateFormat fecc=new SimpleDateFormat("dd-MM-yyyy");
        fechacComplString = fecc.format(d);
        fchAct.setText(fechacComplString);

        //nombre del cobrador
        String name = PreferencesManager.getInstance().get(PreferencesManager.PREF_FULLNAME);
        nameText.setText(name);
        //id user
        userid = PreferencesManager.getInstance().get(PreferencesManager.PREF_ID);

        //btn agregar info
        agregar.setOnClickListener(this);

        //lista de indoCobra en el recyclerView
        infosList = view.findViewById(R.id.list_info);
        infosList.setLayoutManager(new LinearLayoutManager(getActivity()));
        infosList.setAdapter(new InfosAdapter());
        initialize(fechacComplString);

        return view;
    }

    public void initialize(String fchact){
        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Cobranza>> call = service.getCobranza(fchact, userid);
        call.enqueue(new Callback<List<Cobranza>>() {
            @Override
            public void onResponse(Call<List<Cobranza>> call, Response<List<Cobranza>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Cobranza> rescobra = response.body();
                        Log.d(TAG, "res_cobranza: " + rescobra);

                        InfosAdapter adapter = (InfosAdapter) infosList.getAdapter();
                        adapter.setClientes(rescobra);
                        adapter.notifyDataSetChanged();

                        // ProgressBar Gone
                        getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);

                    } else {
                        // ProgressBar Gone
                        getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);

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
            public void onFailure(Call<List<Cobranza>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                // ProgressBar Gone
                getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), InformeActivity.class);
        getActivity().startActivity(intent);
    }
}
