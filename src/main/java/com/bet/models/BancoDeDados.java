// Em BancoDeDados.java (seu repositório em memória)
package com.bet.models;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Repository;

@Repository
public class BancoDeDados {

    private final Map<BigInteger, Cliente> clientes = new HashMap<>();
    private BigInteger proximoId = BigInteger.ONE;

    public Cliente adicionarCliente(Cliente cliente) {
        cliente.setId(proximoId);
        proximoId = proximoId.add(BigInteger.ONE);

        BigInteger tokenAleatorio = new BigInteger(50, new Random());
        cliente.setToken(tokenAleatorio);

        clientes.put(cliente.getId(), cliente);
        
        System.out.println("Cliente salvo no banco: " + cliente.getNome() + " " + cliente.getSobrenome() + " com ID: " + cliente.getId() +
            "\n\nDados do cliente: " + cliente.getCpf() + "\n " + cliente.getEmail() + "\n " + cliente.getToken() + "\n " + cliente.getTelefone() + "\n " + cliente.getSaldo() + "\n " + cliente.getEstadoAtual());
        
        return cliente;
    }

    public Cliente buscarPorId(BigInteger id) {
        return clientes.get(id);
    }
}