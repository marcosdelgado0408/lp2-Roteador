package main;

import src.*;

import java.io.*;
import java.util.Scanner;

/*
    [0][0] [0][1] [0][2]

    [1][0] [1][1] [1][2]

    [2][0] [2][1] [2][2]
*/


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
                    //System.out.println(this.roteadores[i][j].getIp());
                }
            }

            scanner.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void receberInternet() {
        try {
            File file = new File("comunicação.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()){
                String ipOrigem = scanner.next();


                for(int i=0;i<roteadores.length;i++){
                    for(int j=0;j<roteadores[i].length;j++){
                        if(ipOrigem.equals(roteadores[i][j].getIp())){ // caso o ip origem for o ip de um dos roteadores
                            Pacote pacote = new Pacote();

                            String ipDestino = scanner.next();
                            int quantidadeMsg = Integer.parseInt(scanner.next());
                            String Msg = scanner.next();
                            String finalMsg = "";

                            pacote.setDestino(ipDestino);

                            for (int k=0;k<quantidadeMsg;k++){
                                finalMsg += Msg;
                            }
                            pacote.setDados(finalMsg);

                            roteadores[i][j].getPortaRede().getBufferEntrada().add(pacote);

                           // System.out.println(roteadores[i][j].getPortaRede().getBufferEntrada());
                        }
                    }
                }


            }


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void enviarPacote(){

        for(int i=0;i<roteadores.length;i++){
            for(int j=0;j<roteadores[i].length;j++){

                Portas portaRede = roteadores[i][j].getPortaRede();

                if(!portaRede.getBufferEntrada().isEmpty()){ // caso o buffer não for vazio
                    Pacote pacote = roteadores[i][j].getPortaRede().getBufferEntrada().remove();
                    String porta = roteadores[i][j].roteamento(pacote,roteadores,i,j); // essa função só retornará a porta para onde ele deve ir

                    //System.out.println("Porta que veio da rede: " + porta);

                    switch (porta) {
                        case "portaDireita":
                            roteadores[i][j+1].getPortaDireita().getBufferSaida().add(pacote);
                            percorrerRoteadores(i,j+1, porta);
                            break;
                        case "portaBaixo":
                            roteadores[i+1][j].getPortaBaixo().getBufferSaida().add(pacote);
                            percorrerRoteadores(i+1,j, porta);
                            break;
                        case "portaEsquerda":
                            roteadores[i][j-1].getPortaEsquerda().getBufferSaida().add(pacote);
                            percorrerRoteadores(i,j-1, porta);
                            break;
                        case "portaCima":
                            roteadores[i-1][j].getPortaCima().getBufferSaida().add(pacote);
                            percorrerRoteadores(i-1,j, porta);
                            break;
                    }
                }

            }
        }

    }

    private void percorrerRoteadores(int i, int j, String porta) {

        Pacote pacote;
        System.out.println(porta);
        System.out.println(i);
        System.out.println(j);


        switch (porta){

            case "portaDireita":
                pacote = roteadores[i][j].getPortaDireita().getBufferSaida().remove();
                System.out.println(pacote);
                porta = roteadores[i][j].roteamento(pacote,this.roteadores,i,j);
                if(porta.equals("stop")){
                    System.out.println("Pacote chegou ao seu destino\n");
                    System.out.println("------------------------------------------------------------------------------");
                    return;
                }
                enviarPara(pacote, porta, i, j);
                break;

            case "portaBaixo":
                pacote = roteadores[i][j].getPortaBaixo().getBufferSaida().remove();
                porta = roteadores[i][j].roteamento(pacote,this.roteadores,i,j);
                if(porta.equals("stop")){
                    System.out.println("Pacote chegou ao seu destino\n");
                    System.out.println("------------------------------------------------------------------------------");
                    return;
                }
                enviarPara(pacote, porta, i, j);
                break;

            case "portaEsquerda":
                pacote = roteadores[i][j].getPortaEsquerda().getBufferSaida().remove();
                porta = roteadores[i][j].roteamento(pacote, this.roteadores, i, j);
                if(porta.equals("stop")){
                    System.out.println("Pacote chegou ao seu destino\n");
                    System.out.println("------------------------------------------------------------------------------");
                    return;
                }
                enviarPara(pacote, porta, i, j);
                break;

            case "portaCima":
                pacote = roteadores[i][j].getPortaCima().getBufferSaida().remove();
                porta = roteadores[i][j].roteamento(pacote,this.roteadores,i,j);
                if(porta.equals("stop")){
                    System.out.println("Pacote chegou ao seu destino");
                    System.out.println("------------------------------------------------------------------------------");
                    return;
                }
                enviarPara(pacote, porta, i, j);
                break;

        }




    }



    public void enviarPara(Pacote pacote,String porta,int i, int j){

        switch (porta){

            case "portaDireita":
                roteadores[i][j+1].getPortaDireita().getBufferSaida().add(pacote);
                percorrerRoteadores(i, j+1, porta);
                break;

            case "portaBaixo":
                roteadores[i+1][j].getPortaBaixo().getBufferSaida().add(pacote);
                percorrerRoteadores(i+1, j, porta);
                break;

            case "portaEsquerda":
                roteadores[i][j-1].getPortaEsquerda().getBufferSaida().add(pacote);
                percorrerRoteadores(i, j-1, porta);
                break;

            case "portaCima":
                roteadores[i-1][j].getPortaCima().getBufferSaida().add(pacote);
                percorrerRoteadores(i-1, j, porta);
                break;

        }


    }




}
