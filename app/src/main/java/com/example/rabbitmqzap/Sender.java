package com.example.rabbitmqzap;
import android.os.AsyncTask;
import android.util.Log;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.ChannelContinuationTimeoutException;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender extends AsyncTask <Void, Void, Void> {

    private ConnectionFactory factory;
    private String mesage, nomeFila, nomeGrupo;

    public Sender(String mesage, String nomeFila, String nomeGrupo){

        this.mesage = mesage;
        this.nomeFila = nomeFila;
        this.nomeGrupo = nomeGrupo;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            factory = new ConnectionFactory();
            factory.setHost("jackal.rmq.cloudamqp.com");
            factory.setUsername("srfyinmc");
            factory.setPassword("gcckle90rrIydG82JHV972AZ9T9AUrAl");
            factory.setVirtualHost("srfyinmc");
            Connection conexao = factory.newConnection();
            Channel canal = conexao.createChannel();


            if(this.nomeFila!=""){
                canal.queueDeclare(nomeFila, false, false, false, null);
            }else
                if(this.nomeGrupo!=""){
                canal.exchangeDeclare(nomeGrupo, BuiltinExchangeType.FANOUT);
            }

            canal.basicPublish(nomeGrupo,nomeFila, null,mesage.getBytes("UTF-8"));
            canal.close();
            conexao.close();

        } catch (IOException | TimeoutException e) {
            Log.d("testesTiago", e.getCause()+"a");
        }
        return null;
    }
}
