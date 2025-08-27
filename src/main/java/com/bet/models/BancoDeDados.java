package com.bet.models;

import java.math.BigInteger; //Importa a classe BigInteger para manipulação de números inteiros grandes (nesse caso o ID)
import java.util.HashMap; //Importa a classe HashMap para armazenamento de dados do usuário em tempo de execução, com correlação de ID e o objeto do tipo Cliente
import java.util.Map; //Importa a interface Map de dados no formato chave-valor, nesse caso o HashMap
import java.io.IOException;
import java.nio.file.*;

import org.springframework.stereotype.Repository; //IMporta a anotação de repositório do Spring
import org.springframework.beans.factory.InitializingBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Repository //Anotação do Spring para informar que esta classe é uma classe que irá interagir com o banco de dados

//Definição da classe BancoDeDados para armazenamento e manipulação dos dados do sistema
public class BancoDeDados implements InitializingBean {

    private final Path pasta = Paths.get("data");
    private final Path arquivo = pasta.resolve("clientes.json");

    private final Map<BigInteger, Cliente> clientes = new HashMap<>(); //Instancia um HashMap que armazena dados do tipo Cliente, com a chave sendo o ID do Cliente
    private BigInteger proximoId = BigInteger.ONE; //Inicialização do ID dos clientes, começando com o valor 1 do tipo BigInteger

    private final ObjectMapper mapper = new ObjectMapper() // Jackson para serialização
            .enable(SerializationFeature.INDENT_OUTPUT);

    private static volatile BancoDeDados BancoInstantaneo;

    private static class Snapshot { // Estrutura auxiliar para salvar/ler o snapshot do estado
        public Map<BigInteger, Cliente> clientes;
        public BigInteger proximoId;

        public Snapshot() {}
        public Snapshot(Map<BigInteger, Cliente> clientes, BigInteger proximoId) {
            this.clientes = clientes;
            this.proximoId = proximoId;
        }
    }

    @Override
    public void afterPropertiesSet(){ // Método de inicialização automática
        try{
            carregarDoDisco();
            BancoInstantaneo = this;
        } catch (Exception e) {
            System.err.println("[BancoDeDados] Falha ao carregar clientes.json: " + e.getMessage());
            e.printStackTrace();
            this.clientes.clear();
            this.proximoId = BigInteger.ONE;
            try{ salvarNoDisco(); }catch (Exception ignore) {}
        }
    }

    private void carregarDoDisco(){ // Carregar dados do arquivo
        try {
            if (!Files.exists(pasta)) Files.createDirectories(pasta);
            if (!Files.exists(arquivo)) {
                salvarNoDisco();
                return;
            }
            byte[] json = Files.readAllBytes(arquivo);
            if (json.length == 0) {
                salvarNoDisco();
                return;
            }

            Snapshot snap = mapper.readValue(json, Snapshot.class);
            if (snap != null && snap.clientes != null) {
                clientes.clear();
                clientes.putAll(snap.clientes);
                proximoId = (snap.proximoId == null) ? BigInteger.ONE : snap.proximoId;
            }
        } catch (IOException e) {
            throw new RuntimeException("Falha ao carregar clientes.json", e);
        }
    }

    private synchronized void salvarNoDisco(){ // Salvar dados no arquivo
        try{
            Snapshot snap = new Snapshot(clientes, proximoId);
            byte[] json = mapper.writeValueAsBytes(snap);
            Files.write(arquivo, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }catch (IOException e) {
            throw new RuntimeException("Falha ao salvar clientes.json", e);
        }
    }

    public synchronized Cliente adicionarCliente(Cliente cliente) { //Criação do método para adicionar um cliente ao banco de dados
        cliente.setId(proximoId); //Atribui o valor do ID ao atributo id do cliente
        proximoId = proximoId.add(BigInteger.ONE); //Incrementa o atributo proximoId para o pŕoximo cliente que for adicionado ao banco

        clientes.put(cliente.getId(), cliente); //Utiliza o método put do HashMap para adicionar o cliente ao banco de dados com a chave sendo o id do proprio cliente
        salvarNoDisco();

        System.out.println("Cliente salvo no banco: " + cliente.getNome() + " " + cliente.getSobrenome() + " com ID: " + cliente.getId() +
            "\n\nDados do cliente: " + cliente.getCpf() + "\n " + cliente.getEmail() + "\n " + cliente.getDdd() + "\n " + cliente.getTelefone() + "\n " + cliente.getSaldo() + "\n " + cliente.getEstadoAtual());
        
        return cliente;
    }
    
    public synchronized void atualizarCliente(Cliente cliente){ // Atualiza qualquer modificação feita no cliente no banco de dados
        clientes.put(cliente.getId(), cliente);
        salvarNoDisco();
    }
    
    public synchronized void salvarEstado(){ // Método usado para salvar em arquivo
        salvarNoDisco();
    }

    public static void atualizarAgora(Cliente cliente){ // Faz uma atualização instantânea no banco de dados
        BancoDeDados bd = BancoInstantaneo;
        if (bd != null && cliente != null && cliente.getId() != null){
            bd.atualizarCliente(cliente);
        }
    }

    public Cliente buscarPorId(BigInteger id) { //Método para buscar um cliente no banco através do id
        return clientes.get(id);
    }

    public Cliente validarLogin(String email, String senha) { //Método para validar o login do cliente através do email e senha
        for (Cliente cliente : clientes.values()) { //Para todos os dados do tipo Cliente, itera sobre cada um dos valores do mesmo tipo armazenados no banco de dados
            if (cliente.getEmail() != null && cliente.getSenha() != null) { //Verifica se o cliente da posição atual possui email e senha
                if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) { //Se o email e senha desse cliente existir no banco de dados retorna o cliente
                    return cliente;
                }
            }
        }
        return null;
    }
}