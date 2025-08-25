package com.bet.controllers;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; //Importa as anotações do Spring MVC para definição da classe controladora, mapeamento de rotas e manipulação de requisições
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bet.models.Cliente;
import com.bet.models.Deposito;
import com.bet.models.Premium;
import com.bet.models.Saque;
import com.bet.service.ClienteService;
import com.bet.service.SaldoService;


@Controller
@RequestMapping("/info")
public class InfoClienteController {
	
    private final ClienteService clienteService;
    private final SaldoService saldoService;

    public InfoClienteController(ClienteService clienteService, SaldoService saldoService) {
        this.clienteService = clienteService;
        this.saldoService = saldoService;
    }
    
    @GetMapping("/usuario")
    public String clienteLogado(HttpSession session, Model model) { 
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        if (idCliente != null) {
            Cliente cliente = clienteService.buscarPorId(idCliente);
            model.addAttribute("cliente", cliente);
            return "user";
        } else {
            return "redirect:/info/usuario"; 
        }
    }
    
    @PutMapping("/atualizarinfo")
    public String atualizarInformacoes(@RequestBody Cliente cliente, HttpSession session) {
        if (cliente.getSenha() == null || cliente.getSenha().isEmpty()) {    
            return "redirect:/info/usuario"; // Se a senha for nula ou vazia, redireciona para a página de informações do usuário
        }
        
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        Cliente dadosCliente = clienteService.buscarPorId(idCliente);
        return "redirect:/info/usuario";
    }

    @PostMapping("/deposito")
    public String depositar(@RequestBody Deposito infoDeposito, HttpSession session) {
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        Cliente dadosCliente = clienteService.buscarPorId(idCliente);
        if (infoDeposito.getCpf().equals(dadosCliente.getCpf()) && infoDeposito.getEmail().equals(dadosCliente.getEmail()) && infoDeposito.getSenha().equals(dadosCliente.getSenha())) {
            saldoService.deposito(dadosCliente, infoDeposito.getValor());
            System.out.println(dadosCliente.getUltimasTransacoes());
            return "redirect:/info/usuario";
        }
        return "redirect:/info/usuario";
    }

    @PostMapping("/saque")
    @ResponseBody
    public String sacar(@RequestBody Saque infoSaque, HttpSession session) {
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        Cliente dadosCliente = clienteService.buscarPorId(idCliente);
        if (infoSaque.getCpf().equals(dadosCliente.getCpf()) && infoSaque.getEmail().equals(dadosCliente.getEmail()) && infoSaque.getSenha().equals(dadosCliente.getSenha())) {
            saldoService.saque(dadosCliente, infoSaque.getValor());
            System.out.println(dadosCliente.getUltimasTransacoes());
            return "redirect:/info/usuario";
        }
        return "redirect:/info/usuario";
    }

    @PostMapping("/premium")
    @ResponseBody
    public String tornarPremium(@RequestBody Premium infoPremium, HttpSession session) {
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        Cliente dadosCliente = clienteService.buscarPorId(idCliente);
        if (infoPremium.getCpf().equals(dadosCliente.getCpf()) && infoPremium.getEmail().equals(dadosCliente.getEmail()) && infoPremium.getSenha().equals(dadosCliente.getSenha())) {
            dadosCliente.virarPremium();
            System.out.println(dadosCliente.getEstadoAtual());
            return "redirect:/info/usuario";
        }
        return "redirect:/info/usuario";
    }

    @PostMapping("/basico")
    @ResponseBody
    public String tornarBasico(@RequestBody Premium infoPremium, HttpSession session) {
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        Cliente dadosCliente = clienteService.buscarPorId(idCliente);
        if (infoPremium.getCpf().equals(dadosCliente.getCpf()) && infoPremium.getEmail().equals(dadosCliente.getEmail()) && infoPremium.getSenha().equals(dadosCliente.getSenha())) {
            dadosCliente.virarContaPadrao();
            System.out.println(dadosCliente.getEstadoAtual());
            return "redirect:/info/usuario";
        }
        return "redirect:/info/usuario";
    }



    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // Retorna o nome da view que será carregada pelo Thymeleaf
    }
}
