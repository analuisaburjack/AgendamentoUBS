package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.models.Consult;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView hour;
        public TextView pacientName;
        public TextView pacientSus;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            hour = (TextView) itemView.findViewById(R.id.list_hour);
            pacientName = (TextView) itemView.findViewById(R.id.list_pacient);
            pacientSus = (TextView) itemView.findViewById(R.id.list_sus);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent;

                    intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    intent.putExtra("LIST", (Serializable) getConsultList());
                    context.startActivity(intent);
                }
            });

        }
    }

    private List<Consult> consultList;
    private String date;


    // Pass in the contact array into the constructor
    public RecyclerViewAdapter(List<Consult> consultList) {
        this.consultList = consultList;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View consultsView = inflater.inflate(R.layout.item_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(consultsView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Consult consult = consultList.get(position);

        // Set item views based on your views and data model
        TextView hourView = viewHolder.hour;
        hourView.setText(consult.getHour());

        TextView nameView = viewHolder.pacientName;
        nameView.setText(consult.getPacientName());

        TextView susView = viewHolder.pacientSus;
        susView.setText(consult.getPacientSus());
}

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return consultList.size();
    }

    public List<Consult> getConsultList() {
        return consultList;
    }
}