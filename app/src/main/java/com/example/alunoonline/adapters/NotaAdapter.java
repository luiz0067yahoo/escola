package com.example.alunoonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alunoonline.R;
import com.example.alunoonline.model.Nota;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaViewHolder> {

    private List<Nota> listaNotas;
    private Context context;

    public NotaAdapter(List<Nota> listaNotas, Context context) {
        this.listaNotas = listaNotas;
        this.context = context;
    }

    public static class NotaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edCodigoNota;
        TextInputEditText edNomeNota;
        TextInputEditText edProfessor;
        TextInputEditText edCargaHoraria;
        TextInputEditText edTotDiasLetivos;

        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);

            //edCodigoNota = (TextInputEditText) itemView.findViewById(R.id.edCodigoNota);
            //edNomeNota = (TextInputEditText) itemView.findViewById(R.id.edNomeNota);
            edProfessor = (TextInputEditText) itemView.findViewById(R.id.edNomeProfessor);
            edCargaHoraria = (TextInputEditText) itemView.findViewById(R.id.edCargaHoraria);
            edTotDiasLetivos = (TextInputEditText) itemView.findViewById(R.id.edTotDiasLetivos);
        }
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_nota, parent, false);

        NotaAdapter.NotaViewHolder viewHolder = new NotaAdapter.NotaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        Nota nota = listaNotas.get(position);

        holder.edCodigoNota.setText(String.valueOf(nota.getCodigo()));
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }
}