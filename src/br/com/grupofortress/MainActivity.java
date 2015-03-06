package br.com.grupofortress;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import br.com.grupofortress.controller.AdapterListView;
import br.com.grupofortress.controller.HttpManager;
import br.com.grupofortress.controller.populaJson;
import br.com.grupofortress.model.Evento;

public class MainActivity extends Activity implements OnClickListener {
	private Button b;
	private TextView codCliente;
	private ProgressBar pb;
	private ArrayList<Evento> eventoList;

	private ListView listView;
	private AdapterListView adapterListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pb = (ProgressBar) findViewById(R.id.progressBar);
		pb.setVisibility(View.INVISIBLE);

		findViewById(R.id.bt_busca_eventos).setOnClickListener(this);
		// Pega a referencia do ListView
		listView = (ListView) findViewById(R.id.list);
		// Define o Listener quando alguem clicar no item.
        //listView.setOnItemClickListener(this);


	}

	private void createListView() {
		adapterListView = new AdapterListView(this, eventoList);
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		Evento item = adapterListView.getItem(arg2);
		Toast.makeText(this, "Você Clicou em: " + item.getEvento_descricao(),Toast.LENGTH_LONG).show();
	}

	@Override
	public void onClick(View arg0) {
		b = (Button) findViewById(R.id.bt_busca_eventos);
		//b.setClickable(false);
		
		codCliente = (TextView) findViewById(R.id.et_cliente);
		String codCli = codCliente.getText().toString();

		if (isOnline()) {
			if (!codCli.equals("")) {
				String url = "http://200.207.41.249:8080/WebServiceFortress_Leitor/listar/evento/"+ codCli + "/10";
				new MyTask().execute(url);
			} else {
				Toast.makeText(this, "Informe o c�digo do cliente.",Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(this, "Problema na conex�o", Toast.LENGTH_LONG).show();
		}
	}

	protected void imprime() {
		pb.setVisibility(View.VISIBLE);

		if (eventoList != null) {
			if(eventoList.get(0).getStatus() != null && eventoList.get(0).getStatus().equals("clienteFalse")){
				Toast.makeText(this, "Cliente n�o Cadastrado, Informar Depto. Informatica.", Toast.LENGTH_LONG).show();
			}else{
				createListView();
			}
		}

	}

	protected boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		
		if (netinfo != null && netinfo.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
	}

	private class MyTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			pb.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... params) {

			String content = HttpManager.getData(params[0]);
			return content;
		}

		@Override
		protected void onPostExecute(String results) {
			eventoList = populaJson.parseFeed(results);
			
			imprime();
			
			pb.setVisibility(View.INVISIBLE);
		
		}

	}

}