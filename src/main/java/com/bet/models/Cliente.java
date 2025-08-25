package com.bet.models;

import java.math.BigDecimal; //Importa a classe BigDecimal para manipulação de números decimais, nesse caso o Saldo do Cliente
import java.math.BigInteger; //Importa a classe BigInteger para manipulação de números inteiros grandes (nesse caso o ID)
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente { //Definição da classe Cliente com todos os atributos e métodos necessários para manipulação dos dados do cliente
    private BigInteger id; //Atributo id do cliente
    private BigDecimal saldo; //Atributo saldo do cliente

    private String nome; //Atributo nome do cliente
    private String sobrenome; //Atributo sobrenome do cliente
    private String cpf; //Atributo cpf do cliente
    private String email; //Atributo email do cliente
    private String senha; //Atributo senha do cliente
    private String ddd; //Atributo ddd do cliente, utilizado para validação de telefone
    private String telefone; //Atributo telefone do cliente
    private TipoContaState estadoAtual; // Atributo da classe de estado atrelado ao State

    private List<String> ultimasTransacoes = new ArrayList<>(3); // Atributo lista de últimas transações relacionadas ao cliente


    public Cliente() {}
    //Construtor
    public Cliente(String nome, String sobrenome, String cpf,
        String email, String senha,String ddd, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.ddd = ddd;
        this.telefone = telefone;
        this.estadoAtual = new EstadoNormal();
    }
    //Metodos de acesso aos atributos da Classe CLiente (getters e setters)
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

    public String getDdd() {
        return ddd;
    }
    
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
    
    public double getFatorProbabilidade(){
        return this.estadoAtual.porcentagemGanho();
    }
    
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

    public List<String> getUltimasTransacoes(){
        return ultimasTransacoes;
    }

    public void adicionarTransacao(String linha){
        if (ultimasTransacoes.size() == 3){
            ultimasTransacoes.remove(0);
        }
        ultimasTransacoes.add(linha);
    }
    
}


