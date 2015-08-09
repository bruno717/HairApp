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

import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.adapters.ProcedimentoListAdapter;
import br.com.bruno.hairapp.models.AgendarHorarioSingleton;
import br.com.bruno.hairapp.models.Procedimento;
import br.com.bruno.hairapp.tasks.ProcedimentosTask;
import br.com.bruno.hairapp.utils.ControlerFragments;

/**
 * Created by Bruno on 16/02/2015.
 */
public class ProcedimentoFragment extends BaseFragment {

    private ListView listView;
    private AgendarHorarioSingleton agendarSing;
    private ProcedimentoListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_procedimento, null);

        findViews(view);
        agendarSing = AgendarHorarioSingleton.getInstance();
        showFuncionarios();

        listView.setOnItemClickListener(procedimentoSelected());

        return view;
    }

    public void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.fragment_procedimento_list);
    }

    public void showFuncionarios() {
        ProcedimentosTask funcionariosTask = new ProcedimentosTask(agendarSing.getFuncaoList().get(agendarSing.getPositionFuncao()).getId(), new ProcedimentosTask.OnReturnProcedimentos() {
            @Override
            public void onCompletion(ArrayList<Procedimento> listProcedimentos) {
                agendarSing.setProcedimentoList(listProcedimentos);
                adapter = new ProcedimentoListAdapter(getActivity().getBaseContext(), listProcedimentos);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError() {

            }
        });
        funcionariosTask.execute();
    }

    public AdapterView.OnItemClickListener procedimentoSelected() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                agendarSing.setPositionProcedimento(position);
                showFragment(ControlerFragments.SelectedFragment.CONFIRMAR_ATENDIMENTO);
                Log.i("Procedimento selecionado", agendarSing.getProcedimentoList().get(position).getDescricao());
            }
        };
    }
}
