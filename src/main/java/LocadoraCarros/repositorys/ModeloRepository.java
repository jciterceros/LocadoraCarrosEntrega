package LocadoraCarros.repositorys;

import LocadoraCarros.classe.ConexaoBanco;
import LocadoraCarros.model.Modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloRepository {

    // Consultar todos os modelos
    public List<Modelo> consultar() {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "SELECT * from modelo";

            ResultSet result = statement.executeQuery(sql);

            List<Modelo> listaModelo = new ArrayList<>();

            while (result.next()) {
                listaModelo.add(new Modelo(result.getLong("id"),
                        result.getString("nome"),
                        result.getLong("id_fabricante")));
            }

            return listaModelo;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return null;
        }
    }

    // Consultar todos os modelos por ID
    public List<Modelo> consultar(Long idFabricante) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "SELECT * from modelo WHERE id_fabricante = " + idFabricante;

            ResultSet result = statement.executeQuery(sql);

            List<Modelo> listaModelo = new ArrayList<>();

            while (result.next()) {
                listaModelo.add(new Modelo(result.getLong("id"),
                        result.getString("nome"),
                        result.getLong("id_fabricante")));
            }

            return listaModelo;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return null;
        }
    }

    // Inserir modelo
    public boolean inserir(Modelo pModelo) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "INSERT INTO modelo (nome, id_fabricante) VALUES ('" + pModelo.getNome() + "', "
                    + pModelo.getIdFabricante() + ")";
            statement.execute(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return false;
        }
    }

    // Atualizar modelo
    public boolean atualizar(Modelo pModelo) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "UPDATE modelo SET nome = '" + pModelo.getNome() + "', id_fabricante = "
                    + pModelo.getIdFabricante() + " WHERE id = " + pModelo.getId();
            statement.execute(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return false;
        }
    }

    // Deletar modelo
    public boolean deletar(Long pId) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "DELETE FROM modelo WHERE id = " + pId;
            statement.execute(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return false;
        }
    }
}
