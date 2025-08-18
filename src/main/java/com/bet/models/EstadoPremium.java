package com.bet.models;
//Estado Premium da conta do Cliente
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