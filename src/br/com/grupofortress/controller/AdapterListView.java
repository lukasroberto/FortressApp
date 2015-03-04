package br.com.grupofortress.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.grupofortress.R;
import br.com.grupofortress.model.Evento;


public class AdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Evento> vendas = null;
	private ArrayList<Evento> arraylist;


    public AdapterListView(Context context, ArrayList<Evento> itens) {
        //Itens que preencheram o listview
        this.vendas = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<Evento>();
		this.arraylist.addAll(itens);
    }

    public int getCount() {
        return vendas.size();
    }


    public Evento getItem(int position) {
        return vendas.get(position);
    }


    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
    	//Vendas item = vendas.get(position);
        view = mInflater.inflate(R.layout.activity_main, null);

        //((TextView) view.findViewById(R.id.tv_evento)).setText(vendas.get(position).getEvento_descricao().toString());
        //((TextView) view.findViewById(R.id.tv_data)).setText(vendas.get(position).getEve_data());

        return view;
    }
}
