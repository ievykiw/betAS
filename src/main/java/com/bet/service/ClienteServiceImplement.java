package com.bet.service;

import java.math.BigDecimal; //Importa a classe BigDecimal para manipulação de números decimais, nesse caso o Saldo do Cliente
import java.math.BigInteger; //Importa a classe BigInteger para manipulação de números inteiros grandes (nesse caso o ID e o Token)

import org.springframework.stereotype.Service; //Importa a anotação Service do Spring para definir a classe como uma classe do tipo serviço

import com.bet.models.BancoDeDados; //Importa a classe BancoDeDados que contém as informações dos clientes cadastrados
import com.bet.models.Cliente; //Importa a classe Cliente para representar o objeto cliente do usuário do sistema
import com.bet.models.RegistroUser; //Importa a classe RegistroUser para criação de uma nova instância de Cliente com os dados do usuário que está se registrando

@Service
public class ClienteServiceImplement implements ClienteService { //Definição da classe ClienteServiceImplement para implementação dos métodos definidos na interface ClienteService
    
    private final BancoDeDados bancoDeDados; //Criação de um atributo constante do tipo BancoDeDados para acessar os dados dos clientes cadastrados

    public ClienteServiceImplement(BancoDeDados bancoDeDados) { //Construtor para injetar o BancoDeDados no ClienteServiceImplement
        this.bancoDeDados = bancoDeDados;
    }

    @Override //Anotação da interface para que o método abaixo seja uma implementação do método definido na interface
    public Cliente registrarCliente(RegistroUser registroUser) { //Implementação do método de registro de um novo cliente no sistema
        Cliente novoCliente = new Cliente(registroUser.getNome(), registroUser.getSobrenome(), registroUser.getCpf(), registroUser.getEmail(), registroUser.getSenha(), registroUser.getTelefone()); //Instanciamento de um novo objeto do tipo Cliente com os dados do objeto construido pelo RegistroUser
        novoCliente.setSaldo(BigDecimal.ZERO); //Define o saldo inicial do novo cliente como 0 utilizando o BigDecimal
        return bancoDeDados.adicionarCliente(novoCliente); //Chama o método adicionarCliente da classe BancoDeDados para adicionar o novo cliente ao banco e retorna o objeto Cliente adicionado

    }

    @Override
    public Cliente validarLogin(String email, String senha) { //Implementação do método de validação de login de um cliente cadastrado no sistema
        return bancoDeDados.validarLogin(email, senha); //Chama o método validarLogin da classe BancoDeDados para verificar se o email e senha existem no banco e retorna o objeto Cliente correspondente
    }

    @Override
    public Cliente buscarPorId (BigInteger id) { //Implementação do método de busca de um cliente pelo id associado
        return bancoDeDados.buscarPorId(id); //Chama o método buscarPorId da classe BancoDeDados para buscar o cliente pelo id e retorna o objeto Cliente correspondente
    }
}
