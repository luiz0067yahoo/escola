package com.example.alunoonline.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Disciplina extends SugarRecord {

    private int codigo;
    private String nome;
    private String professor;
    private int cargaHoraria;
    private int totDiasLetivos;

    public Disciplina() {
    }

    public Disciplina(int codigo, String nome, String professor, int cargaHoraria, int totDiasLetivos) {
        this.codigo = codigo;
        this.nome = nome;
        this.professor = professor;
        this.cargaHoraria = cargaHoraria;
        this.totDiasLetivos = totDiasLetivos;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getTotDiasLetivos() {
        return totDiasLetivos;
    }

    public void setTotDiasLetivos(int totDiasLetivos) {
        this.totDiasLetivos = totDiasLetivos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
