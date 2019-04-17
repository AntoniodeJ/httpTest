package cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpSend{

    private URL url;
    private HttpsURLConnection urlConnection;

    public HttpSend(String mensagem){

        try {
            url = new URL("https://http-test-antonio.herokuapp.com/");
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            out.write(mensagem.getBytes());
            
            //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader entrada = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            
            in.read();
            entrada.readLine();
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

    }
}
