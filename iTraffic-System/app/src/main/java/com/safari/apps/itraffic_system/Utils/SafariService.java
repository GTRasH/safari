package com.safari.apps.itraffic_system.Utils;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.safari.apps.itraffic_system.Models.xml.Safari;
import com.safari.apps.itraffic_system.Models.xml.spat.SPAT;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Diese Klasse baut eine Verbindung mit dem Server
*/

public class SafariService  {

   public  TcpClient.OnMessageReceived handler ;

    private static final SafariService ourInstance = new SafariService();

   public  static SafariService getInstance() {
        return ourInstance;
    }
//Host:Port
    private String uri ="ws://red-dev.de:15000";

   /*
   LoginDaten fuer Server:
   safari
   glosaRulez18
   */

    private SafariService() {
        serialQueue = Executors.newSingleThreadExecutor();
    }

    ExecutorService serialQueue  ;

  TcpClient mTcpClient;

    public void disconnectWebSocket(){

        if(mTcpClient!=null){
            mTcpClient.stopClient();
            mTcpClient = null;
        }
    }
    public void connectWebSocket() {

//Erzeugt einem Thread. Hat einem Interface mit dem selben Typ (TCP Client MessageReceived). Wenn es an instance hat (Spat oder Safari), schickt die Methode zum AmpelActivity
         Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {

                    mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                        @Override
                        public void messageReceived(Safari message  ,String original) {

                            Log.d(TAG, "Received: " + original);

                            if(handler!=null){

                                handler.messageReceived(message , original);
                            }
                        }

//Verarbeitet die SPAT Nachrichten
                        @Override
                        public void messageReceived(SPAT message  ,String original) {

                            Log.d(TAG, "Received: " + original);

                            if(handler!=null){

                                handler.messageReceived(message , original);
                            }
                        }
                    });
                    mTcpClient.run();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    public static final String TAG = SafariService.class.getSimpleName();

//Schickt einem Message zu TCP Client aber mit einem Queu, damit es nur ein Message nacheinander schickt und nicht gleichzeitig
    public void sendMessage(final String message) {

        Log.i(TAG, "Sending: " + message);

        serialQueue.submit(new Runnable() {
            @Override
            public void run() {

                mTcpClient.sendMessage(message);
            }
        });

    }

}