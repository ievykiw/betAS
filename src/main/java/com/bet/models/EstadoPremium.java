package com.bet.models;

public class EstadoPremium implements TipoContaState {
    @Override
    public double porcentagemGanho() {
        return 0.80;
    }
}