package jogo;

import java.io.Serializable;

public abstract class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
    protected String nome;
    protected int idUsuario;
    protected String email;
    protected double saldo;
    protected static int contadorId = 1;

    public Cliente(String nome, String email, double saldo) {
        this.idUsuario = contadorId++;
        this.nome = nome;
        this.email = email;
        this.saldo = saldo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void adicionarSaldo(double valor) {
        this.saldo += valor;
    }

    public boolean debitarSaldo(double valor) throws Exception {
        if (valor > saldo) {
            throw new Exception("Saldo insuficiente para realizar a compra!");
        }
        this.saldo -= valor;
        return true;
    }

    public abstract double calcularDesconto(double valorOriginal);
    
    public abstract String getTipoCliente();

    @Override
    public String toString() {
        return "ID: " + idUsuario + " | Nome: " + nome + " | Email: " + email + 
               " | Saldo: R$" + String.format("%.2f", saldo) + " | Tipo: " + getTipoCliente();
    }
}