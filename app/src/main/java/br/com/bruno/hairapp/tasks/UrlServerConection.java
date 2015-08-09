package br.com.bruno.hairapp.tasks;

/**
 * Created by Bruno on 04/02/2015.
 */
public class UrlServerConection {

    private static final String URL = "http://192.168.1.3:8084/WsSalaoBeleza/WebServiceSalaoBeleza?wsdl";

    public String getUrlConection(){
        return URL;
    }
}
