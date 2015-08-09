package br.com.bruno.hairapp.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by Bruno on 04/02/2015.
 */
public class Pessoa implements KvmSerializable {

    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String dataDeNascimento;
    private String usuario;
    private String senha;
    private int perfil;

    public Pessoa() {
        id = 0;
        nome = "";
        telefone = "";
        email = "";
        dataDeNascimento = "";
        usuario = "";
        senha = "";
        perfil = -1;
    }

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
                return email;
            case 4:
                return dataDeNascimento;
            case 5:
                return usuario;
            case 6:
                return senha;
            case 7:
                return getPerfil();
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 8;
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
                email = o.toString();
                break;
            case 4:
                dataDeNascimento = o.toString();
                break;
            case 5:
                usuario = o.toString();
                break;
            case 6:
                senha = o.toString();
                break;
            case 7:
                setPerfil(Integer.parseInt(o.toString()));
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
                propertyInfo.name = "email";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "dataDeNascimento";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "usuario";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "senha";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "perfil";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }
}
