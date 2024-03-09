package LocadoraCarros.repositorys;

import LocadoraCarros.classe.ConexaoBanco;
import LocadoraCarros.model.Modelo;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloRespository_Old {

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

        } catch (Exception ex) {
            System.out.println("Algo deu errado");
            return null;
        }
    }
}
