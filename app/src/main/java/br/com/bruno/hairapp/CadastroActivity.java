package br.com.bruno.hairapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.bruno.hairapp.models.MyHttpEntity;
import br.com.bruno.hairapp.models.Usuario;
import br.com.bruno.hairapp.tasks.ClientesCadastroTask;
import br.com.bruno.hairapp.utils.Autenticacao;

/**
 * Created by Bruno on 22/07/2015.
 */
public class CadastroActivity extends Activity {

    private Usuario usuario;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editLembreteSenha;
    private Button btnCadastro;
    private MyHttpEntity myHttpEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        myHttpEntity = MyHttpEntity.getInstance();

        if (myHttpEntity.getSessionId().equals("")) {
            new Autenticacao().auteticar();
        }

        usuario = new Usuario();
        findViews();

        btnCadastro.setOnClickListener(actionClickButton());

    }

    private void findViews() {
        editEmail = (EditText) findViewById(R.id.activity_cadastro_edit_email);
        editSenha = (EditText) findViewById(R.id.activity_cadastro_edit_senha);
        editLembreteSenha = (EditText) findViewById(R.id.activity_cadastro_edit_lembrete_senha);
        btnCadastro = (Button) findViewById(R.id.activity_cadastro_button_cadastro);
    }

    private View.OnClickListener actionClickButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verifyFields()) {
                    usuario.setDsEmail(editEmail.getText().toString());
                    usuario.setCdSenha(editSenha.getText().toString());
                    usuario.setDsLembreteSenha(editLembreteSenha.getText().toString());
                    cadastroClientes();
                }
            }
        };
    }

    private void cadastroClientes() {

        ClientesCadastroTask clientesCadastroTask = new ClientesCadastroTask(usuario, new ClientesCadastroTask.OnReturnListProdutos() {
            @Override
            public void onCompletion(Usuario usuario) {
                Log.i("onCompletion", usuario.getDsLogin());
                Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError() {
                Log.i("onError", "Deu erro!");
                Toast.makeText(getApplicationContext(), "Não foi possível cadastrar usuário, tente mais tarde", Toast.LENGTH_LONG).show();
            }
        });
        clientesCadastroTask.execute();
    }


    private boolean verifyFields() {

        boolean resp = false;

        if (editEmail.getText().toString().equals("") || editSenha.getText().toString().equals("") || editLembreteSenha.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show();
        } else {
            resp = true;
        }

        return resp;
    }
}
