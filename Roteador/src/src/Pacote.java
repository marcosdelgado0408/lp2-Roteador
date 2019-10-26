package src;

public class Pacote {
    private String Dados;
    private String Destino;

    public void setDados(String dados) { Dados = dados; }
    public String getDados() { return this.Dados; }

    public String getDestino() { return Destino; }
    public void setDestino(String destino) { Destino = destino; }

    @Override
    public String toString() {
        return Dados + "-" + Destino;
    }
}
