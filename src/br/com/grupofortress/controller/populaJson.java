package br.com.grupofortress.controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.grupofortress.model.Evento;

public class populaJson {
	
	public static ArrayList<Evento> parseFeed(String content){
		try {
			ArrayList<Evento> eventosList = new ArrayList<Evento>();
			
			JSONObject ar = new JSONObject(content);
            JSONArray resultados = ar.getJSONArray("eventos");

			for (int i = 0; i < resultados.length(); i++) {
                String dadosString = resultados.get(i).toString();
                JSONObject obj = new JSONObject(dadosString);
				
				Evento evento = new Evento();
				String status = obj.get("status").toString();
				
				if(status.equals("clienteFalse")){
					evento.setStatus(obj.get("status").toString());
					eventosList.add(evento);
					return eventosList;
				}else{
				
				evento.setEve_data(obj.get("eve_data").toString());
			    evento.setEve_conta_grupo_receptor(obj.get("eve_conta_grupo_receptor").toString());
			    evento.setEve_codigo_cliente(obj.get("eve_codigo_cliente").toString());
			    evento.setEve_protocolo(obj.get("eve_protocolo").toString());
			    evento.setEve_codigo_evento(obj.get("eve_codigo_evento").toString());
			    evento.setEve_particao(obj.get("eve_particao").toString());
			    evento.setEve_usuario_zona(obj.get("eve_usuario_zona").toString());
			    evento.setCli_nome(obj.get("cli_nome").toString());
			    evento.setEvento_descricao(obj.get("evento_descricao").toString());
			    eventosList.add(evento);
			}
				}
			return eventosList;
		} catch (JSONException e) {
			e.printStackTrace();
		}return null;
	}

}
