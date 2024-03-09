package LocadoraCarros.services;

import LocadoraCarros.model.Fabricante;
import LocadoraCarros.repositorys.FabricanteRepository;

import java.util.ArrayList;
import java.util.List;

public class FabricanteService {

    // Consultar fabricantes
    public List<Fabricante> consultar() {
        return new FabricanteRepository().consultar();
    }

    // Verifica se Existe fabricante
    public Boolean existeFabricante(List<Fabricante> pFabricantes, String pNome) {
        String nome = pNome.trim().toUpperCase();
        for (Fabricante fabricante : pFabricantes) {
            String nomeFabricante = fabricante.getNome().toUpperCase();
            if ((nome.contains(nomeFabricante)) || (nomeFabricante.contains(nome))) {
                return true;
            }
        }
        return false;
    }

    // Salvar fabricante
    public void salvar(Fabricante pFabricante) {
        Boolean result = new FabricanteRepository().inserir(pFabricante);
        if (result) {
            System.out.println("Fabricante salvo com sucesso!");
        }
    }

    // Atualizar fabricante
    public void atualizar(Fabricante pFabricante) {
        Boolean result = new FabricanteRepository().atualizar(pFabricante);
        if (result) {
            System.out.println("Fabricante atualizado com sucesso!");
        }
    }

    // Deletar fabricante
    public void deletar(Long pId) {
        Boolean result = new FabricanteRepository().deletar(pId);
        if (result) {
            System.out.println("Fabricante deletado com sucesso!");
        }
    }

    // TODO: Verificar se é necessário
    public Long selecionar() {
        List<Fabricante> listaFabricante = consultar();

        System.out.println("Lista Fabricantes");
        System.out.println("________________________________");

        List<Long> idsFabricantes = new ArrayList<>();

        for (Fabricante fabricante : listaFabricante) {
            System.out.println(fabricante.toString());
            idsFabricantes.add(fabricante.getId());
        }

        System.out.println("________________________________");

        System.out.println("Selecione o fabricante do carro: ");

        Long idFabricante = 0L;

        try {
            idFabricante = Long.parseLong(new LeituraService().ler());

        } catch (Exception ex) {
            System.out.println("OPCAO INVALIDA");
            return -1L;
        }

        if (!idsFabricantes.contains(idFabricante)) {
            System.out.println("OPCAO INVALIDA");
            return -1L;
        }

        return idFabricante;
    }
}
