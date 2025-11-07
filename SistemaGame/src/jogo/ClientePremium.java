package jogo;

import java.io.Serializable;

public class ClientePremium extends Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final double DESCONTO_PERCENTUAL = 0.15;

    public ClientePremium(String nome, String email, double saldo) {
        super(nome, email, saldo);
    }

    @Override
    public double calcularDesconto(double valorOriginal) {
        return valorOriginal * (1 - DESCONTO_PERCENTUAL);
    }

    @Override
    public String getTipoCliente() {
        return "Premium (15% de desconto)";
    }
}