package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;
import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Day;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders>{
    private List<Consult> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, Day day) {
        this.itemList = day.getConsults();
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.mHour.setText(itemList.get(position).getHour());
        if(itemList.get(position).isBooked()){
            holder.mPacientName.setText(itemList.get(position).getPacient().getName());
            holder.mBookedPhrase.setText("Paciente");
        }else if(!itemList.get(position).isBooked()){
            holder.mPacientName.setText("Horário Livre");
            holder.mBookedPhrase.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
