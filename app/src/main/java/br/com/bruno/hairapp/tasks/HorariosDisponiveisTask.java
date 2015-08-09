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

import br.com.bruno.hairapp.models.Horario;

/**
 * Created by Bruno on 01/02/2015.
 */
public class HorariosDisponiveisTask extends AsyncTask<Void, Void, Boolean> {

    private List<Horario> horariosList;
    private OnReturnListHorarios mListener;
    private String data;
    private int funcionario;
    private int cliente;
    private final static String DATA = "data";
    private final static String FUNCIONARIO = "funcionario";
    private final static String CLIENTE = "cliente";

    public HorariosDisponiveisTask(String data, int funcionario, int cliente, OnReturnListHorarios listener) {
        this.mListener = listener;
        this.data = data;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final String SOAP_ACTION = "http://ws/listaDeHorariosDisponiveis";
        final String NAMESPACE = "http://ws/";
        final String METHOD_NAME = "listaDeHorariosDisponiveis";
        final String URL ="";// new UrlServerConection().getUrlConection();
        horariosList = new ArrayList<>();

        try {
            SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);

            soap.addProperty(DATA, data);
            soap.addProperty(FUNCIONARIO, funcionario);
            soap.addProperty(CLIENTE, cliente);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // envelope.dotNet = true;
            envelope.setOutputSoapObject(soap);

            envelope.addMapping(NAMESPACE, "ArrayList", new ArrayList<Horario>().getClass());

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call(SOAP_ACTION, envelope);

            SoapObject soapSize = (SoapObject) envelope.bodyIn;

            //Condição para saber se o Vector é maior que um
            if (soapSize.getPropertyCount() > 1) {

                Vector vectorResponse = (Vector) envelope.getResponse();

                for (int i = 0; i < vectorResponse.size(); i++) {
                    SoapObject soapObject = (SoapObject) vectorResponse.get(i);
                    if (soapObject != null) {
                        Horario horario = new Horario();
                        horario.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                        horario.setHora(soapObject.getProperty("hora").toString());
                        horariosList.add(horario);
                    }
                }
            } else {
                SoapObject soapObject = (SoapObject) soapSize.getProperty(0);

                Horario horario = new Horario();
                horario.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
                horario.setHora(soapObject.getProperty("hora").toString());
                horariosList.add(horario);
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
            if (sucess) mListener.onCompletion((ArrayList<Horario>) horariosList);
            else mListener.onError();
        }
    }


    public interface OnReturnListHorarios {
        public void onCompletion(ArrayList<Horario> horarios);

        public void onError();
    }
}