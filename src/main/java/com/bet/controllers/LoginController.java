package com.bet.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping; // Importe sua interface de serviço
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bet.models.Cliente;
import com.bet.models.LoginUser;
import com.bet.models.RegistroUser;
import com.bet.service.ClienteService;

@RestController
@RequestMapping("/api") 
@CrossOrigin(origins = "http://127.0.0.1:5500") 

public class LoginController {

    private final ClienteService clienteService;

    public LoginController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/logar")
    
    public Cliente logar (@RequestBody LoginUser loginUser) {
        return clienteService.validarLogin(loginUser.getEmail(), loginUser.getSenha());
    }
    
    @PostMapping("/registrar")

    public ResponseEntity<Cliente> registrar(@RequestBody RegistroUser registroUser) {
        Cliente clienteCriado = clienteService.registrarCliente(registroUser);
        return ResponseEntity.ok(clienteCriado);
    }
}