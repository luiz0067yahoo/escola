package com.example.alunoonline.model;

import com.orm.SugarRecord;

import java.util.List;
import java.util.Objects;

public class Turma extends SugarRecord {

    private int codigo;
    private String curso;
    private String turno;
    private String regime;
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;

    public Turma() {
    }

    public Turma(int codigo, String curso, String turno, String regime, List<Aluno> alunos, List<Disciplina> disciplinas) {
        this.codigo = codigo;
        this.curso = curso;
        this.turno = turno;
        this.regime = regime;
        this.alunos = alunos;
        this.disciplinas = disciplinas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return codigo == turma.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
