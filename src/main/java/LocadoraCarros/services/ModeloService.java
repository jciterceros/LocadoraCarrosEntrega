package LocadoraCarros.services;

import LocadoraCarros.model.Modelo;
import LocadoraCarros.repositorys.ModeloRepository;

import java.util.ArrayList;
import java.util.List;

public class ModeloService {

    // Consultar todos os modelos
    public List<Modelo> consultar() {
        return new ModeloRepository().consultar();
    }

    // Consultar todos os modelos por id do fabricante
    public List<Modelo> consultar(Long idFabricante) {
        return new ModeloRepository().consultar(idFabricante);
    }

    // Verifica se Existe modelo
    public Boolean existeModelo(List<Modelo> pModelos, Modelo pModelo) {
        for (Modelo modelo : pModelos) {
            if (modelo.getIdFabricante().equals(pModelo.getIdFabricante())) {
                String nome = modelo.getNome().toUpperCase().trim();
                if ((nome.contains(pModelo.getNome().toUpperCase().trim()))
                        || (pModelo.getNome().toUpperCase().trim().contains(nome))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Salvar modelo
    public void salvar(Modelo pModelo) {
        Boolean result = new ModeloRepository().inserir(pModelo);
        if (result) {
            System.out.println("Modelo salvo com sucesso!");
        }
    }

    // Atualizar modelo
    public void atualizar(Modelo pModelo) {
        Boolean result = new ModeloRepository().atualizar(pModelo);
        if (result) {
            System.out.println("Modelo atualizado com sucesso!");
        }
    }

    // Deletar modelo
    public void deletar(Long pId) {
        Boolean result = new ModeloRepository().deletar(pId);
        if (result) {
            System.out.println("Modelo deletado com sucesso!");
        }
    }

    // TODO: Verificar se é necessário
    public Long selecionar(Long idFabricante) {
        List<Modelo> listaModelos = consultar(idFabricante);

        System.out.println("Lista de Modelos");
        System.out.println("___________________________________");

        List<Long> idsModelos = new ArrayList<>();

        for (Modelo modelo : listaModelos) {
            System.out.println(modelo.toString());
            idsModelos.add(modelo.getId());
        }

        System.out.println("___________________________________");
        System.out.println("Selecione o modelo desejado: ");

        Long idModelo = 0L;

        try {
            idModelo = Long.parseLong(new LeituraService().ler());
        } catch (Exception ex) {
            System.out.println("Opcao Invalida");
            return -1L;
        }

        if (!idsModelos.contains(idModelo)) {
            System.out.println("Opcao Invalida");
            return -1L;
        }

        return idModelo;
    }

}
