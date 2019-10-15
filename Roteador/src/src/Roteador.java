package src;

import java.util.LinkedList;
import java.util.Queue;

public class Roteador extends DispositivoDeRede implements Roteamento {

    private Portas porta1, porta2, porta3, porta4, portaRede;
    private Queue<Pacote> Buffers;


    public Roteador(){
        this.Buffers = new LinkedList<>();
        this.porta1 = new Portas();
        this.porta2 = new Portas();
        this.porta3 = new Portas();
        this.porta4 = new Portas();
        this.portaRede = new Portas();
    }


    @Override
    public void setIP(String Ip) { this.Ip = Ip; }

    @Override
    public String getIp() { return this.Ip; }

    @Override
    public void setMac(String Mac) { this.Mac = Mac; }

    @Override
    public String getMac() { return this.Mac; }


    public String roteamento(Pacote pacote){
      return pacote.getDestino();
    }


}
