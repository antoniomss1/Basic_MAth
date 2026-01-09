package com.example.basicmath.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicmath.R;
import com.example.basicmath.models.Mode;
import com.example.basicmath.models.ModeConfigType;
import com.example.basicmath.models.ModeInfo;
import com.example.basicmath.utils.ModeConfigDialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
                "Just press the big Button on the screen and see if you got the calculation wright.";
        modes.add(createMode(zenMode.class, R.drawable.meditation, ZenDescription, ZenLongDescription, "Zen mode"));

        String dataDescription = "Can you say the week day?";
        String dataLongDescription = "Practice your ability to find the week day of an event. Were you born in a 13th friday?";
        modes.add(createMode(timeGuessActivity.class, R.drawable.calendar, dataDescription, dataLongDescription, "Date finding"));

        String timeModeDescription =
                "run against the clock!";
        String timeModeLongDescription =
                "solve as manny problems as you can in the selected time!";

        //criar pequena tela para seleção do tempo. esse modo exige que selecione o tempo

        modes.add(
                createMode(typePracticeActivity.class, R.drawable.clock, timeModeDescription, timeModeLongDescription, "cronometred", ModeConfigType.TIME_LIMIT)
        );

        String livesModeDescription =
                "X mistakes and you're out";
        String livesModeLongDescription =
                "You got a few chances, solve as manny problems as you can limiting the number of mistakes!";

        modes.add(
                createMode(typePracticeActivity.class, R.drawable.life, livesModeDescription, livesModeLongDescription, "survival", ModeConfigType.LIVES)
        );

                adapter = new AdapterModes(modes, new AdapterModes.OnModeClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onModeClick(ModeInfo mode) {
                Intent intent = new Intent(ModesActivity.this, mode.getTargetActivity());
                switch (mode.getConfigType()){
                    case TIME_LIMIT:
                        ModeConfigDialogs.showTimeDialog(ModesActivity.this, intent);
//                        showTimeDialog(intent);
                        break;
                    case LIVES:
                        ModeConfigDialogs.showLivesDialog(ModesActivity.this, intent);
//                        askMistakesNumberDialog(intent);
                        break;
                    case NONE:
                        startActivity(intent);
                        break;

                }
//                if (mode.getRequiresInfo()){
////                    askMistakesNumberDialog(intent);
//                }
//                else{
//                    intent.putExtra("mode", mode);
//                    startActivity(intent);
//                }
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

    private ModeInfo createMode(Class<?> target, int image, String description, String longDescription, String modeName){
        System.out.println("AQUI criar 0");

        ModeInfo m = new ModeInfo(modeName, description, longDescription, image, target);

        return m;
    }
    private ModeInfo createMode(Class<?> target, int image, String description, String longDescription, String modeName, ModeConfigType modeConfigType){
        System.out.println("AQUI criar 0");

        ModeInfo m = new ModeInfo(modeName, description, longDescription, image, target, modeConfigType);

        return m;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showTimeDialog(Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informe o tempo");
        builder.setMessage("Digite o tempo em segundos:");

        // Campo de texto
        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("Ex: 30");

        // Margem no EditText (opcional, mas recomendado)
        int padding = (int) (16 * getResources().getDisplayMetrics().density);
        input.setPadding(padding, padding, padding, padding);

        builder.setView(input);

        builder.setCancelable(false); // impede fechar sem escolher

        builder.setPositiveButton("OK", null); // vamos sobrescrever depois
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();

        AtomicInteger tempoT = new AtomicInteger();
        dialog.setOnShowListener(d -> {
            Button btnOk = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            btnOk.setOnClickListener(v -> {
                String texto = input.getText().toString().trim();

                if (texto.isEmpty()) {
                    input.setError("Digite um valor");
                    return;
                }

                int tempo;
                try {
                    tempo = Integer.parseInt(texto);
                } catch (NumberFormatException e) {
                    input.setError("Valor inválido");
                    return;
                }

                if (tempo <= 0) {
                    input.setError("Digite um número maior que zero");
                    return;
                }

                // Tudo ok → abre a próxima activity
                intent.putExtra("time_seconds", tempo);
                startActivity(intent);

                dialog.dismiss();
                tempoT.set(tempo);
            });
        });

        dialog.show();
//        return (tempoT.get());
    }

    private void askMistakesNumberDialog(Intent intent){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Max mistakes");
        builder.setMessage("set maximum number of mistakes");

        View dialogView = getLayoutInflater()
                .inflate(R.layout.mistakes_setter_layout, null);

        builder.setView(dialogView);


        builder.setCancelable(false); // impede fechar sem escolher

        builder.setPositiveButton("OK", null); // vamos sobrescrever depois
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialog1 -> {
            Button btnok = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            btnok.setOnClickListener(v -> {
                EditText input = dialogView.findViewById(R.id.ET_lives);
                String texto = input.getText().toString();

                RadioGroup group = dialogView.findViewById(R.id.radioGroupLives);
                int lifes;

                if (texto.isEmpty()) {
                    RadioButton btn = findViewById(group.getCheckedRadioButtonId());

                    lifes = Integer.valueOf(btn.getTag().toString());
//                    input.setError("Digite um valor");
//                    return;
                }
                group.clearCheck();

                try {
                    lifes = Integer.parseInt(texto);
                } catch (NumberFormatException e) {
                    input.setError("Valor inválido");
                    return;
                }

                if (lifes <= 0) {
                    input.setError("Digite um número maior que zero");
                    return;
                }

                // Tudo ok → abre a próxima activity
                intent.putExtra("lives_number", lifes);
                startActivity(intent);

                dialog.dismiss();
            });
        });
        dialog.show();


    }

}
