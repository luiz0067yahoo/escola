package com.example.alunoonline.activity.professor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.example.alunoonline.R;
import com.example.alunoonline.dao.ProfessorDAO;
import com.example.alunoonline.model.Professor;
import com.example.alunoonline.util.CpfMask;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CadastroProfessorActivity extends AppCompatActivity {

    private TextInputEditText edRaProfessor;
    private TextInputEditText edNomeProfessor;
    private TextInputEditText edCpfProfessor;
    private TextInputEditText edDtNascProfessor;
    private TextInputEditText edDtMatProfessor;
    private LinearLayout lnCadastroProfessor;

    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSeleionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        edRaProfessor = findViewById(R.id.edRaProfessor);
        edNomeProfessor = findViewById(R.id.edNomeProfessor);
        edCpfProfessor = findViewById(R.id.edCpfProfessor);
        edDtNascProfessor = findViewById(R.id.edDtNascProfessor);
        edDtMatProfessor = findViewById(R.id.edDtMatProfessor);
        lnCadastroProfessor = findViewById(R.id.lnCadastroProfessores);

        edDtNascProfessor.setFocusable(false);
        edDtMatProfessor.setFocusable(false);

        edCpfProfessor.addTextChangedListener(CpfMask.insert(edCpfProfessor));
    }

    private void setDataAtual(){
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
        switch (item.getItemId()){
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
        if(edRaProfessor.getText().toString().equals("")){
            edRaProfessor.setError("Informe o RA do professor!");
            edRaProfessor.requestFocus();
            return;
        }

        if(edNomeProfessor.getText().toString().equals("")){
            edNomeProfessor.setError("Informe o Nome do Aluno!");
            edNomeProfessor.requestFocus();
            return;
        }

        if(edCpfProfessor.getText().toString().equals("")){
            edCpfProfessor.setError("Informe o CPF do Aluno!");
            edCpfProfessor.requestFocus();
            return;
        }

        if(edDtNascProfessor.getText().toString().equals("")){
            edDtNascProfessor.setError("Informe a Data de nascimento do Aluno!");
            edDtNascProfessor.requestFocus();
            return;
        }

        if(edDtMatProfessor.getText().toString().equals("")){
            edDtMatProfessor.setError("Informe a Data da matrícula do Aluno!");
            edDtMatProfessor.requestFocus();
            return;
        }

        salvarProfessor();
    }

    private void salvarProfessor() {
        Professor professor = new Professor();
        professor.setRa(Integer.parseInt(edRaProfessor.getText().toString()));
        professor.setNome(edNomeProfessor.getText().toString());
        professor.setCpf(edCpfProfessor.getText().toString());
        professor.setDtNasc(edDtNascProfessor.getText().toString());
        professor.setDtMatricula(edDtMatProfessor.getText().toString());

        if(ProfessorDAO.salvar(professor) > 0) {
            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnCadastroProfessor, "Erro ao salvar o professor ("+professor.getNome()+") " +
                    "verifique o log", 0);
    }


    private void limparCampos() {
        edRaProfessor.setText("");
        edNomeProfessor.setText("");
        edCpfProfessor.setText("");
        edDtNascProfessor.setText("");
        edDtMatProfessor.setText("");
    }
}