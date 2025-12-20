package com.example.basicmath.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicmath.R;
import com.example.basicmath.models.Mode;
import com.example.basicmath.models.ModeInfo;

import java.util.ArrayList;
import java.util.List;

public class ModesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterModes adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modes);

        System.out.println("AQUI 1 0");
        recyclerView = findViewById(R.id.recycler_modes);

        // Layout do RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        System.out.println("AQUI setou");
        // Dados
        List<ModeInfo> modes = new ArrayList<>();
        modes.add(criarMode(new Intent(ModesActivity.this, zenMode.class)));

        System.out.println("AQUI added");
        // Adapter
        adapter = new AdapterModes(modes, new AdapterModes.OnModeClickListener() {
            @Override
            public void onModeClick(Mode mode) {
                Toast.makeText(
                        ModesActivity.this,
                        "Modo clicado",
                        Toast.LENGTH_SHORT
                ).show();
            }

            @Override
            public void onModeButtonClick(Mode mode) {
                Toast.makeText(
                        ModesActivity.this,
                        "Botão do modo clicado",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        System.out.println("AQUI 1 0");
        recyclerView.setAdapter(adapter);
        System.out.println("setou adapter");
    }

//    private List<ModeInfo> createModes() {
//        List<ModeInfo> list = new ArrayList<>();
//
//        list.add(new ModeInfo(
//                "Addition",
//                "Practice basic addition",
//                R.drawable.ic_add // ícone seu
//        ));
//
//        list.add(new ModeInfo(
//                "Subtraction",
//                "Train subtraction skills",
//                R.drawable.ic_remove
//        ));
//
//        list.add(new ModeInfo(
//                "Multiplication",
//                "Improve multiplication speed",
//                R.drawable.ic_multiply
//        ));
//
//        list.add(new ModeInfo(
//                "Division",
//                "Learn division step by step",
//                R.drawable.ic_divide
//        ));
//
//        return list;
//    }

    private ModeInfo criarMode(Intent intent){
        System.out.println("AQUI criar 0");

        ModeInfo m = new ModeInfo("Zen mode", "description", R.drawable.ic_launcher_foreground);

        System.out.println("AQUI criou 0");
        return m;
    }
}
