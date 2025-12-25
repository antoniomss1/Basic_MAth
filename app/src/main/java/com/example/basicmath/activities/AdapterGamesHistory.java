package com.example.basicmath.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicmath.R;
import com.example.basicmath.models.GameData;

import java.util.ArrayList;
import java.util.List;

public class AdapterGamesHistory extends RecyclerView.Adapter<AdapterGamesHistory.ViewHolder> {

    private final List<GameData> historyList;
    private final OnHistoryClickListener listener;

    public interface OnHistoryClickListener {
        void onHistoryClick(GameData history);
    }

    public AdapterGamesHistory(List<GameData> historyList,
                               OnHistoryClickListener listener) {
        this.historyList = historyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_past_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameData history = historyList.get(position);

        holder.date.setText(history.getDateTime());
        holder.numberOfProblems.setText(
                String.valueOf(history.getNumberOfProblems())
        );
        holder.average.setText(String.valueOf(history.getAverageTime()));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onHistoryClick(history);
            }
        });
        holder.numberInSequence.setText(String.valueOf(history.getId()));
        holder.score.setText(String.valueOf(history.getScore()));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView numberInSequence;
        TextView date;
        TextView numberOfProblems;
        TextView average;
        TextView score;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numberInSequence = itemView.findViewById(
                    R.id.textViewID
            );
            date = itemView.findViewById(R.id.textViewDate);
            numberOfProblems = itemView.findViewById(
                    R.id.textViewNumberOfProblems
            );
            average = itemView.findViewById(R.id.textViewAverage);
            score = itemView.findViewById(R.id.textViewScore);

        }
    }

    public void updateList(ArrayList<GameData> newList) {
        this.historyList.clear();
        this.historyList.addAll(newList);
        notifyDataSetChanged();
    }

}
