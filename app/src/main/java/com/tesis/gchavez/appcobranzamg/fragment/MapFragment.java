package com.tesis.gchavez.appcobranzamg.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.activity.SelectclienteActivity;
import com.tesis.gchavez.appcobranzamg.adapters.ClientesAdapter;
import com.tesis.gchavez.appcobranzamg.models.Cliente;
import com.tesis.gchavez.appcobranzamg.service.ApiService;
import com.tesis.gchavez.appcobranzamg.service.ApiServiceGenerator;
import com.tesis.gchavez.appcobranzamg.util.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class MapFragment extends DialogFragment implements OnMapReadyCallback,GoogleMap.OnMyLocationClickListener, View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;

    private List<Marker> markerList = new ArrayList<Marker>();

    private GoogleMap mMap;
    private TextView fchAct;
    private RecyclerView clientesList;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
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
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        ProgressBar progressBar = view.findViewById(R.id.main_progress);

        Button program = view.findViewById(R.id.btn_programClient);
        program.setOnClickListener(this);

        //fecha actual
        Date d=new Date();
        fchAct= view.findViewById(R.id.txt_fchAct);
        SimpleDateFormat fecc=new SimpleDateFormat("dd-MM-yyyy");
        String fechacComplString = fecc.format(d);
        fchAct.setText(fechacComplString);

        //lista de clientes en el recyclerView
        clientesList = view.findViewById(R.id.list_client);
        clientesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        clientesList.setAdapter(new ClientesAdapter());
        initialize(fechacComplString);

        return view;
    }

    public void initialize(String fchcob){
        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        String userid = PreferencesManager.getInstance().get(PreferencesManager.PREF_ID);

        Call<List<Cliente>> call = service.getCliente(fchcob,userid);

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Cliente> rescliente = response.body();
                        Log.d(TAG, "res_client: " + rescliente);

                        ClientesAdapter adapter = (ClientesAdapter) clientesList.getAdapter();
                        adapter.setClientes(rescliente);
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
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                // ProgressBar Gone
                getActivity().findViewById(R.id.main_progress).setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();

            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.setTrafficEnabled(true);
        mMap.setOnMyLocationClickListener(this);

    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Double lat = location.getLatitude();
        Double lon = location.getLongitude();
        Log.d(TAG, "lat: " + lat +"\n long: " + lon);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SelectclienteActivity.class);
        getActivity().startActivity(intent);
    }
}
