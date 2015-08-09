package br.com.bruno.hairapp.utils;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.fragments.ConfirmarAtendimentoFragment;
import br.com.bruno.hairapp.fragments.DataHorarioFragment;
import br.com.bruno.hairapp.fragments.FuncaoFragment;
import br.com.bruno.hairapp.fragments.FuncionarioFragment;
import br.com.bruno.hairapp.fragments.MenuFragment;
import br.com.bruno.hairapp.fragments.MinhaAgendaFragment;
import br.com.bruno.hairapp.fragments.ProcedimentoFragment;
import br.com.bruno.hairapp.models.AgendarHorarioSingleton;

/**
 * Created by bruno on 14/02/2015.
 */
public class ControlerFragments {

    private static FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FuncaoFragment funcaoFragment;
    private FuncionarioFragment funcionarioFragment;
    private DataHorarioFragment dataFragment;
    private ProcedimentoFragment procedimentoFragment;
    private ConfirmarAtendimentoFragment confirmarAtendimentoFragment;
    private MinhaAgendaFragment minhaAgendaFragment;
    private AgendarHorarioSingleton agendarHorarioSingleton;

    public ControlerFragments(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        agendarHorarioSingleton = AgendarHorarioSingleton.getInstance();
    }

    public ControlerFragments() {
        agendarHorarioSingleton = AgendarHorarioSingleton.getInstance();
    }

    public void startFragment() {
        MenuFragment menuFragment = new MenuFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_frame_menu, menuFragment);
        fragmentTransaction.commit();

        agendarHorarioSingleton.setPositionNavegation(SelectedFragment.MENU);
    }

    public void changeFragment(int count) {
        switch (count) {
            case SelectedFragment.FUNCAO:
                funcaoFragment = new FuncaoFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_frame_conteudo, funcaoFragment);
                fragmentTransaction.commit();

                agendarHorarioSingleton.setPositionNavegation(SelectedFragment.FUNCAO);
                break;

            case SelectedFragment.FUNCIONARIO:
                funcionarioFragment = new FuncionarioFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_frame_conteudo, funcionarioFragment);
                fragmentTransaction.addToBackStack(String.valueOf(SelectedFragment.FUNCIONARIO));
                fragmentTransaction.commit();

                agendarHorarioSingleton.setPositionNavegation(SelectedFragment.FUNCIONARIO);
                break;

            case SelectedFragment.DATA_HORARIO:
                dataFragment = new DataHorarioFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_frame_conteudo, dataFragment);
                fragmentTransaction.addToBackStack(String.valueOf(SelectedFragment.DATA_HORARIO));
                fragmentTransaction.commit();

                agendarHorarioSingleton.setPositionNavegation(SelectedFragment.DATA_HORARIO);
                break;

            case SelectedFragment.PROCEDIMENTO:
                procedimentoFragment = new ProcedimentoFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_frame_conteudo, procedimentoFragment);
                fragmentTransaction.addToBackStack(String.valueOf(SelectedFragment.PROCEDIMENTO));
                fragmentTransaction.commit();

                agendarHorarioSingleton.setPositionNavegation(SelectedFragment.PROCEDIMENTO);
                break;

            case SelectedFragment.CONFIRMAR_ATENDIMENTO:
                confirmarAtendimentoFragment = new ConfirmarAtendimentoFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_frame_conteudo, confirmarAtendimentoFragment);
                fragmentTransaction.addToBackStack(String.valueOf(SelectedFragment.CONFIRMAR_ATENDIMENTO));
                fragmentTransaction.commit();

                agendarHorarioSingleton.setPositionNavegation(SelectedFragment.CONFIRMAR_ATENDIMENTO);
                break;

            case SelectedFragment.MINHA_AGENDA:
                minhaAgendaFragment = new MinhaAgendaFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_frame_conteudo, minhaAgendaFragment);
                fragmentTransaction.commit();

                agendarHorarioSingleton.setPositionNavegation(SelectedFragment.MINHA_AGENDA);
                break;

            default:
                Log.i("Script", "Nenhum fragmento selecionado");
                break;
        }
    }

    public static void testeVoltar(){
        fragmentManager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Log.i("Script","testeVoltar()");
    }

    public class SelectedFragment {
        public final static int MENU = 0;
        public final static int FUNCAO = 1;
        public final static int FUNCIONARIO = 2;
        public final static int DATA_HORARIO = 3;
        public final static int PROCEDIMENTO = 4;
        public final static int CONFIRMAR_ATENDIMENTO = 5;
        public final static int MINHA_AGENDA = 6;
    }

}