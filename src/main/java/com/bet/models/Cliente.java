package com.bet.models;
import java.math.*;

public class Cliente {
    private BigInteger id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String senha;
    private String ddd;
    private String telefone;
    private BigDecimal saldo;
    private String email;
    private TipoContaState estadoAtual;

    public Cliente(BigInteger id, String nome, String sobrenome, String cpf, String senha, String ddd, String telefone, BigDecimal saldo, String email,double porcentagemGanho) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
        this.ddd = ddd;
        this.telefone = telefone;
        this.saldo = saldo;
        this.email = email;
        this.estadoAtual = new EstadoNormal();
        }

}