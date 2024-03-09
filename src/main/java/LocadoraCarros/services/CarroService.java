package LocadoraCarros.services;

import LocadoraCarros.model.Carro;
// import LocadoraCarros.model.Fabricante;
import LocadoraCarros.repositorys.CarroRepository;
// import LocadoraCarros.repositorys.FabricanteRepository;

// import java.util.ArrayList;
import java.util.List;

public class CarroService {

    // Consultar carros
    public List<Carro> consultar() {
        return new CarroRepository().consultar();
    }

    // Verifica se Existe carro
    public Boolean existeCarro(List<Carro> pCarros, String pPlaca) {
        for (Carro carro : pCarros) {
            if (carro.getPlaca().equals(pPlaca)) {
                return true;
            }
        }
        return false;
    }

    // Salvar carro
    public void salvar(Carro pCarro) {
        Boolean result = new CarroRepository().salvar(pCarro);
        if (result) {
            System.out.println("Carro salvo com sucesso!");
        }
    }

    // Atualizar carro
    public void atualizar(Carro pCarro) {
        Boolean result = new CarroRepository().atualizar(pCarro);
        if (result) {
            System.out.println("Carro atualizado com sucesso!");
        }
    }

    // Deletar um carro
    public void deletar(Long pId) {
        Boolean result = new CarroRepository().deletar(pId);
        if (result) {
            System.out.println("Carro deletado com sucesso!");
        }
    }

    // TODO: Verificar se é necessário
    public void cadastrar() {
        Carro carroCadastro = new Carro();

        Long idFabricante = new FabricanteService().selecionar();

        if (idFabricante == -1L) {
            return;
        }

        carroCadastro.setIdFabricante(idFabricante);

        Long idModelo = new ModeloService().selecionar(idFabricante);

        if (idModelo == -1L) {
            return;
        }

        carroCadastro.setIdModelo(idModelo);
        carroCadastro.setPlaca(getPlaca());
        carroCadastro.setCor(getCor());

        Boolean disponivel = getDisponivel();

        if (disponivel == null) {
            return;
        }

        carroCadastro.setDisponivel(disponivel);

        Integer ano = getAno();

        if (ano == -1) {
            return;
        }

        carroCadastro.setAno(ano);

        Double valorLocacao = getValorLocacao();

        if (valorLocacao == -1) {
            return;
        }

        carroCadastro.setValorLocacao(valorLocacao);

        new CarroRepository().salvar(carroCadastro);

        System.out.println("SALVO COM SUCESSO!");
    }

    // TODO: Verificar se é necessário
    public String getPlaca() {
        System.out.println("Informe a placa:");
        String placa = new LeituraService().ler();

        return placa;
    }

    // TODO: Verificar se é necessário
    public String getCor() {
        System.out.println("Informe a cor:");
        String cor = new LeituraService().ler();

        return cor;
    }

    // TODO: Verificar se é necessário
    public Boolean getDisponivel() {
        System.out.println("O veiculo está disponivel? (s/n)?");

        String escolha = new LeituraService().ler();

        if (!escolha.toUpperCase().equals("S")
                && !escolha.toUpperCase().equals("N")) {
            System.out.println("OPCAO INVALIDA");
            return null;
        }

        Boolean disponivel = false;

        if (escolha.toUpperCase().equals("S")) {
            disponivel = true;
        }

        return disponivel;
    }

    // TODO: Verificar se é necessário
    public Integer getAno() {
        System.out.println("Informe o Ano:");
        String ano = new LeituraService().ler();

        Integer anoCarro = -1;

        try {
            anoCarro = Integer.parseInt(ano);

        } catch (Exception ex) {
            System.out.println("Ano Invalido");
            return -1;
        }

        return anoCarro;
    }

    // TODO: Verificar se é necessário
    public Double getValorLocacao() {
        System.out.println("Informe o valor da locacao:");
        String valor = new LeituraService().ler();

        if (valor.contains(",")) {
            valor = valor.replace(",", ".");
        }

        Double valorLocacao = -1D;

        try {
            valorLocacao = Double.parseDouble(valor);

        } catch (Exception ex) {
            System.out.println("Valor Invalido");
            return -1D;
        }

        return valorLocacao;
    }
}
