package LocadoraCarros.model;

public class Fabricante {

    private Long id;
    private String nome;

    public Fabricante() {
    }

    public Fabricante(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Fabricante(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
