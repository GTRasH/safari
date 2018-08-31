package com.safari.apps.itraffic_system.Utils;

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

    /*
     Steuert und Baut die TCP Verbindung
     Check ob das XML gueltig ist
     Oeffnet die Socket-Verbindung
    */

public class TcpClient {

    public static final String TAG = TcpClient.class.getSimpleName();
    public static final String SERVER_IP = "red-dev.de"; // server IP address
    public static final int SERVER_PORT = 15000; // Port
// Nachricht, welche zum Server geschickt wird
    private String mServerMessage;
// Schickt Nachrichten - Bekommt notifications
    private OnMessageReceived mMessageListener = null;
// Sobald das true ist, Server wird weiter laufen
    private boolean mRun = false;
// Um Nachrichten zu senden
    private PrintWriter mBufferOut;
// Um Nachrichten vom Server zu lesen
    private BufferedReader mBufferIn;

// Oeffnet Port 15000 mit dem Server

    private  StringBuilder xmlString;

/*
 Constructor der Klasse. 
 OnMessagedReceived hoert die Nachrichten, welche vom Server gesenden wurden
*/
    public TcpClient(OnMessageReceived listener) {
        mMessageListener = listener;
    }

/**
 * Sendet die eingegebene Nachricht zum Server
 *
 * @param message text by client
 */
    public void sendMessage(final String message) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mBufferOut != null) {
                   Log.i(TAG, "Sending: " + message);
                    mBufferOut.println(message);
                    mBufferOut.flush();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

/*
  Schliesst die Verbindung und release die Elementen
*/
    public void stopClient() {

        mRun = false;

        if (mBufferOut != null) {
            mBufferOut.flush();
            mBufferOut.close();
        }

        mMessageListener = null;
        mBufferIn = null;
        mBufferOut = null;
        mServerMessage = null;
    }

    public void run() {

        mRun = true;

        try {
            
// Verbindung mit Server durch IP
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

            Log.d("TCP Client", "C: Connecting...");

// Erzeugt ein Socket um die Verbindung mit dem Server herzustellen
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            try {

// Sendet die Nachricht zum Server
                mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

// Bekommt die Nachricht, welche  receives the message which the server sends back
                mBufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));


// Durch das "While", der Client hoert fuer Nachrichten, welche vom Server geschickt wuedern 
                while (mRun) {

                    mServerMessage = mBufferIn.readLine();

                    if (mServerMessage != null && mMessageListener != null) {
// Ruf die Methode "messageReceived" von der Klasse
                     //   mMessageListener.messageReceived(mServerMessage);

// Trennt die XML Daten der bekommenen Safari oder SPAT Nachrichten
                        if(mServerMessage.equals("<safari>")||mServerMessage.equals("<SPAT>")){
                            xmlString= new StringBuilder();
                        }

                        if(xmlString!=null) {
                            xmlString.append(mServerMessage);
                        }
// Bis der Endung der Nachricht Safari                        
                        if(mServerMessage.equals("</safari>")){

                            Serializer serializer = new Persister();
                            try {
                                Safari xmlObject = serializer.read(Safari.class, xmlString.toString());
                                Log.d("xml",xmlObject.toString());

                                mMessageListener.messageReceived(xmlObject , xmlString.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            
                        }
//... oder SPAT
                        if(mServerMessage.equals("</SPAT>")){
                            Serializer serializer = new Persister();
                            try {
                                SPAT xmlObject = serializer.read(SPAT.class, xmlString.toString());
                                Log.d("xml",xmlObject.toString());

                                mMessageListener.messageReceived(xmlObject , xmlString.toString());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        Log.e("SafariService",mServerMessage);


                    }

                }

                Log.d("RESPONSE FROM SERVER", "S: Received Message: '" + mServerMessage + "'");

            } catch (Exception e) {
                Log.e("TCP", "S: Error", e);
            } finally {

// Nach der Schliessung der Socket, soll ein neues Instance der Socket erzeugt werden
                socket.close();
            }

        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
        }

    }

/*
Deklariert das Interface. 
Die Methode messageReceived(String message) ist beim SafariService implementiert
*/
    public interface OnMessageReceived {
          void messageReceived(Safari message ,String original);
          void messageReceived(SPAT message , String original);
    }

}
