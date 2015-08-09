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

import br.com.bruno.hairapp.models.AgendaCliente;
import br.com.bruno.hairapp.models.Funcionario;

/**
 * Created by Bruno on 16/02/2015.
 */
public class AgendaClienteTask extends AsyncTask<Void, Void, Boolean> {
    private OnReturnAgendaCliente mListener;
    private List<AgendaCliente> agendaClienteList;
    private int idCliente;

    public AgendaClienteTask(int idCliente, OnReturnAgendaCliente listener) {
        this.mListener = listener;
        this.idCliente = idCliente;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        final String SOAP_ACTION = "http://ws/listaAgendaCliente";
        final String NAMESPACE = "http://ws/";
        final String METHOD_NAME = "listaAgendaCliente";
        final String URL = new UrlServerConection().getUrlConection();
        agendaClienteList = new ArrayList<>();

        try {
            SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);

            soap.addProperty("idCliente", idCliente);

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
                        AgendaCliente agendaCliente = new AgendaCliente();
                        agendaCliente.setIdReserva(Integer.parseInt(soapObject.getProperty("idReserva").toString()));
                        agendaCliente.setProfissional(soapObject.getProperty("profissional").toString());
                        agendaCliente.setData(soapObject.getProperty("data").toString());
                        agendaCliente.setHora(soapObject.getProperty("hora").toString());
                        agendaCliente.setProcedimento(soapObject.getProperty("procedimento").toString());
                        agendaClienteList.add(agendaCliente);
                    }
                }

            } else {
                SoapObject soapObject = (SoapObject) soapSize.getProperty(0);

                AgendaCliente agendaCliente = new AgendaCliente();
                agendaCliente.setIdReserva(Integer.parseInt(soapObject.getProperty("idReserva").toString()));
                agendaCliente.setProfissional(soapObject.getProperty("profissional").toString());
                agendaCliente.setData(soapObject.getProperty("data").toString());
                agendaCliente.setHora(soapObject.getProperty("hora").toString());
                agendaCliente.setProcedimento(soapObject.getProperty("procedimento").toString());
                agendaClienteList.add(agendaCliente);
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
            if (sucess) mListener.onCompletion((ArrayList) agendaClienteList);
            else mListener.onError();
        }
    }

    public interface OnReturnAgendaCliente {
        public void onCompletion(ArrayList<AgendaCliente> agendaClienteList);

        public void onError();
    }

}