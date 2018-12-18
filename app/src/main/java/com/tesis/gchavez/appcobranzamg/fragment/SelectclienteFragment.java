package com.tesis.gchavez.appcobranzamg.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tesis.gchavez.appcobranzamg.R;

import androidx.fragment.app.DialogFragment;

public class SelectclienteFragment extends DialogFragment {

    public SelectclienteFragment() {
        // Required empty public constructor
    }

    public static SelectclienteFragment newInstance() {
        SelectclienteFragment fragment = new SelectclienteFragment();
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
        return inflater.inflate(R.layout.fragment_selectcliente, container, false);
    }
}
