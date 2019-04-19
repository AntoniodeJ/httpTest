package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EchoPostHandler implements HttpHandler {

	public void handle(HttpExchange exchange) throws IOException {
		
		// parse request
        Map<String, Object> parameters = new HashMap<String, Object>();
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();
        System.out.println(query);
        ParseQuery.parseQuery(query, parameters);
        String recebido = (String) parameters.get("SensorsData");
        String[] sensorsData = recebido.split("<SD>");
        for (String sensorData : sensorsData) {
        	System.out.println(sensorData);
        }

        // send response
        String response = "";
        for (String key : parameters.keySet())
                 response += key + " = " + parameters.get(key) + "\n";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
	}

}
