package com.emransac.rviewbutton;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Producto> productoArrayList;
    private itemClickListener itemClickListener;
    private TextInputListener textInputListener; // Agregado el campo TextInputListener

    public mAdapter(Context context, ArrayList<Producto> productoArrayList, itemClickListener itemClickListener, TextInputListener textInputListener) {
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
            if (pedido.equals("0")) {
                itemClickListener.onItemClick(getAdapterPosition());
                textInputListener.onTextInputClicked(nombre,inventario,pedido);
                mAdapter.this.removeItem(getAdapterPosition());
            } else {
                // No se cumple la condición, no se elimina el elemento
            }
            //itemClickListener.onItemClick(getAdapterPosition());
            //textInputListener.onTextInputClicked(nombre, stock);
            //mAdapter.this.removeItem(getAdapterPosition());
        }
    }

    public interface itemClickListener {
        void onItemClick(int position);
    }

    public interface TextInputListener {
        void onTextInputClicked(String nombre, String inventario, String pedido);
    }
}
