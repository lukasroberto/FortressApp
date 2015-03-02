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
	public ArrayList<Evento> listarEventos() throws ClientProtocolException, IOException {
		teste();
		return eventos;
	}
	
	public void addEvento(Evento evento) {
		eventos.add(evento);
	}


	public void teste() throws ClientProtocolException, IOException {

		try {
			JSONObject dadosFulltrack = null;
			dadosFulltrack = new ConectaComFortressRest()
					.atualizaVeiculos("http://192.168.0.196:8080/WebServiceFortress_Leitor/listar/evento/5050/50");
			ArrayList<Evento> eventos = new ArrayList<Evento>();

			JSONArray resultados = dadosFulltrack.getJSONArray("eventos");

			for (int i = 0; i < resultados.length(); i++) {

				String dadosString = resultados.get(i).toString();
				JSONObject dadosJson = new JSONObject(dadosString);

				Evento evento = new Evento();

				evento.setEve_hora(dadosJson.get("eve_hora").toString());
				evento.setEve_data(dadosJson.get("eve_data").toString());
				evento.setEve_conta_grupo_receptor(dadosJson.get(
						"eve_conta_grupo_receptor").toString());
				evento.setEve_codigo_cliente(dadosJson
						.get("eve_codigo_cliente").toString());
				evento.setEve_protocolo(dadosJson.get("eve_protocolo")
						.toString());
				evento.setEve_codigo_evento(dadosJson.get("eve_codigo_evento")
						.toString());
				evento.setEve_particao(dadosJson.get("eve_particao").toString());
				evento.setEve_usuario_zona(dadosJson.get("eve_usuario_zona")
						.toString());
				evento.setCli_nome(dadosJson.get("cli_nome").toString());
				evento.setEvento_descricao(dadosJson.get("evento_descricao")
						.toString());

				this.addEvento(evento);
			}

		} catch (JSONException ex) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

}
