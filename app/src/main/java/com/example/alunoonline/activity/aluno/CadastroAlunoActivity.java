package com.example.alunoonline.activity.aluno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.example.alunoonline.R;
import com.example.alunoonline.dao.AlunoDAO;
import com.example.alunoonline.model.Aluno;
import com.example.alunoonline.util.CpfMask;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroAlunoActivity extends AppCompatActivity {

    private TextInputEditText edRaAluno;
    private TextInputEditText edNomeAluno;
    private TextInputEditText edCpfAluno;
    private TextInputEditText edDtNascAluno;
    private TextInputEditText edDtMatAluno;
    private MaterialSpinner spCursos;
    private MaterialSpinner spPeriodo;
    private LinearLayout lnCadastroAlunos;

    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSeleionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        edRaAluno = findViewById(R.id.edRaAluno);
        edNomeAluno = findViewById(R.id.edNomeAluno);
        edCpfAluno = findViewById(R.id.edCpfAluno);
        edDtNascAluno = findViewById(R.id.edDtNascAluno);
        edDtMatAluno = findViewById(R.id.edDtMatAluno);
        lnCadastroAlunos = findViewById(R.id.lnCadastroAlunos);

        edDtNascAluno.setFocusable(false);
        edDtMatAluno.setFocusable(false);

        edCpfAluno.addTextChangedListener(CpfMask.insert(edCpfAluno));

        iniciaSpinners();
        setDataAtual();
    }

    private void iniciaSpinners() {
        spCursos = findViewById(R.id.spCurso);
        spPeriodo = findViewById(R.id.spPeriodo);

        String cursos[] = new String[]{
                "Análise e Desenv. Sistemas",
                "Administração",
                "Ciências Contábeis",
                "Direito",
                "Farmácia",
                "Nutrição"
        };

        String periodos[] = new String[]{
                "1ª Série",
                "2ª Série",
                "3ª Série",
                "4ª Série"
        };

        ArrayAdapter adapterCursos = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, cursos);

        ArrayAdapter adapterPeriodo = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, periodos);

        spCursos.setAdapter(adapterCursos);
        spPeriodo.setAdapter(adapterPeriodo);
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    public void selecionarData(View view) {
        dataSeleionada = view;
        showDialog(0);
    }

    private DatePickerDialog.OnDateSetListener setDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            vAno = year;
            vMes = month;
            vDia = day;

            atualizaData();
        }
    };

    private void atualizaData() {
        TextInputEditText edit = (TextInputEditText) dataSeleionada;
        edit.setText(new StringBuilder()
                .append(vDia).append("/")
                .append(vMes + 1).append("/")
                .append(vAno));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        setDataAtual();
        return new DatePickerDialog(this, setDatePicker,
                vAno, vMes, vDia);
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

    private void validarCampos(){
        if (edRaAluno.getText().toString().equals("")) {
            edRaAluno.setError("Informe o RA do Aluno!");
            edRaAluno.requestFocus();
            return;
        }

        if (edNomeAluno.getText().toString().equals("")) {
            edNomeAluno.setError("Informe o Nome do Aluno!");
            edNomeAluno.requestFocus();
            return;
        }

        if (edCpfAluno.getText().toString().equals("")) {
            edCpfAluno.setError("Informe o CPF do Aluno!");
            edCpfAluno.requestFocus();
            return;
        }

        if (edDtNascAluno.getText().toString().equals("")) {
            edDtNascAluno.setError("Informe a Data de nascimento do Aluno!");
            edDtNascAluno.requestFocus();
            return;
        }

        if (edDtMatAluno.getText().toString().equals("")) {
            edDtMatAluno.setError("Informe a Data da matrícula do Aluno!");
            edDtMatAluno.requestFocus();
            return;
        }

        if (spCursos.getSelectedItem() == null) {
            Util.customSnackBar(lnCadastroAlunos, "Selecione um curso!", 0);
            return;
        }

        if (spPeriodo.getSelectedItem() == null) {
            Util.customSnackBar(lnCadastroAlunos, "Selecione um periodo!", 0);
            return;
        }

        salvarAluno();
    }

    private void salvarAluno() {
        Aluno aluno = new Aluno();
        aluno.setRa(Integer.parseInt(edRaAluno.getText().toString()));
        aluno.setNome(edNomeAluno.getText().toString());
        aluno.setCpf(edCpfAluno.getText().toString());
        aluno.setDtNasc(edDtNascAluno.getText().toString());
        aluno.setDtMatricula(edDtMatAluno.getText().toString());
        aluno.setCurso(spCursos.getSelectedItem().toString());
        aluno.setPeriodo(spPeriodo.getSelectedItem().toString());

        if (AlunoDAO.salvar(aluno) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnCadastroAlunos, "Erro ao salvar o aluno (" + aluno.getNome() + ") " +
                    "verifique o log", 0);
    }

    private void limparCampos() {
        edRaAluno.setText("");
        edNomeAluno.setText("");
        edCpfAluno.setText("");
        edDtNascAluno.setText("");
        edDtMatAluno.setText("");
    }
}