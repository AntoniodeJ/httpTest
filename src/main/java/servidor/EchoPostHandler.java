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
        ParseQuery.parseQuery(query, parameters);
        System.out.println("Recebeu post!");

        // send response
        String response = "";
        for (String key : parameters.keySet())
                 response += key + " = " + parameters.get(key) + "\n";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        PrintStream saida = new PrintStream(os);
        //os.write(response.toString().getBytes());
        saida.println(response.toString());
        os.close();
        saida.close();
	}

}
