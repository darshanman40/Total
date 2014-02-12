package com.manchekar.darshan.total;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HttpExample extends Activity{

	HttpClient client;
	TextView httpStuff;
	final static String URL ="https://api.twitter.com/1.1/statuses/show.json";
	JSONObject json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpex);
		
		client = new DefaultHttpClient();
		httpStuff = (TextView)findViewById(R.id.tvHttp);
		
		new Read().execute("text");
		
		
		/*
		GetMethodEx test = new GetMethodEx();
		String returned;
		try {
			returned = test.getInternetData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returned = null;
			e.printStackTrace();
		}
		
		if(returned!=null)
			httpStuff.setText(returned);
		else
			httpStuff.setText("Not Working man!");
	*/
	}
	
	public JSONObject lastTweet(String username) throws ClientProtocolException, IOException, JSONException{
		StringBuilder url = new StringBuilder(URL);
		url.append(username);
		
		HttpGet get = new HttpGet(url.toString());
		HttpResponse r = client.execute(get);
		int status = r.getStatusLine().getStatusCode();
		if(status ==200){
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
			JSONArray timeline = new JSONArray(data);
			JSONObject last = timeline.getJSONObject(0);
			return last;
		}else{
		
			Toast.makeText(HttpExample.this, "error", Toast.LENGTH_SHORT);
			return null;
		}
	
	}
	public class Read extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try {
				json=lastTweet("darshan1991@rediffmail.com");
				return json.getString(arg0[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			httpStuff.setText(result);
			//super.onPostExecute(result);
		}
		
	}
	
	
}
