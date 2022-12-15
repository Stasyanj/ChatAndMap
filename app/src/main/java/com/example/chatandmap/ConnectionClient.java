package com.example.chatandmap;

import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConnectionClient extends AppCompatActivity {
    private Socket clientSocket = null;
    private String mHost = null;
    private int mPort = 0;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static final String LOG_TAG = "SOCKET";

    public void Connect() {
    }
    public void Connect(final String host, final int port) {
        this.mHost = host;
        this.mPort = port;
    }
    public void openConnection() throws Exception {
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
        reader = new BufferedReader(new InputStreamReader(System.in));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        if (clientSocket == null || clientSocket.isClosed()){
            throw new Exception("Ошибка отправки данных." + "Сокет не создан или закрыт");
        }
        try {
            out.write(data);
            out.flush();
        } catch (IOException e){
            throw new Exception("Ошибка отправки данных: " + e.getMessage());
        }
    }
    public String receiveData() throws Exception{
        byte[] data = "".getBytes(StandardCharsets.UTF_8);
        String serverWord;
        BufferedReader in = null;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            if (clientSocket == null || clientSocket.isClosed()) {
                throw new Exception("Ошибка получения данных." + "Сокет не создан или закрыт");
            }
            try {
                 serverWord = in.readLine();
            } catch (IOException e) {
                throw new Exception("Ошибка отправки данных: " + e.getMessage());
            }
            return serverWord;
    }
    //приветики)) как жизнь?
    protected void finalize() throws Throwable{
        super.finalize();
        closeConnection();
    }
}
