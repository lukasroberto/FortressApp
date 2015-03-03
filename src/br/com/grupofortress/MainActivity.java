package br.com.grupofortress;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import br.com.grupofortress.controller.HttpManager;
import br.com.grupofortress.controller.populaJson;
import br.com.grupofortress.model.Evento;

public class MainActivity extends Activity implements OnClickListener {
	TextView textView;
	ProgressBar pb;
	List<Evento> eventoList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textView2);
		pb = (ProgressBar) findViewById(R.id.progressBar);
		pb.setVisibility(View.INVISIBLE);

		findViewById(R.id.button1).setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		Button b = (Button) findViewById(R.id.button1);
		// b.setClickable(false);
		if (isOnline()) {
			new MyTask()
					.execute("http://192.168.0.196:8080/WebServiceFortress_Leitor/listar/evento/5050/5");
		} else {
			Toast.makeText(this, "Problema na conexão", Toast.LENGTH_LONG)
					.show();
		}
	}

	protected void imprime() {
		pb.setVisibility(View.VISIBLE);

		if (eventoList != null) {
			for (Evento evento : eventoList) {
				textView.append(evento.getCli_nome());
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
