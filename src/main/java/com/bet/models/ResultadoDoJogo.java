package com.bet.models;

import java.math.BigDecimal;
//Classe feita apenas para retornar o JSON com todas as informaçoes
public class ResultadoDoJogo {
    private final String mensagem;
    private final int dadoAzul1, dadoAzul2;
    private final int dadoVerde1, dadoVerde2;
    private double valorApostado, odd;
    private BigDecimal saldoAtual;

    public ResultadoDoJogo(String mensagem, int dadoAzul1, int dadoAzul2, int dadoVerde1, int dadoVerde2,double valorApostado, double odd,BigDecimal saldoAtual) {
        this.mensagem = mensagem;
        this.dadoAzul1 = dadoAzul1;
        this.dadoAzul2 = dadoAzul2;
        this.dadoVerde1 = dadoVerde1;
        this.dadoVerde2 = dadoVerde2;
        this.valorApostado = valorApostado;
        this.odd = odd;
        this.saldoAtual = saldoAtual;
    }

    public String getMensagem(){ 
        return mensagem; 
    }
    public int getDadoAzul1() { 
        return dadoAzul1; 
    }
    public int getDadoAzul2() { 
        return dadoAzul2; 
    }   
    public int getDadoVerde1() { 
        return dadoVerde1; 
    }
    public int getDadoVerde2() { 
        return dadoVerde2; 
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