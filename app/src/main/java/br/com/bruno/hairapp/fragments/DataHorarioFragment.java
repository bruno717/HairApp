package br.com.bruno.hairapp.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.bruno.hairapp.R;
import br.com.bruno.hairapp.adapters.HorarioListAdapter;
import br.com.bruno.hairapp.models.AgendarHorarioSingleton;
import br.com.bruno.hairapp.models.Horario;
import br.com.bruno.hairapp.tasks.HorariosDisponiveisTask;
import br.com.bruno.hairapp.utils.ControlerFragments;

/**
 * Created by Bruno on 16/02/2015.
 */
public class DataHorarioFragment extends BaseFragment {

    private Button btnData;
    private ListView listView;
    private TextView txtHorario;
    private Calendar calendar = Calendar.getInstance();
    private DateFormat dateFormat = DateFormat.getDateInstance();
    private DatePickerDialog datePickerDialog;
    private Date currentDate;

    private AgendarHorarioSingleton agendarSing;
    private HorarioListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data_horario, null);

        findViews(view);
        agendarSing = AgendarHorarioSingleton.getInstance();

        currentDate = calendar.getTime();
        listView.setEnabled(false);
        listView.animate().alpha(0f).setDuration(TIME_ANIMATION);
        txtHorario.setAlpha(0f);

        btnData.setOnClickListener(setDate());
        listView.setOnItemClickListener(horarioSelected());

        return view;
    }

    public void findViews(View view) {
        btnData = (Button) view.findViewById(R.id.fragment_data_horario_button_data);
        listView = (ListView) view.findViewById(R.id.fragment_data_horario_list);
        txtHorario = (TextView) view.findViewById(R.id.fragment_data_horario_text_horario);
    }

    //Seta a data no botão para visualização do usuário
    public void updateDate() {
        btnData.setText(dateFormat.format(calendar.getTime()));
        showHorarios();
        listView.setEnabled(true);
        listView.animate().alpha(1f).setDuration(TIME_ANIMATION);
        txtHorario.animate().alpha(1f).setDuration(TIME_ANIMATION);
        agendarSing.setData(dateFormat.format(calendar.getTime()));

    }

    //Chama caixa de dialogo para usuário escolher uma data
    public View.OnClickListener setDate() {
        return (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(getActivity(), dialog, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                Log.i("Script", "setDate()");
            }
        });
    }

    //Monta calendário para usuário
    private DatePickerDialog.OnDateSetListener dialog = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            if (currentDate.equals(calendar.getTime()) || currentDate.before(calendar.getTime())) {
                updateDate();

            } else {
                btnData.setText("__/__/____");
                Toast.makeText(getActivity().getBaseContext(),
                        "Você não pode fazer uma agenda para uma data anterior a " +
                                dateFormat.format(currentDate.getTime()), Toast.LENGTH_LONG).show();

                listView.setEnabled(false);
                listView.animate().alpha(0f).setDuration(TIME_ANIMATION);
                txtHorario.animate().alpha(0f).setDuration(TIME_ANIMATION);
            }

            Log.i("Script", "onDateSet()");
        }
    };

    public void showHorarios() {
        HorariosDisponiveisTask horariosDisponiveisTask = new HorariosDisponiveisTask(btnData.getText().toString(),
                agendarSing.getFuncionarioList().get(agendarSing.getPositionFuncionario()).getId(), 1,
                new HorariosDisponiveisTask.OnReturnListHorarios() {
                    @Override
                    public void onCompletion(ArrayList<Horario> horarios) {
                        agendarSing.setHorarioList(horarios);
                        adapter = new HorarioListAdapter(getActivity().getBaseContext(), horarios);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onError() {

                    }
                });
        horariosDisponiveisTask.execute();
    }

    public AdapterView.OnItemClickListener horarioSelected() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                agendarSing.setPositionHorario(position);
                showFragment(ControlerFragments.SelectedFragment.PROCEDIMENTO);
                Log.i("Horário selecionado", agendarSing.getHorarioList().get(position).getHora());
            }
        };
    }
}
