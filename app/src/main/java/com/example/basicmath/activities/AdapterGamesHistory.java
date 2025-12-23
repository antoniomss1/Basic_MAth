package com.example.basicmath.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicmath.R;
import com.example.basicmath.models.GameHistory;

import java.util.List;

public class AdapterGamesHistory extends RecyclerView.Adapter<AdapterGamesHistory.ViewHolder> {

    private final List<GameHistory> historyList;
    private final OnHistoryClickListener listener;

    public interface OnHistoryClickListener {
        void onHistoryClick(GameHistory history);
    }

    public AdapterGamesHistory(List<GameHistory> historyList,
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
        GameHistory history = historyList.get(position);

        holder.numberInSequence.setText(
                String.valueOf(history.getNumberInSequence())
        );
        holder.date.setText(history.getDateTime());
        holder.numberOfProblems.setText(
                String.valueOf(history.getNumberOfProblems())
        );
        holder.average.setText(history.getAverageTime());
        holder.timeSpent.setText(history.getTimeSpent());
        holder.accuracy.setText(history.getAccuracy());
        holder.biggestSequence.setText(
                String.valueOf(history.getBiggestSequence())
        );

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onHistoryClick(history);
            }
        });
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
        TextView timeSpent;
        TextView accuracy;
        TextView biggestSequence;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numberInSequence = itemView.findViewById(
                    R.id.textViewNumberInSequency
            );
            date = itemView.findViewById(R.id.textViewDate);
            numberOfProblems = itemView.findViewById(
                    R.id.textViewNumberOfProblems
            );
            average = itemView.findViewById(R.id.textViewAverage);
            timeSpent = itemView.findViewById(
                    R.id.textViewTimeSpent
            );
            accuracy = itemView.findViewById(
                    R.id.textViewAccuracy
            );
            biggestSequence = itemView.findViewById(
                    R.id.textViewBiggestSequency
            );
        }
    }
}
