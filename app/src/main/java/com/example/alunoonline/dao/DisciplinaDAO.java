package com.example.alunoonline.dao;

import android.util.Log;

import com.example.alunoonline.model.Aluno;
import com.example.alunoonline.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public static long salvar(Disciplina disciplina) {
        try {
            return disciplina.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar a disciplina: " + ex.getMessage());
            return -1;
        }
    }

    public static Disciplina getDisciplina(long id) {
        List<Disciplina> disciplina = new ArrayList<>();
        try {
            disciplina = Disciplina.find(Disciplina.class, "id = ?", String.valueOf(id));
            return disciplina.get(0);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar o disciplina: " + ex.getMessage());
            return null;
        }
    }
    public static List<Disciplina> retornaDisciplinas(String where, String[] whereArgs, String orderBy) {
        List<Disciplina> list = new ArrayList<>();
        try {
            list = Disciplina.find(Disciplina.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de disciplinas: " + ex.getMessage());

        }
        return list;
    }
}
