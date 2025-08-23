package com.bet.models;

import java.math.BigDecimal;
//Classe feita apenas para retornar o JSON com todas as informaçoes
public class ResultadoDoJogo {
    private final String mensagem;
    private final int valorAzul1, valorAzul2;
    private final int valorVerde1, valorVerde2;
    private double valorApostado, odd;
    private BigDecimal saldoAtual;

    public ResultadoDoJogo(String mensagem, int valorAzul1, int valorAzul2, int valorVerde1, int valorVerde2,double valorApostado, double odd,BigDecimal saldoAtual) {
        this.mensagem = mensagem;
        this.valorAzul1 = valorAzul1;
        this.valorAzul2 = valorAzul2;
        this.valorVerde1 = valorVerde1;
        this.valorVerde2 = valorVerde2;
        this.valorApostado = valorApostado;
        this.odd = odd;
        this.saldoAtual = saldoAtual;
    }

    public String getMensagem(){ 
        return mensagem; 
    }
    public int getvalorAzul1() { 
        return valorAzul1; 
    }
    public int getvalorAzul2() { 
        return valorAzul2; 
    }   
    public int getvalorVerde1() { 
        return valorVerde1; 
    }
    public int getvalorVerde2() { 
        return valorVerde2; 
    }
    public BigDecimal getSaldoAtual() { 
        return saldoAtual; 
    }
    public double getValorApostado() {
        return valorApostado;
    }
    public double getOdd() {
        return odd;
    }
}