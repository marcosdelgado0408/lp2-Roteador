package src;

    /*
                  PortaCima
                     |
  portaEsquerda - roteador - portaDireta
                     |
                 portaBaixo
    */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Roteador extends DispositivoDeRede implements Roteamento {

    private Portas portaEsquerda, portaCima, portaDireita, portaBaixo, portaRede;


    public Roteador(){
        this.portaEsquerda = new Portas();
        this.portaCima = new Portas();
        this.portaDireita = new Portas();
        this.portaBaixo = new Portas();
        this.portaRede = new Portas();
    }

    public Portas getPortaRede() { return portaRede; }

    public Portas getPortaEsquerda() { return portaEsquerda; }

    public Portas getPortaCima() { return portaCima; }


    public Portas getPortaDireita() { return portaDireita; }


    public Portas getPortaBaixo() { return portaBaixo; }


    @Override
    public void setIP(String Ip) { this.Ip = Ip; }

    @Override
    public String getIp() { return this.Ip; }

    @Override
    public void setMac(String Mac) { this.Mac = Mac; }

    @Override
    public String getMac() { return this.Mac; }





    /*
    [192.168.0.1]  [192.168.0.2]  [192.168.0.3]

    [192.168.0.4]  [192.168.0.5]  [192.168.0.6]

    [192.168.0.7]  [192.168.0.8]  [192.168.0.9]
    */

    public String roteamento(Pacote pacote, Roteador[][] roteadores, int i, int j){

        String destino = pacote.getDestino();

        System.out.println("Posição atual: " + this.getIp());
        System.out.println("Destino: " + destino);
        System.out.println("Pacote em trânsito: " + pacote.getDados());


        // aparentemente o unico modo de dar split por um ponto é utilizando colchetes "[.]"
        int numeroDestino  = Integer.parseInt(destino.split("[.]")[3]); // pegando o último numero do ip

        int numeroAtual = Integer.parseInt(getIp().split("[.]")[3]); // pegando o último numero do ip



        // Caso chegue no ip destino vai gravar a mensagem em um arquivo com o nome do ip desino
        if(this.getIp().equals(destino)) {
            try {
                File file = new File(pacote.getDestino() + ".txt");
                FileWriter fileWriter = new FileWriter(file, true); // true -> modo para dar append no arquivos

                fileWriter.write(pacote.getDados() + "\n");

                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "stop"; // caso o pacote ja esteja no seu destino;
        }

        else if (numeroDestino > numeroAtual){

            // se der para direita ele vai para direita
            if(j + 1 < roteadores[i].length){
                return "portaDireita";
            }

            // se não der para direita e der para baixo ele vai para baixo
            else if(i + 1 < roteadores.length){
                return "portaBaixo";
            }

            // se não der para nenhum dos casos acimam ele vai para esquerda
            else{
                return "portaEsquerda";
            }


        }

        else {

            if(j - 1 >= 0){ // a posição mínima que eu posso ir é a 0
                return "portaEsquerda";
            }

            // se não der para esquerda e der para cima ele vai para cima
            else if(i - 1 >= 0){
                return "portaCima";
            }

            // se não der para nenhum dos casos acimam ele vai para direita
            else{
                return "portaDireita";
            }

        }


    }




}
