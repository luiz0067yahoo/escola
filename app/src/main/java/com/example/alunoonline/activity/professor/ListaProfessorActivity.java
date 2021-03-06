package com.example.alunoonline.activity.professor;

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
import com.example.alunoonline.adapters.ProfessorAdapter;
import com.example.alunoonline.dao.ProfessorDAO;
import com.example.alunoonline.model.Professor;
import com.example.alunoonline.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaProfessorActivity extends AppCompatActivity {

    private RecyclerView rvListaProfessores;
    private LinearLayout lnListaProfessores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professor);

        lnListaProfessores = findViewById(R.id.lnListaProfessores);

        atualizaListaProfessores();
    }

    public void atualizaListaProfessores(){
        List<Professor> listaProfessores = new ArrayList<>();
        listaProfessores = ProfessorDAO.retornaProfessores("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: " + listaProfessores.size());

        rvListaProfessores = findViewById(R.id.rvListaProfessores);
        ProfessorAdapter adapter = new ProfessorAdapter(listaProfessores, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaProfessores.setLayoutManager(llm);
        rvListaProfessores.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_add:
                abrirCadastroProfessor();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroProfessor() {
        Intent intent = new Intent(this, CadastroProfessorActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Util.customSnackBar(lnListaProfessores, "Professor salvo com sucesso!", 1);
        }
        atualizaListaProfessores();
    }
}