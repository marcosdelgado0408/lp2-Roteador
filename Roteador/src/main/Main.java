package main;


public class Main {
    public static void main(String[] args) {
        GerenciadorRoteamento gerenciadorRoteamento = new GerenciadorRoteamento();
        gerenciadorRoteamento.setRouters();
        gerenciadorRoteamento.receberInternet();
        gerenciadorRoteamento.enviarPacote();

    }
}
