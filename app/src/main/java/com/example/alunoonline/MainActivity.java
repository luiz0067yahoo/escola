package com.example.alunoonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.alunoonline.activity.aluno.ListaAlunoActivity;
import com.example.alunoonline.activity.disciplina.ListaDisciplinaActivity;
import com.example.alunoonline.activity.nota.LancamentoNotasActivity;
import com.example.alunoonline.activity.professor.ListaProfessorActivity;
import com.example.alunoonline.activity.turma.ListaTurmaActivity;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastros, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_aluno:
                intent = new Intent(this, ListaAlunoActivity.class);
                startActivity(intent);
                return true;
            case R.id.mn_professor:
                intent = new Intent(this, ListaProfessorActivity.class);
                startActivity(intent);
                return true;
            case R.id.mn_disciplina:
                intent = new Intent(this, ListaDisciplinaActivity.class);
                startActivity(intent);
                return true;
            case R.id.mn_turma:
                intent = new Intent(this, ListaTurmaActivity.class);
                startActivity(intent);
                return true;
            case R.id.mn_nota:
                intent = new Intent(this, LancamentoNotasActivity.class);
                startActivity(intent);
                return true;
            case R.id.mn_frequencia:
//                intent = new Intent(this, CadastroFrequenciasctivity.class);
//                startActivity(intent);
                return true;
            case R.id.mn_lAlunos:
                intent = new Intent(this, ListaAlunoActivity.class);
                startActivity(intent);
                return true;
            case R.id.mn_lProfessores:
                intent = new Intent(this, ListaProfessorActivity.class);
                startActivity(intent);
                return true;
            case R.id.mn_lDisciplinas:
                intent = new Intent(this, ListaDisciplinaActivity.class);
                startActivity(intent);
                return true;
            case R.id.mn_lTurmas:
                intent = new Intent(this, ListaTurmaActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}