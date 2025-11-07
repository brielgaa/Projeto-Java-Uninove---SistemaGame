package jogo;

import java.io.Serializable;

public class Jogo implements Serializable {
	private static final long serialVersionUID = 1L;
    private String titulo;
    private String genero;
    private int anoLancamento;
    private double preco;
    private boolean disponivel;
    private static int contadorId = 1;
    private int id;

    public Jogo(String titulo, String genero, int anoLancamento, double preco) throws Exception {
        if (anoLancamento > 2025) {
            throw new Exception("Ano de lancamento nao pode ser maior que 2025!");
        }
        this.id = contadorId++;
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.preco = preco;
        this.disponivel = true;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) throws Exception {
        if (anoLancamento > 2025) {
            throw new Exception("Ano de lancamento nao pode ser maior que 2025!");
        }
        this.anoLancamento = anoLancamento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Titulo: " + titulo + " | Genero: " + genero + 
               " | Ano: " + anoLancamento + " | Preco: R$" + String.format("%.2f", preco) + 
               " | Status: " + (disponivel ? "Disponivel" : "Indisponivel");
    }
}