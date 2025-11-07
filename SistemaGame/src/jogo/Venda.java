package jogo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;
    private Cliente cliente;
    private Jogo jogo;
    private double valorPago;
    private LocalDateTime dataCompra;
    private static int contadorId = 1;
    private int id;

    public Venda(Cliente cliente, Jogo jogo, double valorPago) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.jogo = jogo;
        this.valorPago = valorPago;
        this.dataCompra = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public double getValorPago() {
        return valorPago;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Venda ID: " + id + " | Cliente: " + cliente.getNome() + 
               " | Jogo: " + jogo.getTitulo() + " | Valor: R$" + String.format("%.2f", valorPago) +
               " | Data: " + dataCompra.format(formatter);
    }
}