package br.com.grupofortress;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.grupofortress.model.Evento;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Button b = (Button) findViewById(R.id.button1);
		b.setClickable(false);
		new buscaJson().execute();
	}

	private class buscaJson extends AsyncTask<Void, Void, String> {

		protected String getASCIIContentFromEntity(HttpEntity entity)
				throws IllegalStateException, IOException {
			InputStream in = entity.getContent();
			StringBuffer out = new StringBuffer();
			int n = 1;
			while (n > 0) {
				byte[] b = new byte[4096];
				n = in.read(b);
				if (n > 0)
					out.append(new String(b, 0, n));
			}

			return out.toString();
		}

		@Override
		protected String doInBackground(Void... params) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet(
					"http://192.168.0.196:8080/WebServiceFortress_Leitor/listar/evento/5050/5");
			String text = null;
			try {
				HttpResponse response = httpClient.execute(httpGet,
						localContext);
				HttpEntity entity = response.getEntity();
				text = getASCIIContentFromEntity(entity);
			} catch (Exception e) {
				return e.getLocalizedMessage();
			}
			return text;
		}

		protected void onPostExecute(String results) {
			ArrayList<Evento> eventos = new ArrayList<Evento>();
			JSONObject eventosFortress = null;
			
			try {
				eventosFortress = new JSONObject(results);
				JSONArray resultados = eventosFortress.getJSONArray("eventos");

				for (int i = 0; i < resultados.length(); i++) {

					String dadosString = resultados.get(i).toString();
					JSONObject dadosJson = new JSONObject(dadosString);

					Evento evento = new Evento();

					evento.setEve_hora(dadosJson.get("eve_hora").toString());
					evento.setEve_data(dadosJson.get("eve_data").toString());
					evento.setEve_conta_grupo_receptor(dadosJson.get("eve_conta_grupo_receptor").toString());
					evento.setEve_codigo_cliente(dadosJson.get("eve_codigo_cliente").toString());
					evento.setEve_protocolo(dadosJson.get("eve_protocolo").toString());
					evento.setEve_codigo_evento(dadosJson.get("eve_codigo_evento").toString());
					evento.setEve_particao(dadosJson.get("eve_particao").toString());
					evento.setEve_usuario_zona(dadosJson.get("eve_usuario_zona").toString());
					evento.setCli_nome(dadosJson.get("cli_nome").toString());
					evento.setEvento_descricao(dadosJson.get("evento_descricao").toString());

					eventos.add(evento);

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (results != null) {	
				EditText et = (EditText) findViewById(R.id.editText1);


			}
			Toast.makeText(getApplicationContext(), "teste", Toast.LENGTH_LONG)
					.show();
		}

	}
	public void btTela1(View v) {
	    startActivity(new Intent(getBaseContext(), RecultadoActivity.class));
	    }


}