package com.bet.models;

import java.math.BigDecimal;

public class Saque extends Transacao {

    public Saque(){
        super();
    }

    public Saque(BigDecimal valor, String cpf, String email, String senha) {
        super(valor, cpf, email, senha);
    }
}