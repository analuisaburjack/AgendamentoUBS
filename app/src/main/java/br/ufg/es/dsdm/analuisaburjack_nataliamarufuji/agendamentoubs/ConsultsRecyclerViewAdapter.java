package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.Models.Consult;

public class ConsultsRecyclerViewAdapter extends RecyclerView.Adapter<ConsultsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Consult> consultList;
    private Context context;

    public ConsultsRecyclerViewAdapter(ArrayList<Consult> cLst, Context ctx) {
        consultList = cLst;
        context = ctx;
    }

    @Override
    public ConsultsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);

        ConsultsRecyclerViewAdapter.ViewHolder viewHolder = new ConsultsRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ConsultsRecyclerViewAdapter.ViewHolder holder, int position) {
        Consult Consult = consultList.get(position);
        holder.consultTime.setText(Consult.getHour());
        holder.consultBookedPhrase.setText(Consult.getBookedPhrase());
        holder.consultPacient.setText(Consult.getPacient().getName());
    }

    @Override
    public int getItemCount() {
        return consultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView consultTime;
        public TextView consultBookedPhrase;
        public TextView consultPacient;

        public ViewHolder(View view) {
            super(view);
            consultTime = (TextView) view.findViewById(R.id.consult_time);
            consultBookedPhrase = (TextView) view.findViewById(R.id.consult_booked);
            consultPacient = (TextView) view.findViewById(R.id.consult_pacient);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "You clicked Consults at  "+ getAdapterPosition(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
