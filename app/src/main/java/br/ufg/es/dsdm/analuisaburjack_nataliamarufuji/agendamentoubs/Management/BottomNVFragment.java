package br.ufg.es.dsdm.analuisaburjack_nataliamarufuji.agendamentoubs.Management;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class BottomNVFragment extends Fragment {
    public static BottomNVFragment newInstance(String itemName) {
        BottomNVFragment fragment = new BottomNVFragment();

        Bundle args = new Bundle();
        args.putString("itemName", itemName);
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        if(getArguments() != null) {
            text.setText("Selected bottom navigation item " +getArguments().getString("itemName", ""));
        }else{
            text.setText( "Default bottom navigation item");
        }


        scroller.addView(text);
        return scroller;
    }
}