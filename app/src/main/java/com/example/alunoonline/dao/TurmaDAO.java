package com.example.alunoonline.dao;

import android.util.Log;

import com.example.alunoonline.model.Turma;

import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    public static long salvar(Turma turma) {
        try {
            return turma.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar a turma: " + ex.getMessage());
            return -1;
        }
    }

    public static List<Turma> retornaTurmas(String where, String[] whereArgs, String orderBy) {
        List<Turma> list = new ArrayList<>();
        try {
            list = Turma.find(Turma.class, where, whereArgs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de turmas: " + ex.getMessage());

        }
        return list;
    }
}
