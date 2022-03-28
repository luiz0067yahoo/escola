package com.example.alunoonline.model;

import com.orm.SugarRecord;

public class Frequencia extends SugarRecord {
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    private long codigo;
    private int frequencia;
    private Aluno aluno;
    private Disciplina disciplina;

    public Frequencia() {
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }


    public float getfrequencia() {
        return frequencia;
    }


    public int getFrequencia() {
        return frequencia;
    }


    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

}
