package br.com.grupofortress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.grupofortress.dao.ConectaComFortressRest;
import br.com.grupofortress.model.Evento;

public class Principal {
	private static Principal instancia;
	private static ArrayList<Evento> eventos = new ArrayList<Evento>();


	public static Principal getInstancia() {
		if (instancia == null) {
			instancia = new Principal();
		}
		return instancia;
	}
	public ArrayList<Evento> listarEventos() {
		return eventos;
	}
	
	public void addEvento(Evento evento) {
		eventos.add(evento);
	}

}
