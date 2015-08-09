package br.com.bruno.hairapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.adapters.FuncionarioListAdapter;
import br.com.bruno.hairapp.models.AgendarHorarioSingleton;
import br.com.bruno.hairapp.models.Funcionario;
import br.com.bruno.hairapp.tasks.FuncionariosTask;
import br.com.bruno.hairapp.utils.ControlerFragments;

/**
 * Created by Bruno on 15/02/2015.
 */
public class FuncionarioFragment extends BaseFragment {

    private ListView listView;
    private FuncionarioListAdapter adapter;
    private List<Funcionario> funcionarioList;
    private AgendarHorarioSingleton agendarSing;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_funcionario, null);

        findViews(view);

        agendarSing = AgendarHorarioSingleton.getInstance();

        listView.setOnItemClickListener(funcionarioSelected());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        showFuncionarios();
    }

    public void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.fragment_funcionario_list);
    }

    public void showFuncionarios() {
        FuncionariosTask funcionariosTask = new FuncionariosTask(agendarSing.getFuncaoList().get(agendarSing.getPositionFuncao()).getId(), new FuncionariosTask.OnReturnListFuncionarios() {
            @Override
            public void onCompletion(ArrayList<Funcionario> funcionarios) {

                agendarSing.setFuncionarioList(funcionarios);
                adapter = new FuncionarioListAdapter(getActivity().getBaseContext(), funcionarios);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError() {
            }
        });
        funcionariosTask.execute();
    }

    public AdapterView.OnItemClickListener funcionarioSelected() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                agendarSing.setPositionFuncionario(position);
                showFragment(ControlerFragments.SelectedFragment.DATA_HORARIO);
                Log.i("Funcionário selecionado", agendarSing.getFuncionarioList().get(position).getNome());
            }
        };
    }
}
