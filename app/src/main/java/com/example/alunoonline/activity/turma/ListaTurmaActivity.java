package com.example.alunoonline.activity.turma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.alunoonline.R;
import com.example.alunoonline.adapters.TurmaAdapter;
import com.example.alunoonline.dao.TurmaDAO;
import com.example.alunoonline.model.Turma;
import com.example.alunoonline.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaTurmaActivity extends AppCompatActivity {

    private RecyclerView rvListaTurmas;
    private LinearLayout lnListaTurmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turma);

        lnListaTurmas = findViewById(R.id.lnListaTurmas);

        atualizaListaTurmas();
    }

    public void atualizaListaTurmas() {
        List<Turma> listaTurmas = new ArrayList<>();
        listaTurmas = TurmaDAO.retornaTurmas("", new String[]{}, "codigo asc");
        Log.e("PHS", "Tamanho da lista: " + listaTurmas.size());

        rvListaTurmas = findViewById(R.id.rvListaTurmas);
        TurmaAdapter adapter = new TurmaAdapter(listaTurmas, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaTurmas.setLayoutManager(llm);
        rvListaTurmas.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_add:
                abrirCadastroTurma();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroTurma() {
        Intent intent = new Intent(this, CadastroTurmaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnListaTurmas, "Turma salva com sucesso!", 1);
        }
        atualizaListaTurmas();
    }
}