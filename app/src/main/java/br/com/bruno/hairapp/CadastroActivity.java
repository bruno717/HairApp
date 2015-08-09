package br.com.bruno.hairapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import br.com.bruno.hairapp.models.Usuario;
import br.com.bruno.hairapp.tasks.ClientesCadastroTask;
import br.com.bruno.hairapp.utils.Autenticacao;

/**
 * Created by Bruno on 22/07/2015.
 */
public class CadastroActivity extends Activity {

    private Usuario usuario;
    private EditText editUsuario;
    private EditText editSenha;
    private EditText editLembreteSenha;
    private Button btnCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        usuario = new Usuario();
        findViews();

        new Autenticacao().auteticar(getBaseContext());

        usuario.setId(0);
        usuario.setCdStatus(15);
        usuario.setIdTipoPermissao(1);
        usuario.setDsEmail("brunooliveira_717@hotmail.com");


        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setDsLogin("Teste android login");
                usuario.setCdSenha("android123");
                usuario.setDsLembreteSenha("Teste android lembrete de senha");
                //Log.i("Cadastro", editUsuario.getText().toString() + " " + editSenha.getText().toString() + " teste");


                cadastroClientes();

            }
        });

    }

    private void findViews() {
        editUsuario = (EditText) findViewById(R.id.activity_cadastro_edit_usuario);
        editSenha = (EditText) findViewById(R.id.activity_cadastro_edit_senha);
        editLembreteSenha = (EditText) findViewById(R.id.activity_cadastro_edit_lembrete_senha);
        btnCadastro = (Button) findViewById(R.id.activity_cadastro_button_cadastro);
    }

    private void cadastroClientes() {

        ClientesCadastroTask clientesCadastroTask = new ClientesCadastroTask(usuario, new ClientesCadastroTask.OnReturnListProdutos() {
            @Override
            public void onCompletion(Usuario usuario) {
                Log.i("onCompletion", usuario.getDsLogin());
            }

            @Override
            public void onError() {
                Log.i("onError", "Deu erro!");
            }
        });
        clientesCadastroTask.execute();

        /*ClientesCadastroTask clientesCadastroTask = new ClientesCadastroTask(usuario, new ClientesCadastroTask.OnReturnListProdutos() {
            @Override
            public void onCompletion(List<Usuario> usuarios) {
                if (usuarios.size() > 0) {
                    Toast.makeText(getBaseContext(), usuarios.toString(), Toast.LENGTH_LONG).show();
                    //((MainActivity) getActivity()).animateTransitionFragments(ControlerFragments.SelectedFragment.MENU);
                    //agendarSing.setPositionNavegation(ControlerFragments.SelectedFragment.MENU);
                } else {
                    Toast.makeText(getBaseContext(), "Não foi possível fazer a reserva!", Toast.LENGTH_LONG).show();
                    Log.i("Resposta do servidor", "array menor q 1!");
                }
            }

            @Override
            public void onError() {
                Log.e("Erro:", "Deu Erro!");
            }
        });
        clientesCadastroTask.execute();*/
    }


}
