package br.com.bruno.hairapp.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;

import br.com.bruno.hairapp.MainActivity;
import br.com.bruno.hairapp.utils.ControlerFragments;

/**
 * Created by Bruno on 14/02/2015.
 */
public class BaseFragment extends Fragment {

    private ControlerFragments controlerFragments;
    protected static final int TIME_ANIMATION = 500;
    private ProgressDialog progressDialog;

    public void showFragment(int fragment) {
        controlerFragments = new ControlerFragments();
        controlerFragments.changeFragment(fragment);
    }

    public void startLoading(Context context, String msg) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    public void stopLoading() {
        progressDialog.dismiss();

    }
}
