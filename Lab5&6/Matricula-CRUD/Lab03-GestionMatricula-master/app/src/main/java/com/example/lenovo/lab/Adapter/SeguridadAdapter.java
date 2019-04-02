package com.example.lenovo.lab.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.lab.LogicaNeg.Usuario;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 21/03/2018.
 */

public class SeguridadAdapter extends RecyclerView.Adapter<SeguridadAdapter.MyViewHolder> implements Filterable {
    private List<Usuario> usuarioList;
    private List<Usuario> usuarioListFiltered;
    private SeguridadAdapterListener listener;
    private Usuario deletedItem;

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
                    listener.onContactSelected(usuarioListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public SeguridadAdapter(List<Usuario> usuarioList, SeguridadAdapterListener listener) {
        this.usuarioList = usuarioList;
        this.listener = listener;
        //init filter
        this.usuarioListFiltered = usuarioList;
    }

    @Override
    public SeguridadAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SeguridadAdapter.MyViewHolder holder, int position) {
        // rendering view
        final Usuario usuario = usuarioListFiltered.get(position);
        holder.titulo1.setText(usuario.getCorreo());
        holder.titulo2.setText(usuario.getPrivilegio());
        holder.description.setText("Contraseña: " + usuario.getContraseña());
    }

    @Override
    public int getItemCount() {
        return usuarioListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = usuarioListFiltered.remove(position);
        Iterator<Usuario> iter = usuarioList.iterator();
        while (iter.hasNext()) {
            Usuario aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (usuarioListFiltered.size() == usuarioList.size()) {
            usuarioListFiltered.add(position, deletedItem);
        } else {
            usuarioListFiltered.add(position, deletedItem);
            usuarioList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Usuario getSwipedItem(int index) {
        if (this.usuarioList.size() == this.usuarioListFiltered.size()) { //not filtered yet
            return usuarioList.get(index);
        } else {
            return usuarioListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (usuarioList.size() == usuarioListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(usuarioList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(usuarioList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(usuarioListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(usuarioListFiltered, i, i - 1);
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
                    usuarioListFiltered = usuarioList;
                } else {
                    List<Usuario> filteredList = new ArrayList<>();
                    for (Usuario row : usuarioList) {
                        // filter use two parameters
                        if (row.getCorreo().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    usuarioListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = usuarioListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                usuarioListFiltered = (ArrayList<Usuario>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface SeguridadAdapterListener {
        void onContactSelected(Usuario usuario);
    }
}