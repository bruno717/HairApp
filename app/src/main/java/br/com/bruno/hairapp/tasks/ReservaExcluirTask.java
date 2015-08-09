package br.com.bruno.hairapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Bruno on 17/02/2015.
 */
public class ReservaExcluirTask extends AsyncTask<Void, Void, Boolean> {

    private OnReturnReservaExclusao mListener;
    private String response;
    private int idReserva;

    public ReservaExcluirTask(int idReserva, OnReturnReservaExclusao listener) {
        this.mListener = listener;
        this.idReserva = idReserva;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final String SOAP_ACTION = "http://ws/deletaReserva";
        final String NAMESPACE = "http://ws/";
        final String METHOD_NAME = "deletaReserva";
        final String URL = new UrlServerConection().getUrlConection();

        try {
            SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);

            soap.addProperty("idReserva", idReserva);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // envelope.dotNet = true;
            envelope.setOutputSoapObject(soap);

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);

            SoapPrimitive soapResponse = (SoapPrimitive) envelope.getResponse();

            response = soapResponse.toString();

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
            if (sucess) mListener.onCompletion(response);
            else mListener.onError();
        }
    }


    public interface OnReturnReservaExclusao {
        public void onCompletion(String response);

        public void onError();
    }

}
