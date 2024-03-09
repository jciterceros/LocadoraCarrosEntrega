package LocadoraCarros.services;

public class MenuService {
    
    public void menuInicial() {
        System.out.println("---- MENU INICIAL ----");
        System.out.println("1 - Cadastar Carro");
        System.out.println("2 - Cadastrar Modelo");
        System.out.println("Selecione a opção desejada: ");
        
        String opcao = new LeituraService().ler();
        
        switch(opcao){
            case "1":
                new CarroService().cadastrar();
                break;
            case "2":
                break;
            default:
                System.out.println("Opcao Inválida");
        }
    }
}
