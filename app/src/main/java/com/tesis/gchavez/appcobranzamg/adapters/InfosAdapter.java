package com.tesis.gchavez.appcobranzamg.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.models.Cobranza;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InfosAdapter extends RecyclerView.Adapter<InfosAdapter.ViewHolder> {

    private List<Cobranza> cobranzas;

    public InfosAdapter(){
        this.cobranzas = new ArrayList<>();
    }

    public void setClientes(List<Cobranza> clientes){this.cobranzas = clientes;}

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView clienteText;
        public TextView rucText;
        public TextView montoText;
        public TextView idCobraText;

        public ViewHolder(View itemView){
            super(itemView);
            clienteText = itemView.findViewById(R.id.txt_cliente);
            rucText = itemView.findViewById(R.id.txt_ruc);
            montoText = itemView.findViewById(R.id.txt_monto);
            idCobraText = itemView.findViewById(R.id.txt_idCobra);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InfosAdapter.ViewHolder viewHolder, int position) {

        final Cobranza cobranza = this.cobranzas.get(position);
        viewHolder.clienteText.setText(String.valueOf(cobranza.getCliente_id()));
        viewHolder.rucText.setText(String.valueOf(cobranza.getRucCli()));
        viewHolder.montoText.setText(String.valueOf(cobranza.getMonto()));
        viewHolder.idCobraText.setText(String.valueOf(cobranza.getId()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            }

        });

    }

    @Override
    public int getItemCount() {
        return this.cobranzas.size();
    }
}
