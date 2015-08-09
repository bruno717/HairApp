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

import br.com.bruno.hairapp.models.Procedimento;

/**
 * Created by Bruno on 10/02/2015.
 */
public class ProcedimentosTask extends AsyncTask<Void, Void, Boolean> {

    private OnReturnProcedimentos listener;
    private List<Procedimento> listProcedimentos;
    private int idFuncao;

    public ProcedimentosTask(int idFuncao, OnReturnProcedimentos listener) {
        this.listener = listener;
        this.idFuncao = idFuncao;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        final String SOAP_ACTION = "http://ws/listaDeProcedimentos";
        final String NAMESPACE = "http://ws/";
        final String METHOD_NAME = "listaDeProcedimentos";
        final String URL = new UrlServerConection().getUrlConection();
        listProcedimentos = new ArrayList<>();

        try {
            SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);

            soap.addProperty("idFuncao", idFuncao);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // envelope.dotNet = true;
            envelope.setOutputSoapObject(soap);

            envelope.addMapping(NAMESPACE, "ArrayList", new ArrayList<Procedimento>().getClass());

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);

            SoapObject soapSize = (SoapObject) envelope.bodyIn;

            //Condição para saber se o Vector é maior que um
            if (soapSize.getPropertyCount() > 1) {

                Vector vectorResponse = (Vector) envelope.getResponse();

                for (int i = 0; i < vectorResponse.size(); i++) {
                    SoapObject soapObject = (SoapObject) vectorResponse.get(i);
                    if (soapObject != null) {
                        Procedimento procedimento = new Procedimento();
                        procedimento.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                        procedimento.setDescricao(soapObject.getProperty("descricao").toString());
                        procedimento.setTempo(soapObject.getProperty("tempo").toString());
                        procedimento.setIdFuncao(Integer.parseInt(soapObject.getProperty("idFuncao").toString()));
                        listProcedimentos.add(procedimento);
                    }
                }
            } else {
                SoapObject soapObject = (SoapObject) soapSize.getProperty(0);

                Procedimento procedimento = new Procedimento();
                procedimento.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                procedimento.setDescricao(soapObject.getProperty("descricao").toString());
                procedimento.setTempo(soapObject.getProperty("tempo").toString());
                procedimento.setIdFuncao(Integer.parseInt(soapObject.getProperty("idFuncao").toString()));
                listProcedimentos.add(procedimento);
            }
            return true;

        } catch (Exception e) {
            Log.e("Erro", e.toString());
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean sucess) {
        if (listener != null) {
            if (sucess) listener.onCompletion((ArrayList<Procedimento>) listProcedimentos);
            else listener.onError();
        }
    }

    public interface OnReturnProcedimentos {
        public void onCompletion(ArrayList<Procedimento> listProcedimentos);

        public void onError();
    }
}