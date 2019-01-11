package com.tesis.gchavez.appcobranzamg.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tesis.gchavez.appcobranzamg.R;
import com.tesis.gchavez.appcobranzamg.ResponseMessage;
import com.tesis.gchavez.appcobranzamg.models.Cliente;
import com.tesis.gchavez.appcobranzamg.service.ApiService;
import com.tesis.gchavez.appcobranzamg.service.ApiServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

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
        public TextView idClienteText;
        private ImageButton btnBorrar;

        public ViewHolder(View itemView){
            super(itemView);
            clienteText = itemView.findViewById(R.id.txt_cliente);
            rucText = itemView.findViewById(R.id.txt_ruc);
            deudaText = itemView.findViewById(R.id.txt_deuda);
            idClienteText = itemView.findViewById(R.id.txt_idClient);
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
    public void onBindViewHolder(ClientesAdapter.ViewHolder viewHolder, final int position) {

        final Cliente cliente = this.clientes.get(position);
        viewHolder.clienteText.setText(String.valueOf(cliente.getNombre()));
        viewHolder.rucText.setText(String.valueOf(cliente.getRuc()));
        viewHolder.deudaText.setText(String.valueOf(cliente.getDeuda()));
        viewHolder.idClienteText.setText(String.valueOf(cliente.getId()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            }

        });

        viewHolder.btnBorrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View v) {
                final String idClient = cliente.getId().toString();
                String fchCobra = "-";

                ApiService service = ApiServiceGenerator.createService(ApiService.class);

                Call<ResponseMessage> call;

                call = service.addCliente(idClient, fchCobra);

                call.enqueue(new Callback<ResponseMessage>() {
                    @Override
                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                        try {

                            int statusCode = response.code();
                            Log.d(TAG, "HTTP status code: " + statusCode);

                            if (response.isSuccessful()) {

                                ResponseMessage responseMessage = response.body();
                                Log.d(TAG, "responseMessage: " + responseMessage);

                                clientes.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position,clientes.size());
                                Toast.makeText(v.getContext(), "Se elimino de la lista al " + cliente.getNombre(), Toast.LENGTH_LONG).show();

                            } else {
                                Log.e(TAG, "onError: " + response.errorBody().string());
                                throw new Exception("Error en el servicio");
                            }

                        } catch (Throwable t) {
                            try {
                                Log.e(TAG, "onThrowable: " + t.toString(), t);
                            }catch (Throwable x){
                                Log.e(TAG, "Error sistem: " + t.toString(), t);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.toString());
                    }

                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.clientes.size();
    }

}

