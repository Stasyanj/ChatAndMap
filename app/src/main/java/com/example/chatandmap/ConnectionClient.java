package com.example.chatandmap;

import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConnectionClient extends AppCompatActivity {
    private Socket clientSocket = null;
    private String mHost = null;
    private int mPort = 0;

    public static final String LOG_TAG = "SOCKET";

    public void Connect() {
    }
    public void Connect(final String host, final int port) {
        this.mHost = host;
        this.mPort = port;
    }
    public void openConntection() throws Exception {
        closeConnection();
        try {
            clientSocket = new Socket(mHost, mPort);
        } catch (IOException e) {
            throw new Exception("Невозможно создать сокет: " + e.getMessage());
        }
    }
    public void closeConnection() {
        if (clientSocket != null && !clientSocket.isClosed()) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Ошибка при закрытии сокета: " + e.getMessage());
            } finally {
                clientSocket = null;
            }
        }
        clientSocket = null;
    }
    public void sendData(String data) throws Exception{
        int strlen = 0;
        if (clientSocket == null || clientSocket.isClosed()){
            throw new Exception("Ошибка отправки данных." + "Сокет не создан или закрыт");
        }
        try {
            strlen = data.length();
            clientSocket.getOutputStream().write(data.getBytes(StandardCharsets.US_ASCII), 0,strlen);
            clientSocket.getOutputStream().flush();
        } catch (IOException e){
            throw new Exception("Ошибка отправки данных: " + e.getMessage());
        }
    }
    public void receiveData() throws Exception{
        byte[] data = "".getBytes(StandardCharsets.UTF_8);
            if (clientSocket == null || clientSocket.isClosed()) {
                throw new Exception("Ошибка получения данных." + "Сокет не создан или закрыт");
            }
            try {
                clientSocket.getInputStream().read(data);
            } catch (IOException e) {
                throw new Exception("Ошибка отправки данных: " + e.getMessage());
            }
            TextView txt_View = (TextView) findViewById(R.id.textView);
            txt_View.append(data.toString());
    }
    protected void finalize() throws Throwable{
        super.finalize();
        closeConnection();
    }
}
