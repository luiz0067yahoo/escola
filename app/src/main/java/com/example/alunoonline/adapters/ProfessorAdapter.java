package com.example.alunoonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alunoonline.R;
import com.example.alunoonline.model.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder>{

    private List<Professor> listaProfessores;
    private Context context;

    public ProfessorAdapter(List<Professor> listaProfessores, Context context) {
        this.listaProfessores = listaProfessores;
        this.context = context;
    }

    public static class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRaProfessor;
        TextInputEditText edNomeProfessor;
        TextInputEditText edCpfProfessor;
        TextInputEditText edDtMatricula;
        TextInputEditText edDtNasc;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaProfessor = (TextInputEditText)itemView.findViewById(R.id.edRaProfessor);
            edNomeProfessor = (TextInputEditText)itemView.findViewById(R.id.edNomeProfessor);
            edCpfProfessor =  (TextInputEditText)itemView.findViewById(R.id.edCpfProfessor);
            edDtMatricula = (TextInputEditText)itemView.findViewById(R.id.edDtMatriculaProfessor);
            edDtNasc = (TextInputEditText)itemView.findViewById(R.id.edDtNascProfessor);

        }
    }

    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_professor, parent, false);

        ProfessorAdapter.ProfessorViewHolder viewHolder = new ProfessorAdapter.ProfessorViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor professor = listaProfessores.get(position);

        holder.edRaProfessor.setText(String.valueOf(professor.getRa()));
        holder.edCpfProfessor.setText(professor.getCpf());
        holder.edNomeProfessor.setText(professor.getNome());
        holder.edDtMatricula.setText(professor.getDtMatricula());
        holder.edDtNasc.setText(professor.getDtNasc());
    }

    @Override
    public int getItemCount() {
        return listaProfessores.size();
    }
}
