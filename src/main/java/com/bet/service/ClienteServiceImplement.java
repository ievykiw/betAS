package com.bet.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bet.models.BancoDeDados;
import com.bet.models.Cliente;
import com.bet.models.RegistroUser;

@Service
public class ClienteServiceImplement implements ClienteService {
    
    private final BancoDeDados bancoDeDados;

    public ClienteServiceImplement(BancoDeDados bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    @Override
    public Cliente registrarCliente(RegistroUser registroUser) {
        Cliente novoCliente = new Cliente(registroUser.getNome(), registroUser.getSobrenome(), registroUser.getCpf(), registroUser.getEmail(), registroUser.getSenha(), registroUser.getTelefone());
        novoCliente.setSaldo(BigDecimal.ZERO);
        return bancoDeDados.adicionarCliente(novoCliente);

    }
}
