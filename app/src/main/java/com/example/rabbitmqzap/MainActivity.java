package com.example.rabbitmqzap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sender sender = new Sender("Ola mateuzin rei delas","recebedor","");
        Sender sender1 = new Sender("mensagem2","","grupoTeste1");

        sender.execute();
        sender1.execute();

        ReceiverExchanges receiverExchange = new ReceiverExchanges("grupoTeste1");
        receiverExchange.execute();
    }
}