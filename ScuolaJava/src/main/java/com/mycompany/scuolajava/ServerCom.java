package com.mycompany.scuolajava;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCom 
{
    private ServerSocket server;
    private Socket client;
    private int operando1;
    private int operando2;
    private String operatore;
    private double risultato;
    private DataOutputStream outVersoClient;
    private BufferedReader inDalClient;
    
    public Socket attendi()
    {
        try
        {
            System.out.println("1 SERVER partito in esecuzione");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            
            outVersoClient = new DataOutputStream(client.getOutputStream());
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }
    
    public void comunica()
    {
        try
        {
            System.out.println("3 benvenuto client");
            operando1 = Integer.parseInt(inDalClient.readLine());
            operando2 = Integer.parseInt(inDalClient.readLine());
            operatore = inDalClient.readLine();
            
            switch (operatore)
            {
                case "+":
                    risultato = (double)operando1 + operando2;
                    break;
                case "-":
                    risultato = (double)operando1 - operando2;
                    break;
                case "*":
                    risultato = (double)operando1 * operando2;
                    break;
                case "/":
                    risultato = (double)operando1 / operando2;
                    break;
                default:
                    throw new Exception("L'operatore non è valido");
            }
            
            System.out.println("6 ricevuti dati dal client");
            
            System.out.println("7 invio il risultato al client");
            outVersoClient.writeBytes(risultato + "\n");
            
            System.out.println("8 SERVER termina elaborazione e buona notte");
            client.close();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comuncazione con il server");
            System.exit(1);
        }
    }
}
