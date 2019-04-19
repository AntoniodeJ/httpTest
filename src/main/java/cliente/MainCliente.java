package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class MainCliente {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("https://http-test-antonio.herokuapp.com/echoPost");
		//HttpPost httppost = new HttpPost("http://localhost:9000/echoPost");

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(4);
		params.add(new BasicNameValuePair("Nome", "Antonio"));
		params.add(new BasicNameValuePair("Tipo", "Treinamento"));
		params.add(new BasicNameValuePair("Atividade", "Andando"));
		params.add(new BasicNameValuePair("SensorsData", "TYPE_ACCELEROMETER;0.12;0.15;0.0005<SD>TYPE_GYROSCOPE;0.5;-2.3;-0.000232<SD>TYPE_ACCELEROMETER;0.1;-1.3;-0.002"));
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		//Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    try (InputStream instream = entity.getContent()) {
		    	BufferedReader entrada = new BufferedReader(new InputStreamReader(instream));
		    	String recebido;
		    	while((recebido = entrada.readLine()) != null) {
		    		System.out.println(recebido);
		    	}
		    }
		}

	}

}
