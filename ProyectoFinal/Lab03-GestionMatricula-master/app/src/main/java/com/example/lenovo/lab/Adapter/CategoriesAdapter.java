package com.example.lenovo.lab.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.lenovo.lab.LogicaNeg.Category;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Luis Carrillo Rodriguez on 17/3/2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> implements Filterable {
  private List<Category> categoriesList;
  private List<Category> categoriesListFiltered;
  private CategoryAdapterListener listener;
  private Category deletedItem;

  public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title1, title2;
    //two layers
    public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

    public MyViewHolder(View view) {
      super(view);
      title1 = view.findViewById(R.id.titleCodeLbl);
      title2 = view.findViewById(R.id.titleNameLbl);
      viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
      viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
      viewForeground = view.findViewById(R.id.view_foreground);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          // send selected contact in callback
          listener.onContactSelected(categoriesListFiltered.get(getAdapterPosition()));
        }
      });
    }
  }

  public CategoriesAdapter(List<Category> categoriesList, CategoryAdapterListener listener) {
    this.categoriesList = categoriesList;
    this.listener = listener;
    this.categoriesListFiltered = categoriesList;
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
    final Category category = categoriesListFiltered.get(position);
    holder.title1.setText(category.getCode());
    holder.title2.setText(category.getName());
  }

  @Override
  public int getItemCount() {
    return categoriesListFiltered.size();
  }

  public void removeItem(int position) {
    deletedItem = categoriesListFiltered.remove(position);
    Iterator<Category> iter = categoriesList.iterator();
    while (iter.hasNext()) {
      Category aux = iter.next();
      if (deletedItem.equals(aux))
        iter.remove();
    }
    // notify item removed
    notifyItemRemoved(position);
  }

  public void restoreItem(int position) {

    if (categoriesListFiltered.size() == categoriesList.size()) {
      categoriesListFiltered.add(position, deletedItem);
    } else {
      categoriesListFiltered.add(position, deletedItem);
      categoriesList.add(deletedItem);
    }
    notifyDataSetChanged();
    // notify item added by position
    notifyItemInserted(position);
  }

  public Category getSwipedItem(int index) {
    if (this.categoriesList.size() == this.categoriesListFiltered.size()) { //not filtered yet
      return categoriesList.get(index);
    } else {
      return categoriesListFiltered.get(index);
    }
  }

  public void onItemMove(int fromPosition, int toPosition) {
    if (categoriesList.size() == categoriesListFiltered.size()) { // without filter
      if (fromPosition < toPosition) {
        for (int i = fromPosition; i < toPosition; i++) {
          Collections.swap(categoriesList, i, i + 1);
        }
      } else {
        for (int i = fromPosition; i > toPosition; i--) {
          Collections.swap(categoriesList, i, i - 1);
        }
      }
    } else {
      if (fromPosition < toPosition) {
        for (int i = fromPosition; i < toPosition; i++) {
          Collections.swap(categoriesListFiltered, i, i + 1);
        }
      } else {
        for (int i = fromPosition; i > toPosition; i--) {
          Collections.swap(categoriesListFiltered, i, i - 1);
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
          categoriesListFiltered = categoriesList;
        } else {
          List<Category> filteredList = new ArrayList<>();
          for (Category row : categoriesList) {
            // filter use two parameters
            if (row.getCode().toLowerCase().contains(charString.toLowerCase()) || row.getName().toLowerCase().contains(charString.toLowerCase())) {
              filteredList.add(row);
            }
          }

          categoriesListFiltered = filteredList;
        }

        FilterResults filterResults = new FilterResults();
        filterResults.values = categoriesListFiltered;
        return filterResults;
      }

      @Override
      protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        categoriesListFiltered = (ArrayList<Category>) filterResults.values;
        notifyDataSetChanged();
      }
    };
  }

  public interface CategoryAdapterListener {
    void onContactSelected(Category category);
  }
}