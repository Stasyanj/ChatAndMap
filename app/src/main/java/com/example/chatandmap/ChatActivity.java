package com.example.chatandmap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {
    private ImageButton connect_button = null;
    private ImageButton send_button = null;
    private EditText Edit_text = null;
    private ConnectionClient mConnect = null;
    private String HOST = "";
    private int PORT = 0;
    private String LOG_TAG = "SOCKET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
    }
    protected void onStart() {
        super.onStart();
        ImageButton send_button = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton connect_button = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton exit_chat_button = (ImageButton) findViewById(R.id.imageButton2);
        TextView txt_view = (TextView) findViewById(R.id.Reveive_Text);
        EditText Edit_text = (EditText) findViewById(R.id.editText);
        Intent myIntent = new Intent(ChatActivity.this, MainActivity.class);
        connect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //создание диалогового окна
                LayoutInflater li = LayoutInflater.from(ChatActivity.this);
                View promptView = li.inflate(R.layout.connect_dialog, null);
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(ChatActivity.this);
                mDialogBuilder.setView(promptView);
                final EditText userInput = (EditText) promptView.findViewById(R.id.input_text);
                final EditText userInput2 = (EditText) promptView.findViewById(R.id.input_text2);
                mDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                 HOST = userInput.getText().toString();
                                 PORT = Integer.parseInt(String.valueOf(userInput2.getText()));
                                onOpenClick();
                            }
                        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = mDialogBuilder.create();
                alertDialog.show();
            }
        });
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSendClick();
                onReceive();
                Edit_text.setText("");
            }
        });
        exit_chat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatActivity.this.startActivity(myIntent);
            }
        });
    }
    private void onOpenClick(){
        connect_button = (ImageButton) findViewById(R.id.imageButton5);
        mConnect = new ConnectionClient();
        mConnect.Connect(HOST,PORT);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mConnect.openConnection();
                    Log.d(LOG_TAG, "Соединение установлено");
                    connect_button.setImageResource(R.drawable.connect_sucsesfull);
                } catch (Exception e){
                    Log.e(LOG_TAG, e.getMessage());
                    mConnect = null;
                }
            }
        }).start();
    }
    private void onSendClick(){
        Edit_text = (EditText) findViewById(R.id.editText);
        if (mConnect == null){
            Log.d(LOG_TAG, "Соединение не установлено");
        } else {
            Log.d(LOG_TAG, "Отправка сообщения");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String text;
                        text = Edit_text.getText().toString() + "\n\0";
                        mConnect.sendData(text);
                    } catch (Exception e){
                        Log.e(LOG_TAG, e.getMessage());
                    }
                }
            }).start();
        }
    }
    private void onReceive(){
        TextView txt_view = (TextView) findViewById(R.id.Reveive_Text);
        if (mConnect == null){
            Log.d(LOG_TAG, "Соединение не установлено");
        }else {
            Log.d(LOG_TAG, "Получение сообщения");
            new Thread(new Runnable() {
                @Override
                    public void run () {
                        try {
                            txt_view.append(mConnect.receiveData());
                        } catch (Exception e) {
                            Log.e(LOG_TAG, e.getMessage());
                        }
                    }
            }).start();
        }
    }
    public void onBackPressed(){
        Intent exit_intent = new Intent(ChatActivity.this, MainActivity.class);
        ChatActivity.this.startActivity(exit_intent);
    }
}

