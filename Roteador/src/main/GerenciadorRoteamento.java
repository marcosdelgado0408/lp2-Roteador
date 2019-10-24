package main;

import src.*;

import java.io.*;
import java.util.Scanner;

public class GerenciadorRoteamento {
    public Roteador[][] roteadores;


    public GerenciadorRoteamento(){
        roteadores = new Roteador[3][3];


        for(int i=0;i<roteadores.length;i++) {
            for (int j = 0; j < roteadores[i].length; j++) {
                roteadores[i][j] = new Roteador();
            }
        }

    }

    public void setRouters(){
        try {

            File file = new File("ips.txt");
            Scanner scanner = new Scanner(file);

            for(int i=0;i<roteadores.length;i++){
                for(int j=0;j<roteadores[i].length;j++){
                    String ip = scanner.next();
                    this.roteadores[i][j].setIP(ip);
                    System.out.println(this.roteadores[i][j].getIp());
                }
            }

            scanner.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }








}
