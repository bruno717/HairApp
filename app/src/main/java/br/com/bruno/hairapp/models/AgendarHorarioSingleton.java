package br.com.bruno.hairapp.models;

import java.util.ArrayList;

/**
 * Created by Bruno on 08/02/2015.
 */
public class AgendarHorarioSingleton {

    private static AgendarHorarioSingleton INSTANCE;

    private int positionFuncao;
    private int positionHorario;
    private int positionFuncionario;
    private int positionProcedimento;
    private String data;
    private ArrayList<Funcionario> funcionarioList;
    private ArrayList<Horario> horarioList;
    private ArrayList<Funcao> funcaoList;
    private ArrayList<Procedimento> procedimentoList;
    private ArrayList<AgendaCliente> agendaClienteList;
    private Reserva reserva;
    private int positionNavegation;

    public AgendarHorarioSingleton() {
//        funcionarioList = new ArrayList();
//        horarioList = new ArrayList();
        reserva = new Reserva();
    }


    public static synchronized AgendarHorarioSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AgendarHorarioSingleton();
        }
        return INSTANCE;
    }

    public ArrayList<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(ArrayList<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    public int getPositionFuncao() {
        return positionFuncao;
    }

    public void setPositionFuncao(int positionFuncao) {
        this.positionFuncao = positionFuncao;
    }

    public ArrayList<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(ArrayList<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getPositionHorario() {
        return positionHorario;
    }

    public void setPositionHorario(int positionHorario) {
        this.positionHorario = positionHorario;
    }

    public int getPositionFuncionario() {
        return positionFuncionario;
    }

    public void setPositionFuncionario(int positionFuncionario) {
        this.positionFuncionario = positionFuncionario;
    }

    public ArrayList<Funcao> getFuncaoList() {
        return funcaoList;
    }

    public void setFuncaoList(ArrayList<Funcao> funcaoList) {
        this.funcaoList = funcaoList;
    }

    public int getPositionNavegation() {
        return positionNavegation;
    }

    public void setPositionNavegation(int positionNavegation) {
        this.positionNavegation = positionNavegation;
    }

    public ArrayList<Procedimento> getProcedimentoList() {
        return procedimentoList;
    }

    public void setProcedimentoList(ArrayList<Procedimento> procedimentoList) {
        this.procedimentoList = procedimentoList;
    }

    public int getPositionProcedimento() {
        return positionProcedimento;
    }

    public void setPositionProcedimento(int positionProcedimento) {
        this.positionProcedimento = positionProcedimento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<AgendaCliente> getAgendaClienteList() {
        return agendaClienteList;
    }

    public void setAgendaClienteList(ArrayList<AgendaCliente> agendaClienteList) {
        this.agendaClienteList = agendaClienteList;
    }
}
