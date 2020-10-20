package com.mycompany.scuolajava;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientCom 
{
    private String nomeServer = "localhost";
    private int portaServer = 6789;
    private Socket socket;
    private BufferedReader tastiera;
    private int operando1;
    private int operando2;
    private String operatore;
    private double risultato;
    private DataOutputStream outVersoServer;
    private BufferedReader inDalServer;
    
    public Socket connetti()
    {
        System.out.println("2 CLIENT partito in esecuzione");
        try
        {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(socket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } 
        catch (UnknownHostException e)
        {
            System.out.println("Host sconosciuto");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return socket;
    }
    
    public void comunica()
    {
        try
        {
            System.out.println("4 inserisci i due operandi e l'operatore da trasmettere al server:\n");
            operando1 = Integer.parseInt(tastiera.readLine());
            operando2 = Integer.parseInt(tastiera.readLine());
            operatore = tastiera.readLine();
            
            System.out.println("5 invio al server e attendo");
            outVersoServer.writeBytes(operando1 + "\n");
            outVersoServer.writeBytes(operando2 + "\n");
            outVersoServer.writeBytes(operatore + "\n");
            
            risultato = Double.parseDouble(inDalServer.readLine());
            System.out.println("8 risposta dal server\n" + risultato);
            
            System.out.println("9 CLIENT termina elaborazione e chiude connessione");
            socket.close();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comuncazione con il server");
            System.exit(1);
        }
    }
}
