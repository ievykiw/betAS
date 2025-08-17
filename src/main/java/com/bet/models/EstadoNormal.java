package com.bet.models;

public class EstadoNormal implements TipoContaState{
    @Override
    public double porcentagemGanho() {
        return 0.20;
    }
}
