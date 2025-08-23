package com.bet.models;

public class JogoDeDadoInvestimento {
    private int dadoEscolhido;
    private double odd;
    private double valorApostado;

    public JogoDeDadoInvestimento(int dadoEscolhido, double odd, double valorApostado) {
        this.dadoEscolhido = dadoEscolhido;
        this.odd = odd;
        this.valorApostado = valorApostado;
    }

    public int getDadoEscolhido() {
        return dadoEscolhido;
    }

    public void setDadoEscolhido(int dadoEscolhido) {
        this.dadoEscolhido = dadoEscolhido;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public double getValorApostado() {
        return valorApostado;
    }

    public void setValorApostado(double valorApostado) {
        this.valorApostado = valorApostado;
    }

}
