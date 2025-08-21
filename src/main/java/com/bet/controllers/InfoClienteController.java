package com.bet.controllers;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller; //Importa as anotações do Spring MVC para definição da classe controladora, mapeamento de rotas e manipulação de requisições
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bet.models.Cliente;
import com.bet.service.ClienteService;


@Controller
@RequestMapping("/info")
public class InfoClienteController {
	
    private final ClienteService clienteService;

    public InfoClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @GetMapping("/user")
    public String clienteLogado(HttpSession session, Model model) { 
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        if (idCliente != null) {
            Cliente cliente = clienteService.buscarPorId(idCliente);
            model.addAttribute("cliente", cliente);
            return "user";
        } else {
            return "redirect:/info/user"; 
        }
    }    

    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // Retorna o nome da view que será carregada pelo Thymeleaf
    }
}
