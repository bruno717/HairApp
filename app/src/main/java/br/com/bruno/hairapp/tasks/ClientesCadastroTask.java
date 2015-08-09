package br.com.bruno.hairapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.bruno.hairapp.models.Pessoa;


/**
 * Created by Bruno on 04/02/2015.
 */
public class ClientesCadastroTask extends AsyncTask<Void, Void, Boolean> {

    private OnReturnCadastroCliente listener;
    private Pessoa cliente;
    private String response;

    public ClientesCadastroTask(Pessoa cliente, OnReturnCadastroCliente listener) {
        this.listener = listener;
        this.cliente = cliente;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        final String SOAP_ACTION = "http://ws/insereCliente";
        final String NAMESPACE = "http://ws/";
        final String METHOD_NAME = "insereCliente";
        final String URL = new UrlServerConection().getUrlConection();

        try {
            SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);

            PropertyInfo pi = new PropertyInfo();

            pi.setName("cliente");
            pi.setValue(cliente);
            pi.setType(cliente.getClass());

            soap.addProperty(pi);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // envelope.dotNet = true;
            envelope.setOutputSoapObject(soap);

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);
            SoapPrimitive resultString = (SoapPrimitive) envelope.getResponse();
            response = resultString.toString();
            return true;

        } catch (Exception e) {
            Log.e("Erro", e.toString());
        }

        return false;
    }


    @Override
    protected void onPostExecute(Boolean sucess) {
        if (listener != null) {
            if (sucess) listener.onCompletion(response);
            else listener.onError(response);
        }
    }

    public interface OnReturnCadastroCliente {
        public void onCompletion(String response);

        public void onError(String response);
    }
}
