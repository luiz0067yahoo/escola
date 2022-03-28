package com.example.alunoonline.activity.disciplina;

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
import com.example.alunoonline.adapters.DisciplinaAdapter;
import com.example.alunoonline.dao.DisciplinaDAO;
import com.example.alunoonline.model.Disciplina;
import com.example.alunoonline.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaDisciplinaActivity extends AppCompatActivity {

    private RecyclerView rvListaDisciplinas;
    private LinearLayout lnListaDisciplinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplina);

        lnListaDisciplinas = findViewById(R.id.lnListaDisciplinas);

        atualizaListaDisciplinas();
    }

    public void atualizaListaDisciplinas() {
        List<Disciplina> listaDisciplinas = new ArrayList<>();
        listaDisciplinas = DisciplinaDAO.retornaDisciplinas("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: " + listaDisciplinas.size());

        rvListaDisciplinas = findViewById(R.id.rvListaDisciplinas);
        DisciplinaAdapter adapter = new DisciplinaAdapter(listaDisciplinas, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaDisciplinas.setLayoutManager(llm);
        rvListaDisciplinas.setAdapter(adapter);
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
                abrirCadastroDisciplina();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroDisciplina() {
        Intent intent = new Intent(this, CadastroDisciplinaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnackBar(lnListaDisciplinas, "Disciplina salva com sucesso!", 1);
        }
        atualizaListaDisciplinas();
    }
}