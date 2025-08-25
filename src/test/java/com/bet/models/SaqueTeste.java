package com.bet.models;

import org.junit.jupiter.api.Test; // Importa a anotação @Test do JUnit 5, usada para marcar métodos de teste
import org.junit.jupiter.api.BeforeEach; // Importa a anotação @BeforeEach, que executa um método antes de cada teste para inicializar objetos

import java.math.BigDecimal; // Importa a classe BigDecimal para manipulação de números decimais

import static org.junit.jupiter.api.Assertions.*; // Importa os métodos estáticos de Assertions, como assertEquals e assertNull, para verificar os resultados dos testes

class SaqueTest { // Declara a classe de teste SaqueTest, responsável por validar o comportamento da classe Saque

    private Saque saque; // Declara um atributo do tipo Saque que será usado nos testes

    @BeforeEach // Anotação que indica que o método abaixo será executado antes de cada teste, garantindo a preparação do objeto
    void setUp() {
        saque = new Saque(); // Inicializa um novo objeto Saque antes de cada execução de teste
    }

    @Test // Anotação do JUnit usada para marcar um método como teste
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