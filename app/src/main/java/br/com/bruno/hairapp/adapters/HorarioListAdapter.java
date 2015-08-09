package br.com.bruno.hairapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.models.Horario;

/**
 * Created by Bruno on 16/02/2015.
 */
public class HorarioListAdapter extends BaseAdapter {

    private ArrayList<Horario> myListHorarios;
    private LayoutInflater myLayoutInflater;

    public HorarioListAdapter(Context context, ArrayList<Horario> arrayList) {
        myListHorarios = arrayList;
        myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myListHorarios.size();
    }

    @Override
    public Object getItem(int position) {
        return myListHorarios.get(position);
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
            holder.itemHorario = (TextView) view.findViewById(R.id.agendar_atendimento_item_list);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }


        Horario horario = myListHorarios.get(position);
        if (horario != null) {
            if (holder.itemHorario != null) {
                //set the item name on the TextView
                holder.itemHorario.setText(horario.getHora());
            }
        }
        return view;
    }

    private static class ViewHolder {

        protected TextView itemHorario;
    }
}
