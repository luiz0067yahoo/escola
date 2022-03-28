package com.example.alunoonline.dao;

import android.util.Log;

import com.example.alunoonline.model.Disciplina;
import com.example.alunoonline.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    public static long salvar(Nota nota){
        try {
            return nota.save();
        }catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar a notas: " + ex.getMessage());
            return -1;
        }
    }

    public static List<Nota> retornaNotas(String where, String[]whereArgs, String orderBy){
        List<Nota> list = new ArrayList<>();
        try{
            list = Nota.find(Nota.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de notas: " + ex.getMessage());

        }
        return list;
    }
}
