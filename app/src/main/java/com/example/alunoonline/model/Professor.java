package com.example.alunoonline.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Professor extends SugarRecord {

    private int ra;
    private String nome;
    private String cpf;
    private String dtNasc;
    private String dtMatricula;

    public Professor() {
    }

    public Professor(int ra, String nome, String cpf, String dtNasc, String dtMatricula) {
        this.ra = ra;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.dtMatricula = dtMatricula;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getDtMatricula() {
        return dtMatricula;
    }

    public void setDtMatricula(String dtMatricula) {
        this.dtMatricula = dtMatricula;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return ra == professor.ra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ra);
    }
}
