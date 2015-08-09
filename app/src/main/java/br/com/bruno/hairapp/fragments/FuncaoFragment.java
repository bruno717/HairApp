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

import br.com.bruno.hairapp.MainActivity;
import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.adapters.FuncaoListAdapter;
import br.com.bruno.hairapp.models.AgendarHorarioSingleton;
import br.com.bruno.hairapp.models.Funcao;
import br.com.bruno.hairapp.tasks.FuncoesTask;
import br.com.bruno.hairapp.utils.ControlerFragments;

/**
 * Created by Bruno on 14/02/2015.
 */
public class FuncaoFragment extends BaseFragment {

    private ListView listView;
    private FuncaoListAdapter adapter;
    private List<Funcao> funcaoList;
    private AgendarHorarioSingleton agendarSing;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_funcao, null);

        findViews(view);
        showAtividades();
        agendarSing = AgendarHorarioSingleton.getInstance();

        listView.setOnItemClickListener(funcaoSelected());

        return view;
    }

    public void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.fragment_funcao_list);
    }

    public void showAtividades() {
        FuncoesTask funcoesTask = new FuncoesTask(new FuncoesTask.OnReturnListFuncoes() {
            @Override
            public void onCompletion(ArrayList<Funcao> funcoes) {
                agendarSing.setFuncaoList(funcoes);
                adapter = new FuncaoListAdapter(getActivity().getBaseContext(), funcoes);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError() {
            }
        });
        funcoesTask.execute();
    }

    public AdapterView.OnItemClickListener funcaoSelected() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                agendarSing.setPositionFuncao(position);
                showFragment(ControlerFragments.SelectedFragment.FUNCIONARIO);
                Log.i("Funcao selecionada", agendarSing.getFuncaoList().get(position).getNome());
            }
        };
    }
}
