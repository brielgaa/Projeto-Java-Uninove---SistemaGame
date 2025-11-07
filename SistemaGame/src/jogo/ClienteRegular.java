package jogo;

import java.io.Serializable;

public class ClienteRegular extends Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

    public ClienteRegular(String nome, String email, double saldo) {
        super(nome, email, saldo);
    }

    @Override
    public double calcularDesconto(double valorOriginal) {
        return valorOriginal;
    }

    @Override
    public String getTipoCliente() {
        return "Regular";
    }
}