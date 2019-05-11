package com.example.lenovo.lab.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lenovo.lab.LogicaNeg.Categoria;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Luis Carrillo Rodriguez on 17/3/2018.
 */

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.MyViewHolder> implements Filterable {
    private List<Categoria> categoriaList;
    private List<Categoria> categoriaListFiltered;
    private CarreraAdapterListener listener;
    private Categoria deletedItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title1, title2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

        public MyViewHolder(View view) {
            super(view);
            title1 = view.findViewById(R.id.titleFirstLbl);
            title2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(categoriaListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public CategoriasAdapter(List<Categoria> categoriaList, CarreraAdapterListener listener) {
        this.categoriaList = categoriaList;
        this.listener = listener;
        //init filter
        this.categoriaListFiltered = categoriaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // basically a render
        final Categoria categoria = categoriaListFiltered.get(position);
        holder.title1.setText(categoria.getCodigo());
        holder.title2.setText(categoria.getNombre());
    }

    @Override
    public int getItemCount() {
        return categoriaListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = categoriaListFiltered.remove(position);
        Iterator<Categoria> iter = categoriaList.iterator();
        while (iter.hasNext()) {
            Categoria aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (categoriaListFiltered.size() == categoriaList.size()) {
            categoriaListFiltered.add(position, deletedItem);
        } else {
            categoriaListFiltered.add(position, deletedItem);
            categoriaList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Categoria getSwipedItem(int index) {
        if (this.categoriaList.size() == this.categoriaListFiltered.size()) { //not filtered yet
            return categoriaList.get(index);
        } else {
            return categoriaListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (categoriaList.size() == categoriaListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(categoriaList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(categoriaList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(categoriaListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(categoriaListFiltered, i, i - 1);
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
                    categoriaListFiltered = categoriaList;
                } else {
                    List<Categoria> filteredList = new ArrayList<>();
                    for (Categoria row : categoriaList) {
                        // filter use two parameters
                        if (row.getCodigo().toLowerCase().contains(charString.toLowerCase()) || row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    categoriaListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = categoriaListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                categoriaListFiltered = (ArrayList<Categoria>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CarreraAdapterListener {
        void onContactSelected(Categoria categoria);
    }
}