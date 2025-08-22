package com.bet.models;

public class InfoEditadaCliente {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String senha;
    private String ddd;
    private String telefone;

    public InfoEditadaCliente() {}

    public InfoEditadaCliente(String nome, String sobrenome, String cpf, String email, String senha, String ddd, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.ddd = ddd;
        this.telefone = telefone;
    }
}

