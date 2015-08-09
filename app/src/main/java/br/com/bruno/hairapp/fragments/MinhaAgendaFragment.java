package br.com.bruno.hairapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.adapters.MinhaAgendaListAdapter;
import br.com.bruno.hairapp.models.AgendaCliente;
import br.com.bruno.hairapp.models.AgendarHorarioSingleton;
import br.com.bruno.hairapp.tasks.AgendaClienteTask;
import br.com.bruno.hairapp.tasks.ReservaExcluirTask;

/**
 * Created by Bruno on 16/02/2015.
 */
public class MinhaAgendaFragment extends BaseFragment {

    private ListView listView;
    private MinhaAgendaListAdapter adapter;
    private AlertDialog alerta;
    private AlertDialog.Builder builder;
    private AgendarHorarioSingleton agendarSing;
    private Bundle bundle;
    private LayoutInflater inflater;
    private ViewGroup viewGroup;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bundle = savedInstanceState;
        viewGroup = container;
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_minha_agenda, null);

        agendarSing = AgendarHorarioSingleton.getInstance();
        findViews(view);
        buscarReservas();

        // startLoading(view.getContext(), "Carregando agenda!");


        listView.setOnItemClickListener(itemSelectedAgenda());

        return view;
    }

    private void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.fragment_minha_agenda_list);
    }

    public void buscarReservas() {
        AgendaClienteTask agendaClienteTask = new AgendaClienteTask(1, new AgendaClienteTask.OnReturnAgendaCliente() {
            @Override
            public void onCompletion(ArrayList<AgendaCliente> agendaClienteList) {
                //   stopLoading();
                agendarSing.setAgendaClienteList(agendaClienteList);
                adapter = new MinhaAgendaListAdapter(getActivity().getBaseContext(), agendaClienteList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError() {
                //   stopLoading();
            }
        });
        agendaClienteTask.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Script", "onPause()");
    }

    public AdapterView.OnItemClickListener itemSelectedAgenda() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Título");
                builder.setMessage("Deseja excluir reserva?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluirReserva(position);
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Resposta", "não");
                    }
                });

                alerta = builder.create();
                alerta.show();
            }
        };
    }

    private void excluirReserva(final int position) {
        ReservaExcluirTask reservaExcluirTask = new ReservaExcluirTask(agendarSing.getAgendaClienteList().get(position).getIdReserva(), new ReservaExcluirTask.OnReturnReservaExclusao() {
            @Override
            public void onCompletion(String response) {
                if (response.equals("OK")) {
                    Toast.makeText(getActivity(), "Atendimento excluído!", Toast.LENGTH_SHORT).show();

                    AgendaCliente item = agendarSing.getAgendaClienteList().get(position);
                    agendarSing.getAgendaClienteList().remove(item);
                    adapter = new MinhaAgendaListAdapter(getActivity().getBaseContext(), agendarSing.getAgendaClienteList());
                    listView.setAdapter(adapter);

                } else {
                    Toast.makeText(getActivity(), "Atendimento não foi excluído.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(getActivity(), "Atendimento não foi excluído. Tente mais tarde! :/", Toast.LENGTH_SHORT).show();
            }
        });
        reservaExcluirTask.execute();
    }
}