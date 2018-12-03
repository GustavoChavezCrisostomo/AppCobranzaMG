package com.tesis.gchavez.appcobranzamg.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tesis.gchavez.appcobranzamg.R;


public class ClientDetailFragment extends Fragment {


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_detail, container, false);
    }

}
