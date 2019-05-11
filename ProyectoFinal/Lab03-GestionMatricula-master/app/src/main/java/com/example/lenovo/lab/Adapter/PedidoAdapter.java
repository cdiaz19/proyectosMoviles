package com.example.lenovo.lab.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.lab.LogicaNeg.Pedido;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 21/03/2018.
 */

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder> implements Filterable {
    private List<Pedido> pedidoList;
    private List<Pedido> pedidoListFiltered;
    private CicloAdapterListener listener;
    private Pedido deletedItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo1, titulo2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;


        public MyViewHolder(View view) {
            super(view);
            titulo1 = view.findViewById(R.id.titleFirstLbl);
            titulo2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(pedidoListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public PedidoAdapter(List<Pedido> pedidoList, CicloAdapterListener listener) {
        this.pedidoList = pedidoList;
        this.listener = listener;
        //init filter
        this.pedidoListFiltered = pedidoList;
    }

    @Override
    public PedidoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PedidoAdapter.MyViewHolder holder, int position) {
        // rendering view
        final Pedido pedido = pedidoListFiltered.get(position);
        holder.titulo1.setText(pedido.getNombre() + "");
        holder.titulo2.setText(String.valueOf(pedido.getCantidad()));
        holder.description.setText(pedido.getRentor() + " - " + pedido.getPrecio());
    }

    @Override
    public int getItemCount() {
        return pedidoListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = pedidoListFiltered.remove(position);
        Iterator<Pedido> iter = pedidoList.iterator();
        while (iter.hasNext()) {
            Pedido aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (pedidoListFiltered.size() == pedidoList.size()) {
            pedidoListFiltered.add(position, deletedItem);
        } else {
            pedidoListFiltered.add(position, deletedItem);
            pedidoList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Pedido getSwipedItem(int index) {
        if (this.pedidoList.size() == this.pedidoListFiltered.size()) { //not filtered yet
            return pedidoList.get(index);
        } else {
            return pedidoListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (pedidoList.size() == pedidoListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(pedidoList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(pedidoList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(pedidoListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(pedidoListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    pedidoListFiltered = pedidoList;
                } else {
                    List<Pedido> filteredList = new ArrayList<>();
                    for (Pedido row : pedidoList) {
                        // filter use two parameters
                        if (row.getNombre().contains(charString)) {
                            filteredList.add(row);
                        }
                    }
                    pedidoListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = pedidoListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                pedidoListFiltered = (ArrayList<Pedido>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CicloAdapterListener {
        void onContactSelected(Pedido pedido);
    }
}
