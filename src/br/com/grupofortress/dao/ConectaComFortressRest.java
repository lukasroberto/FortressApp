/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupofortress.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author informatica
 */
public class ConectaComFortressRest {

    public JSONObject atualizaVeiculos(String endereco) throws ClientProtocolException, IOException, JSONException {
        HttpClient client = new DefaultHttpClient();

        HttpGet request = new HttpGet(endereco);
        //request.setHeader("ApiKey", apiKey.toString());
        //request.setHeader("SecretKey", secretKey.toString());

        HttpResponse response;
        JSONObject dadosFulltrack = null;

            response = client.execute(request);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            String json = "";

            while ((line = rd.readLine()) != null) {
                json = json + line;
            }

            dadosFulltrack = new JSONObject(json);


        return dadosFulltrack;

    }
}
