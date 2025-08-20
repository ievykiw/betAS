package com.bet.models;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Cliente {
    private BigInteger id;
    private BigDecimal saldo;
    private BigInteger token;

    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private TipoContaState estadoAtual; // criacao da classe de estado atrelado ao State


    public Cliente() {}
    //Construtor
    public Cliente(String nome, String sobrenome, String cpf,
        String email, String senha, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.estadoAtual = new EstadoNormal();
    }
    //metodos de Acesso da Classe CLiente
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigInteger getToken() {
        return token;
    }

    public void setToken(BigInteger token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getFatorProbabilidade(){
        return this.estadoAtual.porcentagemGanho();
    }
    // Mudança de Estado da conta com base na troca do tipo de conta(Padrao ou Premium)
    public void virarPremium(){
        estadoAtual = new EstadoPremium();
    }
    
    public void virarContaPadrao(){
        estadoAtual = new EstadoNormal();
    }

    public String getEstadoAtual() {
        return estadoAtual.getEstado();
    }

    public void perderAposta(BigDecimal valorPerdido){
        this.saldo = this.saldo.subtract(valorPerdido);
    }
    
    public void ganharAposta(BigDecimal valorGanho){
        this.saldo = this.saldo.add(valorGanho);
    }
}


