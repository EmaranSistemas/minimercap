package com.emransac.rviewbutton;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Producto> productoArrayList;
    private itemClickListener itemClickListener;
    private TextInputListener textInputListener; // Agregado el campo TextInputListener

    public Adapter(Context context, ArrayList<Producto> productoArrayList, itemClickListener itemClickListener, TextInputListener textInputListener) {
        this.context = context;
        this.productoArrayList = productoArrayList;
        this.itemClickListener = itemClickListener;
        this.textInputListener = textInputListener; // Asignado el TextInputListener
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new ViewHolder(view, itemClickListener, textInputListener); // Pasado el TextInputListener al constructor del ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = productoArrayList.get(position);

        holder.txtid.setText(producto.getId());
        holder.txtNombre.setText(producto.getNombre());
        holder.txtInventario.setText(producto.getInventario());
        holder.txtPedido.setText(producto.getPedido());
        Glide.with(context)
                .load(producto.getImg())
                .into(holder.imageView);

        holder.txtInventario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    holder.txtInventario.setText("");
                }
            }
        });

        holder.txtPedido.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    holder.txtPedido.setText("");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productoArrayList.size();
    }

    public void removeItem(int position) {
        productoArrayList.remove(position);
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtid;
        TextView txtNombre;
        TextView txtInventario;
        TextView txtPedido;
        ImageView imageView;
        Button button;
        itemClickListener itemClickListener;
        TextInputListener textInputListener;

        public ViewHolder(@NonNull View itemView, itemClickListener itemClickListener, TextInputListener textInputListener) {
            super(itemView);

            txtid = itemView.findViewById(R.id.id);
            txtNombre = itemView.findViewById(R.id.nombre);
            txtInventario = itemView.findViewById(R.id.inventario);
            txtPedido = itemView.findViewById(R.id.pedido);
            imageView = itemView.findViewById(R.id.imageView);
            button = itemView.findViewById(R.id.button);

            this.itemClickListener = itemClickListener;
            this.textInputListener = textInputListener;

            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String nombre = txtNombre.getText().toString();
            String inventario = txtInventario.getText().toString();
            String pedido = txtPedido.getText().toString();
            if (!nombre.isEmpty() && !inventario.isEmpty() && !pedido.isEmpty()) {
                itemClickListener.onItemClick(getAdapterPosition());
                textInputListener.onTextInputClicked(nombre,inventario,pedido);
                Adapter.this.removeItem(getAdapterPosition());
            } else {
                if(nombre.isEmpty()){
                    Toast.makeText(context, "Ingrese nombre", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(inventario.isEmpty()){
                    Toast.makeText(context, "Ingrese inventario", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(pedido.isEmpty()){
                    Toast.makeText(context, "Ingrese pedido", Toast.LENGTH_SHORT).show();
                    return;
                }
                // No se cumple la condición, no se elimina el elemento
            }
            //itemClickListener.onItemClick(getAdapterPosition());
            //textInputListener.onTextInputClicked(nombre, stock);
            //Adapter.this.removeItem(getAdapterPosition());
        }
    }
    public interface itemClickListener {
        void onItemClick(int position);
    }

    public interface TextInputListener {
        void onTextInputClicked(String nombre, String inventario, String pedido);
    }

}
