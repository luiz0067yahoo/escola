package com.example.alunoonline.util;

import android.view.View;
import android.widget.TextView;

import com.example.alunoonline.R;
import com.google.android.material.snackbar.Snackbar;

public class Util {

    /***
     *
     * @param view: onde será exibido
     * @param msg: Mensagem
     * @param tipo: tipo do ícone 0-erro, 1-Sucesso, 2-Atenção
     */
    public static void customSnackBar(View view, String msg, int tipo){
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View viewSnack = snackbar.getView();
        TextView tv = (TextView) viewSnack.findViewById(R.id.snackbar_text);

        if (tipo == 0){
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_cancelar,
                    0,0 , 0);
        }
        if (tipo == 1){
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_confirmar,
                    0,0 , 0);
        }

        snackbar.show();
    }
}
