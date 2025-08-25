package com.bet.controllers;

import java.math.BigInteger; //Importa a classe BigInteger para manipulação de números inteiros grandes (nesse caso o ID e o Token)

import javax.servlet.http.HttpSession; //Importa a classe BigDecimal para manipulação de números decimais grandes (nesse caso o valor da aposta)

import org.springframework.stereotype.Controller; //Importa a classe HttpSession para manipular a sessão HTTP do usuário
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; //Importa as anotações do Spring MVC para definição da classe controladora, mapeamento de rotas e manipulação de requisições
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bet.models.Cliente;
import com.bet.models.JogoDeDado;
import com.bet.models.JogoDeDadoInvestimento;
import com.bet.models.ResultadoDoJogo; //Importa a classe RegistroUser para verificação dos dados de registro de novos usuários
import com.bet.service.ClienteService; //Importa a classe Cliente para manipulação dos dados do cliente

@RequestMapping("/games") //Anotação para definir o caminho base para as rotas do controlador JogosController
@Controller //Anotação para definir a classe como um controlador Spring MVC
public class JogosController {
    
    private final ClienteService clienteService;

    public JogosController (ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/resultado")
    @ResponseBody
    public ResultadoDoJogo jogarDado(@RequestBody JogoDeDadoInvestimento rodadaJogo, HttpSession session) {
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        if (idCliente == null) {
            System.out.println("ID do cliente não encontrado na sessão.");
            throw new RuntimeException("ID do cliente não encontrado na sessão.");
        }

        Cliente cliente = clienteService.buscarPorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado para o ID: " + idCliente);
            throw new RuntimeException("Cliente não encontrado para o ID: " + idCliente);
        }

        if (new java.math.BigDecimal(rodadaJogo.getValorApostado()).compareTo(cliente.getSaldo()) > 0) {
            throw new RuntimeException("Saldo insuficiente para a aposta.");
        }

        JogoDeDado jogo = new JogoDeDado();
        ResultadoDoJogo resultado = jogo.jogarJogo(cliente, rodadaJogo.getOdd(), rodadaJogo.getValorApostado(), rodadaJogo.getDadoEscolhido());
        cliente.setSaldo(resultado.getSaldoAtual());

        return resultado;
    }

    @GetMapping("/dado")
    public String mostrarJogoDeDado(HttpSession session, Model model) {
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        if (idCliente != null) {
            Cliente cliente = clienteService.buscarPorId(idCliente);
            model.addAttribute("cliente", cliente);
        }
        return "dado";
    }
}
