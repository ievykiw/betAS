package com.bet.models;

public class EstadoAlternar{ // Classe feita apenas para controle da reinicialização do banco de dados

    public static TipoContaState StringParaEstado(String nome){ // Alterna o nome de um estado de conta para o estado em si
        if (nome == null) return null;
        switch (nome){
            case "PREMIUM": return new EstadoPremium();
            case "BASICO": return new EstadoNormal();
            default: return new EstadoNormal();
        }
    }

    public static String EstadoParaString(TipoContaState estado){ // Alterna o estado de uma conta para o nome dele em string
        if (estado == null) return null;
        return estado.getEstado();
    }
}
    

