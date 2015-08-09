package br.com.bruno.hairapp.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import br.com.bruno.hairapp.tasks.AutenticacaoTask;

/**
 * Created by Bruno on 26/07/2015.
 */
public class Autenticacao {

    public void auteticar(final Context context){
        AutenticacaoTask autenticacaoTask = new AutenticacaoTask(new AutenticacaoTask.OnReturnListProdutos() {
            @Override
            public void onCompletion(String users) {
                Toast.makeText(context, users, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError() {
                Log.e("Erro", "Deu Erro!");
            }
        });
        autenticacaoTask.execute();
    }
}
