package com.example.alunoonline.dao;

import android.util.Log;

import com.example.alunoonline.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public static long salvar(Professor professor){
        try {
            return professor.save();
        }catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar o professor: " + ex.getMessage());
            return -1;
        }
    }

    public static List<Professor> retornaProfessores(String where, String[]whereArgs, String orderBy){
        List<Professor> list = new ArrayList<>();
        try{
            list = Professor.find(Professor.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de professores: " + ex.getMessage());

        }
        return list;
    }
}
