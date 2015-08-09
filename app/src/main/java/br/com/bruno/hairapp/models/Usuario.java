package br.com.bruno.hairapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Bruno on 22/07/2015.
 */

@JsonIgnoreProperties({"usuario"})
public class Usuario {

    @JsonProperty("id")
    private int id;

    @JsonProperty("idTipoPermissao")
    private int idTipoPermissao;

    @JsonProperty("dsEmail")
    private String dsEmail;

    @JsonProperty("dtOperacao")
    private long dtOperacao;

    @JsonProperty("cdStatus")
    private long cdStatus;

    @JsonProperty("dsLogin")
    private String dsLogin;

    @JsonProperty("cdSenha")
    private String cdSenha;

    @JsonProperty("dsLembreteSenha")
    private String dsLembreteSenha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipoPermissao() {
        return idTipoPermissao;
    }

    public void setIdTipoPermissao(int idTipoPermissao) {
        this.idTipoPermissao = idTipoPermissao;
    }

    public long getCdStatus() {
        return cdStatus;
    }

    public void setCdStatus(long cdStatus) {
        this.cdStatus = cdStatus;
    }

    public String getDsLogin() {
        return dsLogin;
    }

    public void setDsLogin(String dsLogin) {
        this.dsLogin = dsLogin;
    }

    public String getDsLembreteSenha() {
        return dsLembreteSenha;
    }

    public void setDsLembreteSenha(String dsLembreteSenha) {
        this.dsLembreteSenha = dsLembreteSenha;
    }

    public String getCdSenha() {
        return cdSenha;
    }

    public void setCdSenha(String cdSenha) {
        this.cdSenha = cdSenha;
    }

    public long getDtOperacao() {
        return dtOperacao;
    }

    public void setDtOperacao(long dtOperacao) {
        this.dtOperacao = dtOperacao;
    }


    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }
}
