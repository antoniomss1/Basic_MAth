package com.example.basicmath.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicmath.R;
import com.example.basicmath.environment.Data.DBContract;
import com.example.basicmath.environment.Data.DBHandler;
import com.example.basicmath.models.GameData;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class historyActivity extends AppCompatActivity {

    TextView tv;
    TextView TVmostP;
    double avg = 5;
    int qnt = 9;
    double precision;
    int wrongProblems;
    int biggestSeq =0;

    private AdapterGamesHistory adapter;
    private ArrayList<GameData> pastGamesList;
    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv =        findViewById(R.id.textViewBestAvarage);
        TVmostP =   findViewById(R.id.textViewMostProblems);
        double avgAtual = Double.parseDouble(tv.getText().toString());
        int qntAtual = Integer.parseInt(TVmostP.getText().toString());


        //pegar dados dos intents (game)
        this.avg = getIntent().getDoubleExtra("avg", 0);
        this.qnt = getIntent().getIntExtra("quant", 0);
        this.precision = getIntent().getDoubleExtra("precision", 0);
        this.wrongProblems = getIntent().getIntExtra("wrongs", 19);
        LocalDateTime period = LocalDateTime.now().withSecond(0).withNano(0);

        GameData game = new GameData(String.valueOf(period).replace("T", " "), qnt, avg, wrongProblems, biggestSeq);

        pastGamesList = new ArrayList<>();
        adapter = new AdapterGamesHistory(pastGamesList, null);

        //carregard dados do banco de dados
        getAllPastGames(game);

        recyclerView = findViewById(R.id.recycler_past_games);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        DividerItemDecoration divider =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        divider.setDrawable(
                ContextCompat.getDrawable(this, R.drawable.gradient)
        );

        recyclerView.addItemDecoration(divider);

        recyclerView.setAdapter(adapter);


        //comparar se é melhor que o atual
        verifyBestResult(avg, qnt, precision);

//        if(avg<avgAtual || avgAtual ==0){
//            tv.setText(avg+"s");
//        }
//        if(qnt>qntAtual || qntAtual == 0){
//            TVmostP.setText(qnt);
//        }


        //verificar se game tem dados
//        pastGamesList.add(game);

        //adicionar no banco e no recycler



//        setRecyclerView(recyclerView, R.id.recycler_past_games, adapter);


        TextView TV = new TextView(this);
        recyclerView.setAdapter(adapter);

    }

    private void verifyBestResult(double avg, int qnt, double precision) {

    }


    //corrigir metodo abaixo (muito rígido. Pouco arquitetônico)
    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private void getAllPastGames(GameData game) {

        System.out.println("aqui");
        ArrayList<GameData> l = new ArrayList<>();
        DBHandler helper = new DBHandler(historyActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();

        System.out.println("aqui");
        Cursor cursor = helper.getAllGamesInDb(db);
        System.out.println("pegou cursor");
        if (cursor.moveToFirst()) {
            System.out.println("entrou no if");
            do {
                System.out.println("vai id");
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.GAME_FIELD_ID));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.GAME_FIELD_DATE));
                System.out.println("date, vai number");
                int numberOfProblems =  cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.GAME_FIELD_NUMBER_OF_PROBLEMS));
                double average =  cursor.getDouble(cursor.getColumnIndexOrThrow(DBContract.GAME_FIELD_AVERAGE));
                System.out.println("biggest seq");
                int biggestSeq =  cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.GAME_FIELD_BIGGEST_SEQ));
                System.out.println("wrong");
                int wrongProblems =  cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.GAME_FIELD_WRONG_PROBLEMS));

                GameData t = new GameData(date, numberOfProblems, average, wrongProblems, biggestSeq, id);
                l.add(t);
            } while (cursor.moveToNext());
        }
        if(game.getNumberOfProblems() != 0){
            long id = addGameInHistory(game);
            game.setId(id);
            l.addFirst(game);
        }

        cursor.close();
        db.close();
        adapter.updateList(l);
    }

    private long addGameInHistory(GameData game) {
        System.out.println("adding: "+game.toString());
        DBHandler helper = new DBHandler(historyActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();

        long id =helper.addPastGame(db, game);
        System.out.println("id: "+id);
        game.setId(id);
        return id;
//        finish();
    }


}