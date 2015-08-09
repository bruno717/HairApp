package br.com.bruno.hairapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.bruno.hairapp.MainActivity;
import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.models.AgendarHorarioSingleton;
import br.com.bruno.hairapp.models.Reserva;
import br.com.bruno.hairapp.tasks.ReservaCadastroTask;
import br.com.bruno.hairapp.utils.ControlerFragments;

/**
 * Created by Bruno on 16/02/2015.
 */
public class ConfirmarAtendimentoFragment extends BaseFragment {

    private TextView txtProfissional;
    private TextView txtData;
    private TextView txtHorario;
    private TextView txtProcedimento;
    private Button btnCancelar;
    private Button btnConfirmar;
    private AgendarHorarioSingleton agendarSing;
    private Reserva reserva;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirmar_atendimento, null);

        agendarSing = AgendarHorarioSingleton.getInstance();
        findViews(view);

        txtProfissional.setText(agendarSing.getFuncionarioList().get(agendarSing.getPositionFuncionario()).getNome());
        txtData.setText(agendarSing.getData());
        txtHorario.setText(agendarSing.getHorarioList().get(agendarSing.getPositionHorario()).getHora());
        txtProcedimento.setText(agendarSing.getProcedimentoList().get(agendarSing.getPositionProcedimento()).getDescricao());

        btnCancelar.setOnClickListener(cancelarOperacao());
        btnConfirmar.setOnClickListener(confirmarReserva());

        return view;
    }

    private void montarReserva() {
        reserva = new Reserva();
        reserva.setData(agendarSing.getData());
        reserva.setProcedimento(agendarSing.getProcedimentoList().get(agendarSing.getPositionProcedimento()).getDescricao());
        reserva.setIdHora(agendarSing.getHorarioList().get(agendarSing.getPositionHorario()).getId());
        reserva.setIdCliente(1);
        reserva.setIdFuncionario(agendarSing.getFuncionarioList().get(agendarSing.getPositionFuncionario()).getId());
    }

    public void findViews(View view) {
        txtProfissional = (TextView) view.findViewById(R.id.fragment_confirmar_atendimento_text_profissional);
        txtData = (TextView) view.findViewById(R.id.fragment_confirmar_atendimento_text_data);
        txtHorario = (TextView) view.findViewById(R.id.fragment_confirmar_atendimento_text_horario);
        txtProcedimento = (TextView) view.findViewById(R.id.fragment_confirmar_atendimento_text_procedimento);
        btnCancelar = (Button) view.findViewById(R.id.fragment_confirmar_atendimento_button_cancelar);
        btnConfirmar = (Button) view.findViewById(R.id.fragment_confirmar_atendimento_button_confirmar);
    }

    public View.OnClickListener cancelarOperacao() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).animateTransitionFragments(ControlerFragments.SelectedFragment.MENU);
                agendarSing.setPositionNavegation(ControlerFragments.SelectedFragment.MENU);
            }
        };
    }

    public View.OnClickListener confirmarReserva() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                montarReserva();

                ReservaCadastroTask cadastroReservaTask = new ReservaCadastroTask(reserva, new ReservaCadastroTask.OnReturnCadastroReserva() {
                    @Override
                    public void onCompletion(String response) {
                        if (response.contains("sucesso")) {
                            Toast.makeText(getActivity().getBaseContext(), response, Toast.LENGTH_LONG).show();
                            ((MainActivity) getActivity()).animateTransitionFragments(ControlerFragments.SelectedFragment.MENU);
                            agendarSing.setPositionNavegation(ControlerFragments.SelectedFragment.MENU);
                        } else {
                            Toast.makeText(getActivity().getBaseContext(), "Não foi possível fazer a reserva!", Toast.LENGTH_LONG).show();
                            Log.i("Resposta do servidor", response);
                        }
                    }

                    @Override
                    public void onError(String response) {
                        Toast.makeText(getActivity().getBaseContext(), "Não foi possível fazer a reserva!", Toast.LENGTH_LONG).show();
                    }
                });
                cadastroReservaTask.execute();

            }
        };
    }

}