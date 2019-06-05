package com.example.lenovo.lab.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by HsingPC on 26/3/2018.
 */

public class MatriculaAdapter extends RecyclerView.Adapter<MatriculaAdapter.MyViewHolder> {

    private MatriculaAdapterListener listener;
    private Grupo deletedItem;
    private List<Grupo> matriculaListFiltered;
    private List<Grupo> matriculaList;

    public MatriculaAdapter(List<Grupo> matriculaList, MatriculaAdapterListener listener){
        this.matriculaList = matriculaList;
        this.listener = listener;
        this.matriculaListFiltered = matriculaList;
    }

    @Override
    public MatriculaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MatriculaAdapter.MyViewHolder holder, int position) {
        final Grupo grupo = matriculaListFiltered.get(position);
        holder.titulo1.setText(grupo.getCurso());
        holder.titulo2.setText(grupo.getNumero());
        holder.description.setText(grupo.getHorario());
    }

    @Override
    public int getItemCount() {
        return matriculaListFiltered.size();
    }

    public void removeItem(int position, String ced) {
        deletedItem = matriculaListFiltered.remove(position);
        for(Grupo i: matriculaList){
            if(i.equals(deletedItem))
                i.eliminarAlumno(ced);
        }
        // notify item removed
        notifyItemRemoved(position);
    }
    public void restoreItem(int position) {

        if (matriculaListFiltered.size() == matriculaList.size()) {
            matriculaListFiltered.add(position, deletedItem);
        } else {
            matriculaListFiltered.add(position, deletedItem);
            matriculaList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Grupo getSwipedItem(int index) {
        if (this.matriculaList.size() == this.matriculaListFiltered.size()) { //not filtered yet
            return matriculaList.get(index);
        } else {
            return matriculaListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (matriculaList.size() == matriculaListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(matriculaList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(matriculaList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(matriculaListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(matriculaListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo1, titulo2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo1 = itemView.findViewById(R.id.titleFirstLbl);
            titulo2 = itemView.findViewById(R.id.titleSecLbl);
            description = itemView.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = itemView.findViewById(R.id.view_background_delete);
            //viewBackgroundEdit = itemView.findViewById(R.id.view_background_edit);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(matriculaListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface MatriculaAdapterListener {
        void onContactSelected(Grupo grupo);
    }
}
