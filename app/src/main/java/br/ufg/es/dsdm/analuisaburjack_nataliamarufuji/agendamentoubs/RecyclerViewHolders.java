package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView mHour;
    public TextView mPacientName;
    public TextView mBookedPhrase;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mHour = (TextView)itemView.findViewById(R.id.list_hour);
        mPacientName = (TextView)itemView.findViewById(R.id.list_pacient);
        mBookedPhrase = (TextView)itemView.findViewById(R.id.list_bookedPhrase);
    }
    @Override
    public void onClick(View view) {
    }
}
