package com.example.alunoonline.dao;

import android.util.Log;

import com.example.alunoonline.model.Frequencia;

import java.util.ArrayList;
import java.util.List;

public class FrequenciaDAO {

    public static long salvar(Frequencia frequencia){
        try {
            return frequencia.save();
        }catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar a Frequencia: " + ex.getMessage());
            return -1;
        }
    }

    public static List<Frequencia> retornaFrequencias(String where, String[]whereArgs, String orderBy){
        List<Frequencia> list = new ArrayList<>();
        try{
            list = Frequencia.find(Frequencia.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de Frequencia: " + ex.getMessage());

        }
        return list;
    }
}
