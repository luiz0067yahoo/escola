package com.example.alunoonline.activity.frequencia;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alunoonline.R;
import com.example.alunoonline.dao.AlunoDAO;
import com.example.alunoonline.dao.DisciplinaDAO;
import com.example.alunoonline.dao.FrequenciaDAO;
import com.example.alunoonline.model.Aluno;
import com.example.alunoonline.model.Disciplina;
import com.example.alunoonline.model.Frequencia;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class LancamentoFrequenciaActivity extends AppCompatActivity {

    private AutoCompleteTextView atAlunos;
    private MaterialSpinner spRegime;
    private MaterialSpinner spAlunos;
    private MaterialSpinner spDisciplina;
    private TextInputLayout edFrequencia;
    private LinearLayout lnLancamentoFrequencias;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastrar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_frequencias);
        edFrequencia = findViewById(R.id.edFrequencia);
        lnLancamentoFrequencias = findViewById(R.id.lnLancamentoFrequencias);
        iniciaSpinners();
    }

    private void iniciaAluno(){
        spAlunos = findViewById(R.id.spAlunos);
        List<Aluno> listaAlunos = AlunoDAO.retornaAlunos("", new String[]{}, "nome asc");

        // Depois de retornado os alunos, é necessário criar um vetor para atribuir ao adapter
        String[] nomes = new String[listaAlunos.size()];

        for (int i = 0; i < listaAlunos.size(); i++) {
            Aluno aluno = listaAlunos.get(i);
            nomes[i] = aluno.getRa() + " - " + aluno.getNome();
        }

        //Criando o adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                nomes);

        //Setando o adapter ao componente AutoCompleteTextView
         atAlunos.setAdapter(adapter);
    }

    private void iniciaDisplina(){
        spDisciplina = findViewById(R.id.spDisciplina);
        List<Disciplina> listaDisciplina = DisciplinaDAO.retornaDisciplinas("", new String[]{}, "nome asc");

        // Depois de retornado os alunos, é necessário criar um vetor para atribuir ao adapter
        String[] nomes = new String[listaDisciplina.size()];

        for (int i = 0; i < listaDisciplina.size(); i++) {
            Disciplina disciplina = listaDisciplina.get(i);
            nomes[i] = disciplina.getId() + " - " + disciplina.getNome();
        }

        //Criando o adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                nomes);

        //Setando o adapter ao componente AutoCompleteTextView
        atAlunos.setAdapter(adapter);
    }
    private void iniciaRegime(){
        spRegime = findViewById(R.id.spRegime);
        String regimes[] = new String[]{
            "Semestral",
            "Anual"
        };

        ArrayAdapter adapterRegime = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2, regimes);

        spRegime.setAdapter(adapterRegime);


    }
    private void iniciaSpinners() {
        iniciaAluno();
        iniciaDisplina();
        iniciaRegime();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_limpar:
                limparCampos();
                return true;
            case R.id.mn_salvar:
                validarCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void validarCampos() {
        if (spAlunos.getSelectedItem() == null) {
            Util.customSnackBar(lnLancamentoFrequencias, "Selecione um aluno!", 0);
            return;
        }

        if (spRegime.getSelectedItem() == null) {
            Util.customSnackBar(lnLancamentoFrequencias, "Selecione um regime!", 0);
            return;
        }

        if (edFrequencia.getVisibility() == View.VISIBLE &&
                edFrequencia.getEditText().getText().toString().equals("")) {
            edFrequencia.setError("Informe a frequencia!");
            edFrequencia.requestFocus();
            return;
        }

        if (spDisciplina.getSelectedItem() == null  ) {
            spDisciplina.setError("Informe a Disciplina!");
            spDisciplina.requestFocus();
            return;
        }
        if (spRegime.getSelectedItem() == null  ) {
            spRegime.setError("Informe a Regime!");
            spRegime.requestFocus();
            return;
        }
        if (spAlunos.getSelectedItem() == null  ) {
            spRegime.setError("Informe o aluno!");
            spRegime.requestFocus();
            return;
        }
        salvarFrequencia();
    }

    private void salvarFrequencia() {
        Frequencia frequencia = new Frequencia();
        frequencia.setFrequencia(Integer.parseInt(edFrequencia.getEditText().getText().toString()));

        if (edFrequencia.getVisibility() != View.INVISIBLE) {
            frequencia.setFrequencia(Integer.parseInt(edFrequencia.getEditText().getText().toString()));
        }

        frequencia.setFrequencia(0);

        int ra = ((Aluno) spAlunos.getSelectedItem()).getRa();
        frequencia.setAluno(AlunoDAO.getAluno(ra));

        long id = ((Disciplina) spDisciplina.getSelectedItem()).getId();
        frequencia.setDisciplina(DisciplinaDAO.getDisciplina(id));

        if (FrequenciaDAO.salvar(frequencia) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnLancamentoFrequencias, "Erro ao salvar a turma (" + frequencia.getId() + ") " +
                    "verifique o log", 0);
    }

    private void limparCampos() {
        edFrequencia.getEditText().setText("");
        edFrequencia.getEditText().setText("");
    }
}