package com.example.basicmath.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

        String ZenDescription =
                "A mode for when you don't wanna type";
        String ZenLongDescription =
                "If you don't wanna type, relax! " +
                "Zen mode was made for you. " +
                "Just press the big Button on the screen and see if you got the calculation wright.";
        modes.add(criarMode(zenMode.class, R.drawable.meditation, ZenDescription, ZenLongDescription));

        String dataDescription = "Can you say the week day?";
        String dataLongDescription = "Practice your ability to find the week day of an event. Were you born in a 13th friday?";
        modes.add(criarMode(timeGuessActivity.class, R.drawable.calendar, dataDescription, dataLongDescription));

        System.out.println("AQUI added");
        // Adapter
//        adapter = new AdapterModes(modes, new AdapterModes.OnModeClickListener() );

        adapter = new AdapterModes(modes, new AdapterModes.OnModeClickListener() {
            @Override
            public void onModeClick(ModeInfo mode) {
                Intent intent = new Intent(ModesActivity.this, mode.getTargetActivity());
                startActivity(intent);
            }

            @Override
            public void onModeButtonClick(Mode mode) {

            }
        });

        System.out.println("AQUI 1 0");
        recyclerView.setAdapter(adapter);
        System.out.println("setou adapter");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );
            return insets;
        });
    }

    private ModeInfo criarMode(Class<?> target, int image, String description, String longDescription){
        System.out.println("AQUI criar 0");

        ModeInfo m = new ModeInfo("Zen mode", description, longDescription, image, target);

        return m;
    }
}
