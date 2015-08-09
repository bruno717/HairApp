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

import br.com.bruno.hairapp.models.Funcao;

/**
 * Created by Bruno on 15/02/2015.
 */
public class FuncoesTask extends AsyncTask<Void, Void, Boolean> {

    private OnReturnListFuncoes mListener;
    private List<Funcao> funcaoList;

    public FuncoesTask(OnReturnListFuncoes listener) {
        this.mListener = listener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final String SOAP_ACTION = "http://ws/listaDeFuncoes";
        final String NAMESPACE = "http://ws/";
        final String METHOD_NAME = "listaDeFuncoes";
        final String URL ="";// new UrlServerConection().getUrlConection();
        funcaoList = new ArrayList<>();

        try {
            SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // envelope.dotNet = true;
            envelope.setOutputSoapObject(soap);

            envelope.addMapping(NAMESPACE, "ArrayList", new ArrayList<Funcao>().getClass());

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);

            SoapObject soapSize = (SoapObject) envelope.bodyIn;

            //Condição para saber se o Vector é maior que um
            if (soapSize.getPropertyCount() > 1) {

                Vector vectorResponse = (Vector) envelope.getResponse();


                for (int i = 0; i < vectorResponse.size(); i++) {
                    SoapObject soapObject = (SoapObject) vectorResponse.get(i);
                    if (soapObject != null) {
                        Funcao funcao = new Funcao();
                        funcao.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                        funcao.setNome(soapObject.getProperty("nome").toString());
                        funcaoList.add(funcao);
                    }
                }
            } else {
                SoapObject soapObject = (SoapObject) soapSize.getProperty(0);

                Funcao funcao = new Funcao();
                funcao.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                funcao.setNome(soapObject.getProperty("nome").toString());
                funcaoList.add(funcao);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("ERRO", e.toString());
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean sucess) {
        if (mListener != null) {
            if (sucess) mListener.onCompletion((ArrayList<Funcao>) funcaoList);
            else mListener.onError();
        }
    }


    public interface OnReturnListFuncoes {
        public void onCompletion(ArrayList<Funcao> funcoes);

        public void onError();
    }

}
