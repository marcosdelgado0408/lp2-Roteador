package src;

import java.util.LinkedList;
import java.util.Queue;

public class Portas {

  private Queue<Pacote> bufferEntrada;
  private Queue<Pacote> bufferSaida;

   public Portas(){
       this.bufferEntrada = new LinkedList<>();
        this.bufferSaida = new LinkedList<>();
   }

    public Queue<Pacote> getBufferEntrada() { return bufferEntrada; }

    public void setBufferEntrada(Queue<Pacote> bufferEntrada) { this.bufferEntrada = bufferEntrada; }

    public Queue<Pacote> getBufferSaida() { return bufferSaida; }

}
