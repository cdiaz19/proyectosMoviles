package com.example.lenovo.lab.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.lab.LogicaNeg.VideoJuego;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 21/03/2018.
 */

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.MyViewHolder> implements Filterable {
    private List<VideoJuego> videoJuegoList;
    private List<VideoJuego> videoJuegoListFiltered;
    private CursoAdapterListener listener;
    private VideoJuego deletedItem;

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
                    listener.onContactSelected(videoJuegoListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public CursoAdapter(List<VideoJuego> cursolist, CursoAdapterListener listener) {
        this.videoJuegoList = cursolist;
        this.listener = listener;
        //init filter
        this.videoJuegoListFiltered = cursolist;
    }

    @Override
    public CursoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CursoAdapter.MyViewHolder holder, int position) {
        // rendering view
        final VideoJuego videoJuego = videoJuegoListFiltered.get(position);
        holder.titulo1.setText(videoJuego.getCodigo());
        holder.titulo2.setText(videoJuego.getNombre());

    }

    @Override
    public int getItemCount() {
        return videoJuegoListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = videoJuegoListFiltered.remove(position);
        Iterator<VideoJuego> iter = videoJuegoList.iterator();
        while (iter.hasNext()) {
            VideoJuego aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (videoJuegoListFiltered.size() == videoJuegoList.size()) {
            videoJuegoListFiltered.add(position, deletedItem);
        } else {
            videoJuegoListFiltered.add(position, deletedItem);
            videoJuegoList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public VideoJuego getSwipedItem(int index) {
        if (this.videoJuegoList.size() == this.videoJuegoListFiltered.size()) { //not filtered yet
            return videoJuegoList.get(index);
        } else {
            return videoJuegoListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (videoJuegoList.size() == videoJuegoListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(videoJuegoList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(videoJuegoList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(videoJuegoListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(videoJuegoListFiltered, i, i - 1);
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
                    videoJuegoListFiltered = videoJuegoList;
                } else {
                    List<VideoJuego> filteredList = new ArrayList<>();
                    for (VideoJuego row : videoJuegoList) {
                        // filter use two parameters
                        if (row.getCodigo().toLowerCase().contains(charString.toLowerCase()) || row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    videoJuegoListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = videoJuegoListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                videoJuegoListFiltered = (ArrayList<VideoJuego>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CursoAdapterListener {
        void onContactSelected(VideoJuego videoJuego);
    }
}
