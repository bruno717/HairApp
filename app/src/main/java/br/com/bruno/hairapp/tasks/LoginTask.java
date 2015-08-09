package br.com.bruno.hairapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import br.com.bruno.hairapp.models.Funcionario;
import br.com.bruno.hairapp.models.Pessoa;

/**
 * Created by Bruno on 15/02/2015.
 */
public class LoginTask extends AsyncTask<Void, Void, Boolean> {
    private OnReturnListFuncionarios mListener;
    private Pessoa pessoa;

    public LoginTask(Pessoa pessoa, OnReturnListFuncionarios listener) {
        this.mListener = listener;
        this.pessoa = pessoa;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        final String SOAP_ACTION = "http://ws/loginPessoa";
        final String NAMESPACE = "http://ws/";
        final String METHOD_NAME = "loginPessoa";
        final String URL = new UrlServerConection().getUrlConection();

        try {
            SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);

            PropertyInfo pi = new PropertyInfo();
            pi.setName("pessoa");
            pi.setValue(pessoa);
            pi.setType(pessoa.getClass());

            soap.addProperty(pi);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // envelope.dotNet = true;
            envelope.setOutputSoapObject(soap);

            envelope.addMapping(NAMESPACE, "Pessoa", new ArrayList<Funcionario>().getClass());

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);
            SoapObject soapObject = (SoapObject) envelope.getResponse();


            if (soapObject != null) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                pessoa.setNome(soapObject.getProperty("nome").toString());
                pessoa.setTelefone(soapObject.getProperty("telefone").toString());
                pessoa.setEmail(soapObject.getProperty("email").toString());
                pessoa.setDataDeNascimento(soapObject.getProperty("dataDeNascimento").toString());
                pessoa.setUsuario(soapObject.getProperty("usuario").toString());
                pessoa.setSenha(soapObject.getProperty("senha").toString());
                pessoa.setPerfil(Integer.parseInt(soapObject.getProperty("perfil").toString()));
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERRO", e.toString());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean sucess) {

        if (mListener != null) {
            if (sucess) mListener.onCompletion(pessoa);
            else mListener.onError();
        }
    }

    public interface OnReturnListFuncionarios {
        public void onCompletion(Pessoa pessoa);

        public void onError();
    }
}