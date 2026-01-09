package com.example.basicmath.utils;

import android.app.AlertDialog;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicmath.R;

import java.util.concurrent.atomic.AtomicInteger;

public final class ModeConfigDialogs {
    private ModeConfigDialogs() {}

    public static void showTimeDialog(
            AppCompatActivity activity,
            Intent intent
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Informe o tempo");
        builder.setMessage("Digite o tempo em segundos:");

        // Campo de texto
        EditText input = new EditText(activity);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("Ex: 30");

        // Margem no EditText (opcional, mas recomendado)
        int padding = (int) (16 * activity.getResources().getDisplayMetrics().density);
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
                activity.startActivity(intent);

                dialog.dismiss();
                tempoT.set(tempo);
            });
        });

        dialog.show();
    }

    public static void showLivesDialog(
            AppCompatActivity activity,
            Intent intent
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Max mistakes");
        builder.setMessage("set maximum number of mistakes");

        View dialogView = activity.getLayoutInflater()
                .inflate(R.layout.mistakes_setter_layout, null);

        builder.setView(dialogView);


        builder.setCancelable(false); // impede fechar sem escolher

        builder.setPositiveButton("OK", null); // vamos sobrescrever depois
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialog1 -> {
            Button btnok = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            btnok.setOnClickListener(v -> {

                EditText input = dialogView.findViewById(R.id.ET_lives);
                String texto = input.getText().toString();
                RadioGroup group = dialogView.findViewById(R.id.radioGroupLives);
                int lifes;

                RadioButton btn = dialogView.findViewById(group.getCheckedRadioButtonId());

                if(group.getCheckedRadioButtonId() == -1){
                    if (texto.isEmpty()) {
                        input.setError("Digite um valor");
                        return;
                    }
                    try {
                        lifes = Integer.parseInt(texto);
                        if (lifes <= 0) {
                            input.setError("Type a number greater than zero");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        input.setError("Invalid value");
                        return;
                    }
                }else{
                    System.out.println("else:");
                    lifes = Integer.valueOf(btn.getTag().toString());
                    System.out.println("lifes = "+ lifes);
                }

                System.out.println("lifes final: "+lifes);

                // Tudo ok → abre a próxima activity
                intent.putExtra("lives_number", lifes);
                activity.startActivity(intent);

                dialog.dismiss();
            });
        });
        dialog.show();


    }
}
