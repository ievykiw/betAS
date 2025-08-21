package com.bet.service;

import com.bet.models.Cliente;
import com.bet.models.RegistroUser;

//Definição da interface ClienteService para definir os métodos de execução relacionados ao cliente
public interface ClienteService {
    
    Cliente registrarCliente(RegistroUser registroUser); //Define o método de registro de um novo cliente
    Cliente validarLogin(String email, String senha); //Define o método de validação de login de um cliente cadastrado
    Cliente buscarPorId(java.math.BigInteger id); //Define o método de busca de um cliente pelo id associado
}
