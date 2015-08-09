package br.com.bruno.hairapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import br.com.bruno.hairapp.models.AgendarHorarioSingleton;
import br.com.bruno.hairapp.utils.ControlerFragments;


public class MainActivity extends Activity {

    private FrameLayout frameMenu;
    private FrameLayout frameConteudo;
    private FragmentManager fragmentManager;
    private ControlerFragments controlerFragments;
    private AgendarHorarioSingleton agendarSing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeActionBar();
        findViews();
        agendarSing = AgendarHorarioSingleton.getInstance();

        //Altera posição do frame contudo para ficar em baixo do frame menu quando app inicia
        frameMenu.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                frameConteudo.setY(frameMenu.getBottom());
                frameMenu.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        fragmentManager = getFragmentManager();
        controlerFragments = new ControlerFragments(fragmentManager);
        controlerFragments.startFragment();
    }

    public void findViews() {
        frameMenu = (FrameLayout) findViewById(R.id.activity_main_frame_menu);
        frameConteudo = (FrameLayout) findViewById(R.id.activity_main_frame_conteudo);
    }

    public void changeActionBar() {

        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setCustomView(R.layout.action_bar);
    }

    public void animateTransitionFragments(int fragment) {
        switch (fragment) {
            case ControlerFragments.SelectedFragment.MENU:

                frameMenu.animate().setDuration(300).translationY(0);
                frameConteudo.animate().setDuration(300).translationY(frameConteudo.getHeight());

                fragmentManager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;

            case ControlerFragments.SelectedFragment.FUNCAO:
                frameMenu.animate().setDuration(300).translationY(-frameMenu.getHeight());
                frameConteudo.animate().setDuration(300).translationY(0);


                break;
        }

    }

    @Override
    public void onBackPressed() {
        switch (agendarSing.getPositionNavegation()) {

            case ControlerFragments.SelectedFragment.MENU:
                super.onBackPressed();
                break;

            case ControlerFragments.SelectedFragment.FUNCAO:
                animateTransitionFragments(ControlerFragments.SelectedFragment.MENU);
                agendarSing.setPositionNavegation(ControlerFragments.SelectedFragment.MENU);
                break;

            case ControlerFragments.SelectedFragment.FUNCIONARIO:
                agendarSing.setPositionNavegation(ControlerFragments.SelectedFragment.FUNCAO);
                super.onBackPressed();
                break;

            case ControlerFragments.SelectedFragment.DATA_HORARIO:
                agendarSing.setPositionNavegation(ControlerFragments.SelectedFragment.FUNCIONARIO);
                super.onBackPressed();
                break;

            case ControlerFragments.SelectedFragment.PROCEDIMENTO:
                agendarSing.setPositionNavegation(ControlerFragments.SelectedFragment.DATA_HORARIO);
                super.onBackPressed();
                break;

            case ControlerFragments.SelectedFragment.CONFIRMAR_ATENDIMENTO:
                agendarSing.setPositionNavegation(ControlerFragments.SelectedFragment.PROCEDIMENTO);
                super.onBackPressed();
                break;

            case ControlerFragments.SelectedFragment.MINHA_AGENDA:
                animateTransitionFragments(ControlerFragments.SelectedFragment.MENU);
                agendarSing.setPositionNavegation(ControlerFragments.SelectedFragment.MENU);
                break;
        }

    }
}
