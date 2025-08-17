package com.bet.models;

public class EstadoPremium implements TipoContaState {
    private String estado = "PREMIUM";
    @Override
    public double porcentagemGanho() {
        return 0.80;    
    }
    public String getEstado(){
        return estado;
    }
}