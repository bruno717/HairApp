package br.com.bruno.hairapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import br.com.bruno.hairapp.MainActivity;
import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.utils.ControlerFragments;

/**
 * Created by Bruno on 14/02/2015.
 */
public class MenuFragment extends BaseFragment {

    private LinearLayout btnAgendarAtendimento;
    private LinearLayout btnMinhaAgenda;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, null);

        findViews(view);

        btnAgendarAtendimento.setOnClickListener(showAgendarAtendimento());

        btnMinhaAgenda.setOnClickListener(showMinhaAgenda());

        return view;
    }

    public void findViews(View view) {
        btnAgendarAtendimento = (LinearLayout) view.findViewById(R.id.fragment_menu_linear_agendar_atendimento);
        btnMinhaAgenda = (LinearLayout) view.findViewById(R.id.fragment_menu_linear_minha_agenda);
    }

    public View.OnClickListener showAgendarAtendimento() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(ControlerFragments.SelectedFragment.FUNCAO);
                ((MainActivity) getActivity()).animateTransitionFragments(ControlerFragments.SelectedFragment.FUNCAO);

            }
        };
    }

    public View.OnClickListener showMinhaAgenda() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(ControlerFragments.SelectedFragment.MINHA_AGENDA);
                ((MainActivity) getActivity()).animateTransitionFragments(ControlerFragments.SelectedFragment.FUNCAO);

            }
        };
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Script", "onPause()");
    }

    @Override
    public void onStop() {
        super.onPause();
        Log.i("Script", "onStop()");
    }

    @Override
    public void onResume() {
        super.onPause();
        Log.i("Script", "onResume()");
    }
}