package com.example.alunoonline.activity.turma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.alunoonline.R;
import com.example.alunoonline.dao.TurmaDAO;
import com.example.alunoonline.model.Turma;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaActivity extends AppCompatActivity {

    private TextInputEditText edCodigoTurma;
    private LinearLayout lnCadastroTurmas;
    private MaterialSpinner spCursos;
    private MaterialSpinner spTurno;
    private MaterialSpinner spRegime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        edCodigoTurma = findViewById(R.id.edCodigoTurma);
        lnCadastroTurmas = findViewById(R.id.lnCadastroTurmas);

        iniciaSpinners();
    }

    private void iniciaSpinners() {
        spCursos = findViewById(R.id.spCurso);
        spTurno = findViewById(R.id.spTurno);
        spRegime = findViewById(R.id.spRegime);

        String cursos[] = new String[]{
                "Análise e Desenv. Sistemas",
                "Administração",
                "Ciências Contábeis",
                "Direito",
                "Farmácia",
                "Nutrição"
        };

        String turnos[] = new String[]{
                "Matutino",
                "Vespertino",
                "Noturno"
        };

        String regimes[] = new String[]{
                "Semestral",
                "Anual",
        };

        ArrayAdapter adapterCursos = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, cursos);

        ArrayAdapter adapterTurno = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turnos);

        ArrayAdapter adapterRegime = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, regimes);

        spCursos.setAdapter(adapterCursos);
        spTurno.setAdapter(adapterTurno);
        spRegime.setAdapter(adapterRegime);
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
        if (edCodigoTurma.getText().toString().equals("")) {
            edCodigoTurma.setError("Informe o Código da turma!");
            edCodigoTurma.requestFocus();
            return;
        }

        if (spCursos.getSelectedItem() == null) {
            Util.customSnackBar(lnCadastroTurmas, "Selecione um curso!", 0);
            return;
        }

        if (spTurno.getSelectedItem() == null) {
            Util.customSnackBar(lnCadastroTurmas, "Selecione um turno!", 0);
            return;
        }

        if (spRegime.getSelectedItem() == null) {
            Util.customSnackBar(lnCadastroTurmas, "Selecione um regime!", 0);
            return;
        }

        salvarTurma();
    }

    private void salvarTurma() {
        Turma turma = new Turma();
        turma.setCodigo(Integer.parseInt(edCodigoTurma.getText().toString()));
        turma.setCurso(spCursos.getSelectedItem().toString());
        turma.setTurno(spTurno.getSelectedItem().toString());
        turma.setRegime(spRegime.getSelectedItem().toString());

        if (TurmaDAO.salvar(turma) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnCadastroTurmas, "Erro ao salvar a turma (" + turma.getCodigo() + ") " +
                    "verifique o log", 0);
    }

    private void limparCampos() {
        edCodigoTurma.setText("");
    }
}