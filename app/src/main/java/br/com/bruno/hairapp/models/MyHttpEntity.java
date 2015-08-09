package br.com.bruno.hairapp.models;

/**
 * Created by Bruno on 05/08/2015.
 */
public class MyHttpEntity {

    private static MyHttpEntity INSTANCE;

    private String sessionId = "";

    public static synchronized MyHttpEntity getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MyHttpEntity();
        }
        return INSTANCE;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
