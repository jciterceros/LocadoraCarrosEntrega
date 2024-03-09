package LocadoraCarros;

import LocadoraCarros.classe.ConexaoBanco;
import LocadoraCarros.services.MainService;

public class App {

    public static void main(String[] args) {
        new ConexaoBanco().conectar();
        
        new MainService().iniciarSistema();
    }
}
