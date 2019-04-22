package cliente;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainClienteOkHttp {
	
	public static void main (String[] args) {
		OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("Nome", "Antonio")
                .addFormDataPart("Tipo", "Treinamento")
                .addFormDataPart("Atividade", "Antonio")
                .addFormDataPart("SensorsData", "TYPE_ACCELEROMETER;0.12;0.15;0.0005<SD>TYPE_GYROSCOPE;0.5;-1.2;-0.000232<SD>TYPE_ACCELEROMETER;0.1;-1.3;-0.002<SD>TYPE_GYROSCOPE;0.2;-2;-0.1")
                .build();

        Request request = new Request.Builder()
                .url("https://activity-inferencer.herokuapp.com/echoPost")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
