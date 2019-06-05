package com.example.lenovo.lab.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.lab.LogicaNeg.Client;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 21/03/2018.
 */

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder> implements Filterable {
    private List<Client> clientList;
    private List<Client> clientListFiltered;
    private ClientAdapterListener listener;
    private Client deletedItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo1, titulo2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;


        public MyViewHolder(View view) {
            super(view);
            titulo1 = view.findViewById(R.id.titleCodeLbl);
            titulo2 = view.findViewById(R.id.titleNameLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(clientListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public ClientAdapter(List<Client> clientList, ClientAdapterListener listener) {
        this.clientList = clientList;
        this.listener = listener;
        //init filter
        this.clientListFiltered = clientList;
    }

    @Override
    public ClientAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClientAdapter.MyViewHolder holder, int position) {
        // rendering view
        final Client client = clientListFiltered.get(position);
        holder.titulo1.setText(client.getNombre());
        holder.titulo2.setText(client.getUser().getCedula());
        holder.description.setText(client.getUser().getEmail());
    }

    @Override
    public int getItemCount() {
        return clientListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = clientListFiltered.remove(position);
        Iterator<Client> iter = clientList.iterator();
        while (iter.hasNext()) {
            Client aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (clientListFiltered.size() == clientList.size()) {
            clientListFiltered.add(position, deletedItem);
        } else {
            clientListFiltered.add(position, deletedItem);
            clientList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Client getSwipedItem(int index) {
        if (this.clientList.size() == this.clientListFiltered.size()) { //not filtered yet
            return clientList.get(index);
        } else {
            return clientListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (clientList.size() == clientListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(clientList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(clientList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(clientListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(clientListFiltered, i, i - 1);
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
                    clientListFiltered = clientList;
                } else {
                    List<Client> filteredList = new ArrayList<>();
                    for (Client row : clientList) {
                        // filter use two parameters
                        if (row.getNombre().toLowerCase().contains(charString.toLowerCase()) || row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    clientListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = clientListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                clientListFiltered = (ArrayList<Client>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ClientAdapterListener {
        void onContactSelected(Client client);
    }
}
