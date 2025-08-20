package com.bet.models;
//Estado Padrao da conta do Cliente
public class EstadoNormal implements TipoContaState{
    private String estado = "BASICO";
    @Override
    public double porcentagemGanho() {
        return 0.25;
    }
    public String getEstado(){
        return estado;
    }
}
