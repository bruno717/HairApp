package br.com.bruno.hairapp.tasks;


import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import br.com.bruno.hairapp.models.MyHttpEntity;
import br.com.bruno.hairapp.models.Usuario;
import br.com.bruno.hairapp.utils.Autenticacao;
import br.com.bruno.hairapp.utils.ConfigAuthentication;

/**
 * Created by Bruno on 04/02/2015.
 */
public class ClientesCadastroTask extends AsyncTask<Void, Void, Boolean> {

    private OnReturnListProdutos mListener;
    private Usuario usuario;
    private MyHttpEntity myHttpEntity;

    public ClientesCadastroTask(Usuario usuario, OnReturnListProdutos listener) {
        this.mListener = listener;
        this.usuario = usuario;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        myHttpEntity = MyHttpEntity.getInstance();

        headers.add(ConfigAuthentication.COOKIE, myHttpEntity.getSessionId());
        HttpEntity httpEntity = new HttpEntity<Object>(usuario, headers);

        String url = String.format(Locale.US, "%s%s", UrlServerConection.URL, "/users");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            TypeReference ref = new TypeReference<Usuario>() {
            };
            usuario = mapper.readValue(response.getBody(), ref);
            return true;
        } catch (HttpClientErrorException ex) {
            Log.e("Erro", ex.getStatusCode().toString());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (mListener != null) {
            if (success) mListener.onCompletion(usuario);
            else mListener.onError();
        }
    }

    public interface OnReturnListProdutos {
        public void onCompletion(Usuario usuario);

        public void onError();
    }
}
