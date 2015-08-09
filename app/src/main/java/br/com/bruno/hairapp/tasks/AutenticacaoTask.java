package br.com.bruno.hairapp.tasks;


import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import br.com.bruno.hairapp.models.MyHttpEntity;
import br.com.bruno.hairapp.utils.ConfigAuthentication;

/**
 * Created by Bruno on 04/02/2015.
 */
public class AutenticacaoTask extends AsyncTask<Void, Void, Boolean> {

    private OnReturnListProdutos mListener;
    private String strResponse;
    private MyHttpEntity myHttpEntity;

    public AutenticacaoTask(OnReturnListProdutos listener) {
        this.mListener = listener;
        myHttpEntity = MyHttpEntity.getInstance();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        String url = UrlServerConection.URL + UrlServerConection.LOGIN;

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add(ConfigAuthentication.KEY_USERNAME, ConfigAuthentication.KEY_VALUE);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> requestEntity = new HttpEntity<Object>(body, requestHeaders);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            strResponse = response.getBody();
            Log.i("Funcionou", strResponse);
            myHttpEntity.setSessionId(response.getHeaders().getFirst(ConfigAuthentication.SET_COOKIE));
            return true;
        } catch (Exception e) {
            Log.e("Erro", e.getLocalizedMessage(), e);
        }


        return false;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (mListener != null) {
            if (success) mListener.onCompletion(strResponse);
            else mListener.onError();
        }
    }

    public interface OnReturnListProdutos {
        public void onCompletion(String response);

        public void onError();
    }
}
