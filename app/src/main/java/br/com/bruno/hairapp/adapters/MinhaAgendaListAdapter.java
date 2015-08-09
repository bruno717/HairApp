package br.com.bruno.hairapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.models.AgendaCliente;
import br.com.bruno.hairapp.models.Reserva;

/**
 * Created by Bruno on 16/02/2015.
 */
public class MinhaAgendaListAdapter extends BaseAdapter {

    private ArrayList<AgendaCliente> myListReservas;
    private LayoutInflater myLayoutInflater;

    public MinhaAgendaListAdapter(Context context, ArrayList<AgendaCliente> arrayList) {
        myListReservas = arrayList;
        myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setMyListReservas(ArrayList<AgendaCliente> myListReservas){
        this.myListReservas = myListReservas;
    }

    @Override
    public int getCount() {
        return myListReservas.size();
    }

    @Override
    public Object getItem(int position) {
        return myListReservas.get(position);
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

            view = myLayoutInflater.inflate(R.layout.minha_agenda_list, null);
            holder.itemProfissional = (TextView) view.findViewById(R.id.fragment_minha_agenda_text_profissional);
            holder.itemData = (TextView) view.findViewById(R.id.fragment_minha_agenda_text_data);
            holder.itemHorario = (TextView) view.findViewById(R.id.fragment_minha_agenda_text_horario);
            holder.itemProcedimento = (TextView) view.findViewById(R.id.fragment_minha_agenda_text_procedimento);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }


        AgendaCliente agendaCliente = myListReservas.get(position);
        if (agendaCliente != null) {
            if (holder.itemProfissional != null) {
                //set the item name on the TextView
                holder.itemProfissional.setText(agendaCliente.getProfissional());
                holder.itemData.setText(agendaCliente.getData());
                holder.itemHorario.setText(agendaCliente.getHora());
                holder.itemProcedimento.setText(agendaCliente.getProcedimento());
            }
        }
        return view;
    }

    private static class ViewHolder {

        protected TextView itemProfissional;
        protected TextView itemData;
        protected TextView itemHorario;
        protected TextView itemProcedimento;
    }
}


