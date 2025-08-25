package com.bet.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SaqueTest {

    private Saque saque;

    @BeforeEach
    void setUp() {
        saque = new Saque();
    }

    @Test
    void testSeteGetValor() {
        BigDecimal valorEsperado = new BigDecimal("100.50");
        
        saque.setValor(valorEsperado);
        BigDecimal valorObtido = saque.getValor();
        
        assertEquals(valorEsperado, valorObtido);
        assertEquals(0, valorEsperado.compareTo(valorObtido));
    }

    @Test
    void testSeteGetValorNulo() {
        saque.setValor(null);
        BigDecimal valorObtido = saque.getValor();
        
        assertNull(valorObtido);
    }

    @Test
    void testSeteGetCpf() {
        String cpfEsperado = "123.456.789-00";
        
        saque.setCpf(cpfEsperado);
        String cpfObtido = saque.getCpf();
        
        assertEquals(cpfEsperado, cpfObtido);
    }

    @Test
    void testSeteGetCpfNulo() {
        saque.setCpf(null);
        String cpfObtido = saque.getCpf();
        
        assertNull(cpfObtido);
    }

    @Test
    void testSeteGetEmail() {
        String emailEsperado = "cliente@email.com";
        
        saque.setEmail(emailEsperado);
        String emailObtido = saque.getEmail();
        
        assertEquals(emailEsperado, emailObtido);
    }

    @Test
    void testSetAndGetSenha() {
        String senhaEsperada = "senhaSegura123";
        
        saque.setSenha(senhaEsperada);
        String senhaObtida = saque.getSenha();
        
        assertEquals(senhaEsperada, senhaObtida);
    }

    @Test
    void testSeteGetSenhaVazia() {
        String senhaEsperada = "";
        
        saque.setSenha(senhaEsperada);
        String senhaObtida = saque.getSenha();
        
        assertEquals(senhaEsperada, senhaObtida);
    }

    @Test
    void testeDeClasse() {
        BigDecimal valor = new BigDecimal("500.75");
        String cpf = "987.654.321-00";
        String email = "joao.silva@bet.com";
        String senha = "minhaSenhaSecreta";
        
        saque.setValor(valor);
        saque.setCpf(cpf);
        saque.setEmail(email);
        saque.setSenha(senha);
        
        assertEquals(valor, saque.getValor());
        assertEquals(cpf, saque.getCpf());
        assertEquals(email, saque.getEmail());
        assertEquals(senha, saque.getSenha());
    }

    @Test
    void testeBigDecimalValor() {
        BigDecimal valorPreciso = new BigDecimal("123.4567");
        
        saque.setValor(valorPreciso);
        BigDecimal valorObtido = saque.getValor();
        
        assertEquals(valorPreciso, valorObtido);
        assertEquals(4, valorObtido.scale());
    }
}