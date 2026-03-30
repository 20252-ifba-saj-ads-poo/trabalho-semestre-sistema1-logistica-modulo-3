package br.edu.ifba.saj.fwads.model;

public class Produto {
    private String nome;
    private double peso;
    private double volume;
    private int quantidade;

    public Produto(String nome, double peso, double volume, int quantidade) {
        this.nome = nome;
        this.peso = peso;
        this.volume = volume;
        this.quantidade = quantidade;
    }

    public String getNome()       { return nome; }
    public double getPeso()       { return peso; }
    public double getVolume()     { return volume; }
    public int getQuantidade()    { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return nome;   // o ListCell vai mostrar mais detalhes
    }
}