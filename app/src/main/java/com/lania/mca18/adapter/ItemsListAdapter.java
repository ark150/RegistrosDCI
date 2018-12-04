package com.lania.mca18.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lania.mca18.model.Equipo;
import com.lania.mca18.model.Item;
import com.lania.mca18.model.Persona;
import com.lania.mca18.registrosdci.R;
import com.lania.mca18.registrosdci.RegistroESFragment;
import com.lania.mca18.registrosdci.VerificacionActivity;

import java.util.List;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ItemViewHolder> {
    private List<Item> items;
    private int band;
    Context context;


    public static class ItemViewHolder extends RecyclerView.ViewHolder
    {
        // Inicalizar elementos de la interfaz
        public ImageView imagen;
        public TextView txvTitle, txvSecondary;
        public RelativeLayout rvItems;

        public ItemViewHolder(View view)
        {
            super(view);
            imagen = (ImageView)view.findViewById(R.id.Item_recycler_image);
            txvTitle =(TextView)view.findViewById(R.id.txvTitle);
            txvSecondary = (TextView)view.findViewById(R.id.txvSecText);
            rvItems = (RelativeLayout)view.findViewById(R.id.rv_items_list);
        }
    }

    public ItemsListAdapter(List<Item> items)
    {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.items_list_card, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int i) {
        // Asigna recursos
        //viewHolder.imagen.setImageResource();

        if(this.items.get(i) instanceof Persona)
        {
            viewHolder.txvTitle.setText(((Persona) this.items.get(i)).getNombre());
            viewHolder.txvSecondary.setText(((Persona)
                    this.items.get(i)).getEquipo().getNombre());
            band=0;
        } else if(this.items.get(i) instanceof Equipo)
        {
            viewHolder.txvTitle.setText(((Equipo) this.items.get(i)).getNombre());
            band=1;
        }
        viewHolder.rvItems.setTag(this.items.get(i).getId());
        viewHolder.rvItems.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                // Evento OnClick
                if(band==0){
                    Toast.makeText(view.getContext(),
                            "ID: " + String.valueOf(view.getTag()),
                            Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(view.getContext(), RegistroESFragment.class);
                    i.putExtra("idPersona", String.valueOf(view.getTag()));
                    view.getContext().startActivity(i);
                }else if(band==1){
                    Toast.makeText(view.getContext(),
                            "ID: " + String.valueOf(view.getTag()),
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(view.getContext(),VerificacionActivity.class);
                    i.putExtra("idEquipo", String.valueOf(view.getTag()));
                    view.getContext().startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
