package com.tasteit.app.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class HttpClientHelper {
	// Tag para identificar en el Log
	private static final String TAG = "HttpClientHelper";

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// Contructor
	public HttpClientHelper() {

	}

	public static JSONObject getJSONFromUrl(String OperationName) {
		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(
					"http://www.atipujate.somee.com/WCFAtipujate/Atipujate.svc/"
							+ OperationName);
			HttpResponse httpResponse = httpClient.execute(request);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		// return JSON String
		return jObj;
	}

	// Obtiene un arreglo de JSON's
	public static JSONArray GET(String OperationName) throws Exception {
		BufferedReader reader = null;
		StringBuilder sb = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(
					"http://www.atipujate.somee.com/WCFAtipujate/Atipujate.svc/"
							+ OperationName);
			HttpResponse response = httpClient.execute(request);
			HttpEntity responseEntity = response.getEntity();

			InputStream stream = responseEntity.getContent();

			reader = new BufferedReader(new InputStreamReader(stream));
			sb = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}
		} catch (Exception ex) {
			// Procesamos el error
			Log.e(TAG, "Error al crear la actividad. Error: " + ex.getMessage());
			throw ex;
		} finally {
			reader.close();
		}
		return new JSONArray(sb.toString());

	}

}
