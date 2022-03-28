package com.example.alunoonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alunoonline.R;
import com.example.alunoonline.model.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {
    private List<Turma> listaTurmas;
    private Context context;

    public TurmaAdapter(List<Turma> listaTurmas, Context context) {
        this.listaTurmas = listaTurmas;
        this.context = context;
    }

    public static class TurmaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edCodigoTurma;
        TextInputEditText edCursoTurma;
        TextInputEditText edTurnoTurma;
        TextInputEditText edRegimeTurma;

        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);

            edCodigoTurma = (TextInputEditText) itemView.findViewById(R.id.edCodigoTurma);
            edCursoTurma = (TextInputEditText) itemView.findViewById(R.id.edCursoTurma);
            edTurnoTurma = (TextInputEditText) itemView.findViewById(R.id.edTurnoTurma);
            edRegimeTurma = (TextInputEditText) itemView.findViewById(R.id.edRegimeTurma);
        }
    }

    @Override
    public TurmaAdapter.TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_turma, parent, false);

        TurmaAdapter.TurmaViewHolder viewHolder = new TurmaAdapter.TurmaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaAdapter.TurmaViewHolder holder, int position) {
        Turma turma = listaTurmas.get(position);

        holder.edCodigoTurma.setText(String.valueOf(turma.getCodigo()));
        holder.edCursoTurma.setText(turma.getCurso());
        holder.edTurnoTurma.setText(turma.getTurno());
        holder.edRegimeTurma.setText(turma.getTurno());
    }

    @Override
    public int getItemCount() {
        return listaTurmas.size();
    }
}
