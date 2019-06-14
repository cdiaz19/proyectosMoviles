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
import com.example.lenovo.lab.LogicaNeg.Order;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 21/03/2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> implements Filterable {
    private List<Order> orderList;
    private List<Order> orderListFiltered;
    private OrderAdapterListener listener;
    private Order deletedItem;

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
                    listener.onContactSelected(orderListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public OrderAdapter(List<Order> orderList, OrderAdapterListener listener) {
        this.orderList = orderList;
        this.listener = listener;
        //init filter
        this.orderListFiltered = orderList;
    }

    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.MyViewHolder holder, int position) {
        // rendering view
        final Order order = orderListFiltered.get(position);
        holder.titulo1.setText(order.getVideoGame().getCodigoJuego() + ", cantidad: "+ order.getCantidad());
        holder.titulo2.setText(order.getClient().getUser().getCedula());
        holder.description.setText("Fecha: " + order.getFecha() + ", Monto: "+ order.getTotal());
    }

    @Override
    public int getItemCount() {
        return orderListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = orderListFiltered.remove(position);
        Iterator<Order> iter = orderList.iterator();
        while (iter.hasNext()) {
            Order aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (orderListFiltered.size() == orderList.size()) {
            orderListFiltered.add(position, deletedItem);
        } else {
            orderListFiltered.add(position, deletedItem);
            orderList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Order getSwipedItem(int index) {
        if (this.orderList.size() == this.orderListFiltered.size()) { //not filtered yet
            return orderList.get(index);
        } else {
            return orderListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (orderList.size() == orderListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(orderList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(orderList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(orderListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(orderListFiltered, i, i - 1);
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
                    orderListFiltered = orderList;
                } else {
                    List<Order> filteredList = new ArrayList<>();
                    for (Order row : orderList) {
                        // filter use two parameters
                        if (row.getFecha().toLowerCase().contains(charString.toLowerCase()) || row.getFecha().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    orderListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = orderListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                orderListFiltered = (ArrayList<Order>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface OrderAdapterListener {
        void onContactSelected(Order order);
    }
}
