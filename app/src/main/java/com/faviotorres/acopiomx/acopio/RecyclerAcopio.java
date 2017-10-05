package com.faviotorres.acopiomx.acopio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faviotorres.acopiomx.R;
import com.faviotorres.acopiomx.model.Producto;
import com.faviotorres.acopiomx.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAcopio extends RecyclerView.Adapter<RecyclerAcopio.HolderProducto>{

    private Context context;
    private List<Producto> productos;

    RecyclerAcopio(Context context, List<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    @Override
    public int getItemCount() {
        return this.productos.size();
    }

    @Override
    public HolderProducto onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HolderProducto(LayoutInflater.from(context)
                .inflate(R.layout.row_producto, parent, false));
    }

    @Override
    public void onBindViewHolder(HolderProducto holder, int position) {
        Producto producto = this.productos.get(position);
        holder.nombreTV.setText(producto.getNombre());
        holder.fechaTV.setText(new StringBuilder("Actualizado - ")
                .append(DateUtils.formatDte(producto.getFechaDeActualizacion())));
    }

    class HolderProducto extends RecyclerView.ViewHolder {

        @BindView(R.id.row_producto_fecha_tv) TextView fechaTV;
        @BindView(R.id.row_producto_nombre_tv) TextView nombreTV;

        HolderProducto(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
