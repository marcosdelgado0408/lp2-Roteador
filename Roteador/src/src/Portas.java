package src;

public class Portas {

   Pacote bufferEntrada;
   Pacote bufferSaida;

   public Portas(){
       this.bufferEntrada = new Pacote();
       this.bufferEntrada = new Pacote();
   }


    public Pacote getBufferEntrada() { return bufferEntrada; }

    public Pacote getBufferSaida() { return bufferSaida; }

    public void setBufferEntrada(Pacote bufferEntrada) { this.bufferEntrada = bufferEntrada; }

    public void setBufferSaida(Pacote bufferSaida) { this.bufferSaida = bufferSaida; }


}
