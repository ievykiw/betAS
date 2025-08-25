package com.bet.models;

import org.junit.jupiter.api.Test; // Importa a anotação @Test do JUnit 5, usada para marcar métodos de teste
import org.junit.jupiter.api.BeforeEach; // Importa a anotação @BeforeEach, que executa um método antes de cada teste para inicializar objetos

import java.math.BigDecimal; // Importa a classe BigDecimal para manipulação de números decimais

import static org.junit.jupiter.api.Assertions.*; // Importa os métodos estáticos de Assertions, como assertEquals e assertNull, para verificar os resultados dos testes

class DepositoTest { // Declara a classe de teste DepositoTest, responsável por validar o comportamento da classe Deposito

    private Deposito deposito; // Declara um atributo do tipo Deposito que será usado nos testes

    @BeforeEach // Anotação que indica que o método abaixo será executado antes de cada teste, garantindo a preparação do objeto
    void setUp() {
        deposito = new Deposito(); // Inicializa um novo objeto Deposito antes de cada execução de teste
    }

    @Test  // Anotação do JUnit usada para marcar um método como teste
    void testSeteGetValor() {
        BigDecimal valorEsperado = new BigDecimal("100.50");
        
        deposito.setValor(valorEsperado);
        BigDecimal valorObtido = deposito.getValor();
        
        assertEquals(valorEsperado, valorObtido);
        assertEquals(0, valorEsperado.compareTo(valorObtido));
    }

    @Test
    void testSeteGetValorNulo() {
        deposito.setValor(null);
        BigDecimal valorObtido = deposito.getValor();
        
        assertNull(valorObtido);
    }

    @Test
    void testSeteGetCpf() {
        String cpfEsperado = "123.456.789-00";
        
        deposito.setCpf(cpfEsperado);
        String cpfObtido = deposito.getCpf();
        
        assertEquals(cpfEsperado, cpfObtido);
    }

    @Test
    void testSeteGetCpfNulo() {
        deposito.setCpf(null);
        String cpfObtido = deposito.getCpf();
        
        assertNull(cpfObtido);
    }

    @Test
    void testSeteGetEmail() {
        String emailEsperado = "cliente@email.com";
        
        deposito.setEmail(emailEsperado);
        String emailObtido = deposito.getEmail();
        
        assertEquals(emailEsperado, emailObtido);
    }

    @Test
    void testSetAndGetSenha() {
        String senhaEsperada = "senhaSegura123";
        
        deposito.setSenha(senhaEsperada);
        String senhaObtida = deposito.getSenha();
        
        assertEquals(senhaEsperada, senhaObtida);
    }

    @Test
    void testSeteGetSenhaVazia() {
        String senhaEsperada = "";
        
        deposito.setSenha(senhaEsperada);
        String senhaObtida = deposito.getSenha();
        
        assertEquals(senhaEsperada, senhaObtida);
    }

    @Test
    void testeDeClasse() {
        BigDecimal valor = new BigDecimal("500.75");
        String cpf = "987.654.321-00";
        String email = "joao.silva@bet.com";
        String senha = "minhaSenhaSecreta";
        
        deposito.setValor(valor);
        deposito.setCpf(cpf);
        deposito.setEmail(email);
        deposito.setSenha(senha);
        
        assertEquals(valor, deposito.getValor());
        assertEquals(cpf, deposito.getCpf());
        assertEquals(email, deposito.getEmail());
        assertEquals(senha, deposito.getSenha());
    }

    @Test
    void testeBigDecimalValor() {
        BigDecimal valorPreciso = new BigDecimal("123.4567");
        
        deposito.setValor(valorPreciso);
        BigDecimal valorObtido = deposito.getValor();
        
        assertEquals(valorPreciso, valorObtido);
        assertEquals(4, valorObtido.scale());
    }
}