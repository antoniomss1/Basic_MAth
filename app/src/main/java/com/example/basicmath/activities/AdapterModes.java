package com.example.basicmath.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicmath.R;
import com.example.basicmath.models.Mode;
import com.example.basicmath.models.ModeInfo;

import java.util.List;

public class AdapterModes extends RecyclerView.Adapter<AdapterModes.ViewHolder> {

    private final List<ModeInfo> modeList;
    private final OnModeClickListener listener;

    // Listener para clique no item ou no botão
    public interface OnModeClickListener {
        void onModeClick(Mode mode);
        void onModeButtonClick(Mode mode);
    }

    public AdapterModes(List<ModeInfo> modeList, OnModeClickListener listener) {
        this.modeList = modeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mode, parent, false);  // seu XML
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModeInfo mode = modeList.get(position);

        holder.title.setText(mode.getTitle());
        holder.description.setText(mode.getDescription());
        holder.icon.setImageResource(mode.getIconResId());

        // Clique no item inteiro
//        holder.itemView.setOnClickListener(v ->
////                listener.onModeClick(mode)
//        );
//
//        // Clique no imageButton lateral
//        holder.button.setOnClickListener(v ->
////                listener.onModeButtonClick(mode)
//        );
    }

    @Override
    public int getItemCount() {
        return modeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        TextView description;
        ImageButton button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.textView6);
            description = itemView.findViewById(R.id.textView7);
            button = itemView.findViewById(R.id.imageButton);
        }
    }
}
