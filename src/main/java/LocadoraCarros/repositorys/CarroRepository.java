package LocadoraCarros.repositorys;

import LocadoraCarros.classe.ConexaoBanco;
import LocadoraCarros.model.Carro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroRepository {

    // Consultar todos os carros
    public List<Carro> consultar() {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();

            String sql = "SELECT * FROM carro";

            ResultSet result = statement.executeQuery(sql);

            List<Carro> listaCarro = new ArrayList<>();

            while (result.next()) {
                listaCarro.add(new Carro(
                        result.getLong("id"),
                        result.getLong("id_fabricante"),
                        result.getLong("id_modelo"),
                        result.getString("placa"),
                        result.getString("cor"),
                        result.getBoolean("disponivel"),
                        result.getInt("ano"),
                        result.getDouble("valorlocacao")));
            }

            return listaCarro;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return null;
        }
    }

    // Consultar carro por id
    public Carro consultar(Long pId) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();

            String sql = "SELECT * FROM carro WHERE id = " + pId;

            ResultSet result = statement.executeQuery(sql);

            Carro carro = new Carro();

            while (result.next()) {
                carro.setId(result.getLong("id"));
                carro.setIdFabricante(result.getLong("id_fabricante"));
                carro.setIdModelo(result.getLong("id_modelo"));
                carro.setPlaca(result.getString("placa"));
                carro.setCor(result.getString("cor"));
                carro.setDisponivel(result.getBoolean("disponivel"));
                carro.setAno(result.getInt("ano"));
                carro.setValorLocacao(result.getDouble("valorlocacao"));
            }

            return carro;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return null;
        }
    }

    // Salvar um carro
    public Boolean salvar(Carro pCarro) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String insertCarro = "INSERT INTO carro (id_fabricante, id_modelo, placa," +
                    "cor, disponivel, ano, valorlocacao) VALUES ("
                    + pCarro.getIdFabricante() + ","
                    + pCarro.getIdModelo() + ", '"
                    + pCarro.getPlaca() + "', '"
                    + pCarro.getCor() + "', "
                    + pCarro.getDisponivel() + ", "
                    + pCarro.getAno() + ", "
                    + pCarro.getValorLocacao() + ")";

            statement.execute(insertCarro);
            return true;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return false;
        }
    }

    // Atualizar carro
    public Boolean atualizar(Carro pCarro) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String updateCarro = "UPDATE carro SET id_fabricante = "
                    + pCarro.getIdFabricante()
                    + ", id_modelo = " + pCarro.getIdModelo()
                    + ", placa = '" + pCarro.getPlaca()
                    + "', cor = '" + pCarro.getCor()
                    + "', disponivel = " + pCarro.getDisponivel()
                    + ", ano = " + pCarro.getAno()
                    + ", valorlocacao = " + pCarro.getValorLocacao()
                    + " WHERE id = " + pCarro.getId();

            statement.execute(updateCarro);
            return true;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return false;
        }
    }

    // Deletar um carro
    public Boolean deletar(Long pId) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String deleteCarro = "DELETE FROM carro WHERE id = " + pId;

            statement.execute(deleteCarro);
            return true;

        } catch (SQLException ex) {
            System.out.println("Algo deu errado... " + ex.getMessage());
            return false;
        }
    }
}
