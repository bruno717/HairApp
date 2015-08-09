package br.com.bruno.hairapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.models.Funcao;

/**
 * Created by Bruno on 15/02/2015.
 */
public class FuncaoListAdapter extends BaseAdapter {

    private ArrayList<Funcao> myListAtividades;
    private LayoutInflater myLayoutInflater;

    public FuncaoListAdapter(Context context, ArrayList<Funcao> arrayList) {
        myListAtividades = arrayList;
        myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myListAtividades.size();
    }

    @Override
    public Object getItem(int position) {
        return myListAtividades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();

            view = myLayoutInflater.inflate(R.layout.agendar_atendimento_list, null);
            holder.itemAtividade = (TextView) view.findViewById(R.id.agendar_atendimento_item_list);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }


        Funcao funcao = myListAtividades.get(position);
        if (funcao != null) {
            if (holder.itemAtividade != null) {
                //set the item name on the TextView
                holder.itemAtividade.setText(funcao.getNome());
            }
        }
        return view;
    }

    private static class ViewHolder {

        protected TextView itemAtividade;
    }
}
