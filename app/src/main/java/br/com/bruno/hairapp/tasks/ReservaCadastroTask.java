package br.com.bruno.hairapp.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.bruno.hairapp.models.Reserva;


/**
 * Created by Bruno on 12/02/2015.
 */
public class ReservaCadastroTask extends AsyncTask<Void, Void, Boolean> {


    private OnReturnCadastroReserva listener;
    private Reserva reserva;
    private String response;

    public ReservaCadastroTask(Reserva reserva, OnReturnCadastroReserva listener) {
        this.listener = listener;
        this.reserva = reserva;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final String SOAP_ACTION = "http://ws/insereReserva";
        final String NAMESPACE = "http://ws/";
        final String METHOD_NAME = "insereReserva";
        final String URL = new UrlServerConection().getUrlConection();

        try {
            SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);

            PropertyInfo pi = new PropertyInfo();

            pi.setName("reserva");
            pi.setValue(reserva);
            pi.setType(reserva.getClass());

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

    public interface OnReturnCadastroReserva {
        public void onCompletion(String response);

        public void onError(String response);
    }
}
