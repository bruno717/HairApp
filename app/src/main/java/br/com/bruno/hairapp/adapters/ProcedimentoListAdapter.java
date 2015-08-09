package br.com.bruno.hairapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.models.Procedimento;

/**
 * Created by Bruno on 16/02/2015.
 */
public class ProcedimentoListAdapter extends BaseAdapter{
    private ArrayList<Procedimento> myListProcedimentos;
    private LayoutInflater myLayoutInflater;

    public ProcedimentoListAdapter(Context context, ArrayList<Procedimento> arrayList) {
        myListProcedimentos = arrayList;
        myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myListProcedimentos.size();
    }

    @Override
    public Object getItem(int position) {
        return myListProcedimentos.get(position);
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
            holder.itemProcedimento = (TextView) view.findViewById(R.id.agendar_atendimento_item_list);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }


        Procedimento procedimento = myListProcedimentos.get(position);
        if (procedimento != null) {
            if (holder.itemProcedimento != null) {
                //set the item name on the TextView
                holder.itemProcedimento.setText(procedimento.getDescricao());
            }
        }
        return view;
    }

    private static class ViewHolder {

        protected TextView itemProcedimento;
    }
}