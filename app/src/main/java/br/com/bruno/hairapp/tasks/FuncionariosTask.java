package br.com.bruno.hairapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.com.bruno.hairapp.models.Funcionario;

/**
 * Created by Bruno on 15/02/2015.
 */
public class FuncionariosTask extends AsyncTask<Void, Void, Boolean> {
    private OnReturnListFuncionarios mListener;
    private List<Funcionario> listFuncionarios;
    private int idFuncao;

    public FuncionariosTask(int idFuncao, OnReturnListFuncionarios listener) {
        this.mListener = listener;
        this.idFuncao = idFuncao;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        final String SOAP_ACTION = "http://ws/listaDeFuncionarios";
        final String NAMESPACE = "http://ws/";
        final String METHOD_NAME = "listaDeFuncionarios";
        final String URL = new UrlServerConection().getUrlConection();
        listFuncionarios = new ArrayList<>();

        try {
            SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);

            soap.addProperty("idFuncao", idFuncao);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // envelope.dotNet = true;
            envelope.setOutputSoapObject(soap);

            envelope.addMapping(NAMESPACE, "ArrayList", new ArrayList<Funcionario>().getClass());

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);

            SoapObject soapSize = (SoapObject) envelope.bodyIn;

            //Condição para saber se o Vector é maior que um
            if (soapSize.getPropertyCount() > 1) {

                Vector vectorResponse = (Vector) envelope.getResponse();

                for (int i = 0; i < vectorResponse.size(); i++) {

                    SoapObject soapObject = (SoapObject) vectorResponse.get(i);
                    if (soapObject != null) {
                        Funcionario funcionario = new Funcionario();
                        funcionario.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                        funcionario.setNome(soapObject.getProperty("nome").toString());
                        funcionario.setTelefone(soapObject.getProperty("telefone").toString());
                        funcionario.setCelular(soapObject.getProperty("celular").toString());
                        funcionario.setCidade(soapObject.getProperty("cidade").toString());
                        funcionario.setEstado(soapObject.getProperty("estado").toString());
                        funcionario.setCep(soapObject.getProperty("cep").toString());
                        funcionario.setEmail(soapObject.getProperty("email").toString());
                        funcionario.setEstado(soapObject.getProperty("estado").toString());
                        funcionario.setIdFuncao(Integer.parseInt(soapObject.getProperty("idFuncao").toString()));
                        listFuncionarios.add(funcionario);
                    }
                }

            } else {
                SoapObject soapObject = (SoapObject) soapSize.getProperty(0);

                Funcionario funcionario = new Funcionario();
                funcionario.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                funcionario.setNome(soapObject.getProperty("nome").toString());
                funcionario.setTelefone(soapObject.getProperty("telefone").toString());
                funcionario.setCelular(soapObject.getProperty("celular").toString());
                funcionario.setCidade(soapObject.getProperty("cidade").toString());
                funcionario.setEstado(soapObject.getProperty("estado").toString());
                funcionario.setCep(soapObject.getProperty("cep").toString());
                funcionario.setEmail(soapObject.getProperty("email").toString());
                funcionario.setEstado(soapObject.getProperty("estado").toString());
                funcionario.setIdFuncao(Integer.parseInt(soapObject.getProperty("idFuncao").toString()));
                listFuncionarios.add(funcionario);
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
            if (sucess) mListener.onCompletion((ArrayList) listFuncionarios);
            else mListener.onError();
        }
    }

    public interface OnReturnListFuncionarios {
        public void onCompletion(ArrayList<Funcionario> listFuncionarios);

        public void onError();
    }
}