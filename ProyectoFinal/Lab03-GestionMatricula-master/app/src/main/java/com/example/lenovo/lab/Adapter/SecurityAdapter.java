package com.example.lenovo.lab.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.lab.LogicaNeg.User;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 21/03/2018.
 */

public class SecurityAdapter extends RecyclerView.Adapter<SecurityAdapter.MyViewHolder> implements Filterable {
  private List<User> userList;
  private List<User> userListFiltered;
  private SeguridadAdapterListener listener;
  private User deletedItem;

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
          listener.onContactSelected(userListFiltered.get(getAdapterPosition()));
        }
      });
    }
  }

  public SecurityAdapter(List<User> userList, SeguridadAdapterListener listener) {
    this.userList = userList;
    this.listener = listener;
    //init filter
    this.userListFiltered = userList;
  }

  @Override
  public SecurityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.item_list_row, parent, false);

    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(SecurityAdapter.MyViewHolder holder, int position) {
    // rendering view
    final User user = userListFiltered.get(position);
    holder.titulo1.setText(user.getEmail());
    holder.titulo2.setText(user.getRole());
    holder.description.setText("Password: " + user.getPassword());
  }

  @Override
  public int getItemCount() {
    return userListFiltered.size();
  }

  public void removeItem(int position) {
    deletedItem = userListFiltered.remove(position);
    Iterator<User> iter = userList.iterator();
    while (iter.hasNext()) {
      User aux = iter.next();
      if (deletedItem.equals(aux))
        iter.remove();
    }
    // notify item removed
    notifyItemRemoved(position);
  }

  public void restoreItem(int position) {

    if (userListFiltered.size() == userList.size()) {
      userListFiltered.add(position, deletedItem);
    } else {
      userListFiltered.add(position, deletedItem);
      userList.add(deletedItem);
    }
    notifyDataSetChanged();
    // notify item added by position
    notifyItemInserted(position);
  }

  public User getSwipedItem(int index) {
    if (this.userList.size() == this.userListFiltered.size()) { //not filtered yet
      return userList.get(index);
    } else {
      return userListFiltered.get(index);
    }
  }

  public void onItemMove(int fromPosition, int toPosition) {
    if (userList.size() == userListFiltered.size()) { // without filter
      if (fromPosition < toPosition) {
        for (int i = fromPosition; i < toPosition; i++) {
          Collections.swap(userList, i, i + 1);
        }
      } else {
        for (int i = fromPosition; i > toPosition; i--) {
          Collections.swap(userList, i, i - 1);
        }
      }
    } else {
      if (fromPosition < toPosition) {
        for (int i = fromPosition; i < toPosition; i++) {
          Collections.swap(userListFiltered, i, i + 1);
        }
      } else {
        for (int i = fromPosition; i > toPosition; i--) {
          Collections.swap(userListFiltered, i, i - 1);
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
          userListFiltered = userList;
        } else {
          List<User> filteredList = new ArrayList<>();
          for (User row : userList) {
            // filter use two parameters
            if (row.getEmail().toLowerCase().contains(charString.toLowerCase())) {
              filteredList.add(row);
            }
          }

          userListFiltered = filteredList;
        }

        FilterResults filterResults = new FilterResults();
        filterResults.values = userListFiltered;
        return filterResults;
      }

      @Override
      protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        userListFiltered = (ArrayList<User>) filterResults.values;
        notifyDataSetChanged();
      }
    };
  }

  public interface SeguridadAdapterListener {
    void onContactSelected(User user);
  }
}