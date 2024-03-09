package LocadoraCarros.repositorys;

import LocadoraCarros.classe.ConexaoBanco;
import LocadoraCarros.model.Fabricante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FabricanteRepository {

    // Consultar todos os fabricantes
    public List<Fabricante> consultar() {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "SELECT * from fabricante";
            ResultSet result = statement.executeQuery(sql);
            List<Fabricante> listaFabricante = new ArrayList<>();

            while (result.next()) {
                listaFabricante.add(new Fabricante(
                        result.getLong("id"),
                        result.getString("nome")));
            }

            return listaFabricante;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return null;
        }
    }

    // Consultar fabricante por id
    public Fabricante consultar(Long pId) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "SELECT * from fabricante WHERE id = " + pId;
            ResultSet result = statement.executeQuery(sql);
            Fabricante fabricante = new Fabricante();

            while (result.next()) {
                fabricante.setId(result.getLong("id"));
                fabricante.setNome(result.getString("nome"));
            }

            return fabricante;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return null;
        }
    }

    // Inserir fabricante
    public boolean inserir(Fabricante pFabricante) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "INSERT INTO fabricante (nome) VALUES ('" + pFabricante.getNome() + "')";
            statement.execute(sql);

            return true;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return false;
        }
    }

    // Atualizar fabricante
    public boolean atualizar(Fabricante pFabricante) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "UPDATE fabricante SET nome = '" + pFabricante.getNome() + "' WHERE id = "
                    + pFabricante.getId();

            statement.execute(sql);

            return true;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return false;
        }
    }

    // Deletar fabricante
    public boolean deletar(Long pId) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String sql = "DELETE FROM fabricante WHERE id = " + pId;
            statement.execute(sql);

            return true;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return false;
        }
    }
}
