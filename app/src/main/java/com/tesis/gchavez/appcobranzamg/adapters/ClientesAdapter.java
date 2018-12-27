package com.tesis.gchavez.appcobranzamg.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.models.Cliente;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ViewHolder> {

    private List<Cliente> clientes;

    public ClientesAdapter(){
        this.clientes = new ArrayList<>();
    }

    public void setClientes(List<Cliente> clientes){this.clientes = clientes;}

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView clienteText;
        public TextView rucText;
        public TextView deudaText;
        private ImageButton btnBorrar;

        public ViewHolder(View itemView){
            super(itemView);
            clienteText = itemView.findViewById(R.id.txt_cliente);
            rucText = itemView.findViewById(R.id.txt_ruc);
            deudaText = itemView.findViewById(R.id.txt_deuda);
            btnBorrar = itemView.findViewById(R.id.btn_remove);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClientesAdapter.ViewHolder viewHolder, int position) {

        final Cliente cliente = this.clientes.get(position);
        viewHolder.clienteText.setText(String.valueOf(cliente.getNombre()));
        viewHolder.rucText.setText(String.valueOf(cliente.getRuc()));
        viewHolder.deudaText.setText(String.valueOf(cliente.getDeuda()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            }

        });

        viewHolder.btnBorrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return this.clientes.size();
    }

}

