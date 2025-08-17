package com.bet.models;

public class EstadoNormal implements TipoContaState{
    private String estado = "BASICO";
    @Override
    public double porcentagemGanho() {
        return 0.20;
    }
    public String getEstado(){
        return estado;
    }
}
