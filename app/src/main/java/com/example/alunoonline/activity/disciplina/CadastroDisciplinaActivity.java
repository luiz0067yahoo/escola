package com.example.alunoonline.activity.disciplina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.alunoonline.R;
import com.example.alunoonline.dao.DisciplinaDAO;
import com.example.alunoonline.dao.ProfessorDAO;
import com.example.alunoonline.model.Disciplina;
import com.example.alunoonline.model.Professor;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText edCodigoDisciplina;
    private TextInputEditText edNomeDisciplina;
    private TextInputEditText edCargaHoraria;
    private TextInputEditText edTotDiasLetivos;

    private MaterialSpinner spProfessor;

    private LinearLayout lnCadastroDisciplinas;

    private List<Professor> professores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        edCodigoDisciplina = findViewById(R.id.edCodigoDisciplina);
        edNomeDisciplina = findViewById(R.id.edNomeDisciplina);
        edCargaHoraria = findViewById(R.id.edCargaHoraria);
        edTotDiasLetivos = findViewById(R.id.edTotDiasLetivos);

        lnCadastroDisciplinas = findViewById(R.id.lnCadastroDisciplinas);

        iniciaSpinner();
    }

    private void iniciaSpinner() {
        spProfessor = findViewById(R.id.spProfessor);

        professores = ProfessorDAO.retornaProfessores("", new String[]{}, "");

        ArrayAdapter adaptersProfessor = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, professores);

        spProfessor.setAdapter(adaptersProfessor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastrar, menu);

        return true;
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
        if (edCodigoDisciplina.getText().toString().equals("")) {
            edCodigoDisciplina.setError("Informe o Código da disciplina!");
            edCodigoDisciplina.requestFocus();
            return;
        }

        if (edNomeDisciplina.getText().toString().equals("")) {
            edNomeDisciplina.setError("Informe o Nome da disciplina!");
            edNomeDisciplina.requestFocus();
            return;
        }

        if (spProfessor.getSelectedItem() == null) {
            Util.customSnackBar(lnCadastroDisciplinas, "Selecione um professor!", 0);
            return;
        }

        if (edCargaHoraria.getText().toString().equals("")) {
            edCargaHoraria.setError("Informe a Carga horária da disciplina!");
            edCargaHoraria.requestFocus();
            return;
        }

        if (edTotDiasLetivos.getText().toString().equals("")) {
            edTotDiasLetivos.setError("Informe o total de dias letivos!");
            edTotDiasLetivos.requestFocus();
            return;
        }

        salvarDisciplina();
    }

    private void salvarDisciplina() {
        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(Integer.parseInt(edCodigoDisciplina.getText().toString()));
        disciplina.setNome(edNomeDisciplina.getText().toString());
        disciplina.setProfessor(spProfessor.getSelectedItem().toString());
        disciplina.setCargaHoraria(Integer.parseInt(edCargaHoraria.getText().toString()));
        disciplina.setTotDiasLetivos(Integer.parseInt(edTotDiasLetivos.getText().toString()));

        if (DisciplinaDAO.salvar(disciplina) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnCadastroDisciplinas, "Erro ao salvar a disciplina (" + disciplina.getNome() + ") " +
                    "verifique o log", 0);
    }

    private void limparCampos() {
        edCodigoDisciplina.setText("");
        edNomeDisciplina.setText("");
        edCargaHoraria.setText("");
        edTotDiasLetivos.setText("");
    }
}