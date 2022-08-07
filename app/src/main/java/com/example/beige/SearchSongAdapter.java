package com.example.beige;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchSongAdapter extends RecyclerView.Adapter<SearchViewActivity> implements Filterable {

    public SearchSongAdapter(List<Song> songs) {
        this.songs = songs;
        this.songsFiltered = songs;
    }

    private final List<Song> songs;
    private List<Song> songsFiltered;
    Context context;

    @NonNull
    @Override
    public SearchViewActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.activity_search_view, parent, false);

        SearchViewActivity searchHolder = new SearchViewActivity(songView);

        return searchHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewActivity holder, int position) {
        Song song = songsFiltered.get(position);

        TextView title = holder.songTitle;
        title.setText(song.getTitle());

        TextView artist = holder.songArtist;
        artist.setText(song.getArtiste());

        ImageView songCoverImage = holder.songCoverImage;
        songCoverImage.setImageResource(song.getDrawable());
    }

    @Override
    public int getItemCount() {return songsFiltered.size();}

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();
                if(charString.isEmpty()){
                    songsFiltered = songs;
                }
                else {
                    List<Song> filteredList = new ArrayList<Song>();
                    for (int i = 0; i < songs.size(); i++) {
                        if(songs.get(i).getTitle().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(songs.get(i));
                        }
                    }
                    songsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = songsFiltered;
                notifyDataSetChanged();
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {

                songsFiltered = (List<Song>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
