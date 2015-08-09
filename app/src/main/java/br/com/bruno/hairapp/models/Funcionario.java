package br.com.bruno.hairapp.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by Bruno on 01/02/2015.
 */
public class Funcionario implements KvmSerializable {

    private int id;
    private String nome;
    private String telefone;
    private String celular;
    private String cidade;
    private String estado;
    private String cep;
    private String email;
    private int idFuncao;

//    public Funcionario() {
//        this.id = 0;
//        this.nome = "";
//        this.telefone = "";
//        this.celular = "";
//        this.cidade = "";
//        this.estado = "";
//        this.cep = "";
//        this.email = "";
//        this.idFuncao = 0;
//    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return id;
            case 1:
                return nome;
            case 2:
                return telefone;
            case 3:
                return celular;
            case 4:
                return cidade;
            case 5:
                return estado;
            case 6:
                return cep;
            case 7:
                return email;
            case 8:
                return idFuncao;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 9;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                id = Integer.parseInt(o.toString());
                break;
            case 1:
                nome = o.toString();
                break;
            case 2:
                telefone = o.toString();
                break;
            case 3:
                celular = o.toString();
                break;
            case 4:
                cidade = o.toString();
                break;
            case 5:
                estado = o.toString();
                break;
            case 6:
                cep = o.toString();
                break;
            case 7:
                email = o.toString();
                break;
            case 8:
                idFuncao = Integer.parseInt(o.toString());
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "id";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "nome";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "telefone";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "celular";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "cidade";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "estado";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "cep";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "email";
                break;
            case 8:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "idFuncao";
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }
}