package com.example.lenovo.lab.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.lab.LogicaNeg.VideoGame;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by HsingPC on 26/3/2018.
 */

public class VideoGameAdapter extends RecyclerView.Adapter<VideoGameAdapter.MyViewHolder> implements Filterable {

  private VideoGameAdapterListener listener;
  private VideoGame deletedItem;
  private List<VideoGame> videoGameListFiltered;
  private List<VideoGame> videoGamesList;

  public VideoGameAdapter(List<VideoGame> videoGamesList, VideoGameAdapterListener listener){
    this.videoGamesList = videoGamesList;
    this.listener = listener;
    this.videoGameListFiltered = videoGamesList;
  }

  @Override
  public VideoGameAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.item_list_row, parent, false);
    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(VideoGameAdapter.MyViewHolder holder, int position) {
    final VideoGame videoGame = videoGameListFiltered.get(position);

    holder.codeVG.setText(videoGame.getCode());
    holder.nameVG.setText(videoGame.getName());
    holder.priceVG.setText("Price: " + String.valueOf(videoGame.getPrice()));
  }

  @Override
  public int getItemCount() {
    return videoGameListFiltered.size();
  }

  @Override
  public Filter getFilter() {
    return new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence charSequence) {
        String charString = charSequence.toString();
        if (charString.isEmpty()) {
          videoGameListFiltered = videoGamesList;
        } else {
          List<VideoGame> filteredList = new ArrayList<>();
          for (VideoGame row : videoGamesList) {
            if (row.getCode().toLowerCase().contains(charString.toLowerCase()) || row.getCategory().getName().toLowerCase().contains(charSequence)) {
              filteredList.add(row);
            }
          }

          videoGameListFiltered = filteredList;
        }

        FilterResults filterResults = new FilterResults();
        filterResults.values = videoGameListFiltered;
        return filterResults;
      }

      @Override
      protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        videoGameListFiltered = (ArrayList<VideoGame>) filterResults.values;
        notifyDataSetChanged();
      }
    };
  }

  public void removeItem(int position) {
    deletedItem = videoGameListFiltered.remove(position);
    Iterator<VideoGame> iter = videoGamesList.iterator();
    while (iter.hasNext()) {
      VideoGame aux = iter.next();
      if (deletedItem.equals(aux))
        iter.remove();
    }
    notifyItemRemoved(position);
  }

  public void restoreItem(int position) {

    if (videoGameListFiltered.size() == videoGamesList.size()) {
      videoGameListFiltered.add(position, deletedItem);
    } else {
      videoGameListFiltered.add(position, deletedItem);
      videoGamesList.add(deletedItem);
    }
    notifyDataSetChanged();
    notifyItemInserted(position);
  }

  public VideoGame getSwipedItem(int index) {
    if (this.videoGamesList.size() == this.videoGameListFiltered.size()) {
      return videoGamesList.get(index);
    } else {
      return videoGameListFiltered.get(index);
    }
  }

  public void onItemMove(int fromPosition, int toPosition) {
    if (videoGamesList.size() == videoGameListFiltered.size()) {
      if (fromPosition < toPosition) {
        for (int i = fromPosition; i < toPosition; i++) {
          Collections.swap(videoGamesList, i, i + 1);
        }
      } else {
        for (int i = fromPosition; i > toPosition; i--) {
          Collections.swap(videoGamesList, i, i - 1);
        }
      }
    } else {
      if (fromPosition < toPosition) {
        for (int i = fromPosition; i < toPosition; i++) {
          Collections.swap(videoGameListFiltered, i, i + 1);
        }
      } else {
        for (int i = fromPosition; i > toPosition; i--) {
          Collections.swap(videoGameListFiltered, i, i - 1);
        }
      }
    }
    notifyItemMoved(fromPosition, toPosition);
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView codeVG, nameVG, priceVG;
    //two layers
    public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

    public MyViewHolder(View itemView) {
      super(itemView);
      codeVG = itemView.findViewById(R.id.titleCodeLbl);
      nameVG = itemView.findViewById(R.id.titleNameLbl);
      priceVG = itemView.findViewById(R.id.descriptionLbl);
      viewBackgroundDelete = itemView.findViewById(R.id.view_background_delete);
      viewBackgroundEdit = itemView.findViewById(R.id.view_background_edit);
      viewForeground = itemView.findViewById(R.id.view_foreground);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          listener.onContactSelected(videoGameListFiltered.get(getAdapterPosition()));
        }
      });
    }
  }

  public interface VideoGameAdapterListener {
    void onContactSelected(VideoGame grupo);
  }
}
