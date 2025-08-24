package com.bet.controllers;

import java.math.BigInteger; //Importa a classe BigInteger para manipulação de números inteiros grandes (nesse caso o ID)

import javax.servlet.http.HttpSession; //Importa a classe HttpSession para manipular a sessão HTTP do usuário

import org.springframework.stereotype.Controller; //Importa as anotações do Spring MVC para definição da classe controladora, mapeamento de rotas e manipulação de requisições
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bet.models.Cliente;
import com.bet.models.LoginUser; //Importa a classe Cliente que representa o usuário do sistema
import com.bet.models.RegistroUser; //Importa a classe LoginUser para verificação  dos dados de login do usuário
import com.bet.service.ClienteService; //Importa a classe RegistroUser para verificação dos dados de registro de novos usuários

@Controller //Anotação Spring MVC para indicar que esta classe é um controlador e que irá lidar com requisições HTTP
@RequestMapping("/api") //Anotação para definir o caminho base para as rotas do controlador LoginController 
public class LoginController {

    private final ClienteService clienteService; //Criando um atributo constante do tipo ClienteService para acessar os méetodos de serviço relacionados ao Cliente

    //Construtor para injetar o ClienteService no LoginController para utilizar os métodos de validação e registro de clientes
    public LoginController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/logar") //Anotação para mapear requisições do tipo POST e definição do caminho /logar para o método de login
    @ResponseBody //Anotação para inidicar que a informação retornada pelo método será escrita diretamente no corpo da resposta HTTP
    //Método para validar o login do cliente, recebendo um objeto do tipo LoginUser e a sessão HTTP
    public Cliente logar(@RequestBody LoginUser loginUser, HttpSession session) {  //O RquestBody é usando para converter o corpo da requisição em um objeto do tipo RegistroUser e o HttpSession é usado para armazenar informações do usuário registrado
        //Chama o método validarLogin do ClienteService passando o email e senha do LoginUser
        Cliente cliente = clienteService.validarLogin(loginUser.getEmail(), loginUser.getSenha());
        //Se o cliente for encontrado, armazena o ID do cliente na sessão HTTP
        if (cliente != null) {
            session.setAttribute("usuarioLogado", cliente.getId());
        }
        //Retorna o objeto Cliente, que pode ser nulo se o login falhar
        return cliente;
    }

    @PostMapping("/registrar") //Anotação para mapear requisições do tipo POST e definição do caminho /registrar para o método de registro
    @ResponseBody
    //Método para registrar um novo cliente, recebendo um objeto do tipo RegistroUser e a sessão HTTP
    public Cliente registrar(@RequestBody RegistroUser registroUser, HttpSession session) {
        //Chama o método registrarCliente do ClienteService passando o RegistroUser
        Cliente clienteCriado = clienteService.registrarCliente(registroUser);
        //Se o cliente for criado com sucesso, armazena o ID do cliente na sessão HTTP
        if (clienteCriado != null) {
            session.setAttribute("usuarioLogado", clienteCriado.getId());
        }
        //Retorna o objeto Cliente criado como resposta da requisição
        return clienteCriado;
    }

    @GetMapping("/home") //Anotação para mapear requisições do tipo GET e definição do caminho /home para o método clienteLogado
    //Método para exibir a página inicial do cliente logado, contendo suas informações, recebendo a sessão HTTP e o modelo para adicionar atributos
    public String clienteLogado(HttpSession session, Model model) { //O HttpSession é usado para acessar a sessão do usuário logado e o Model é usado para adicionar atributos relacionados ao cliente da sessão
        //Consulta o ID do cliente logado da sessão HTTP
        BigInteger idCliente = (BigInteger) session.getAttribute("usuarioLogado");
        //Se o ID do cliente não for nulo, busca o cliente pelo ID e adiciona ao modelo
        if (idCliente != null) {
            Cliente cliente = clienteService.buscarPorId(idCliente); //Chama o método buscarPorId do ClienteService passando o ID do cliente
            model.addAttribute("cliente", cliente); //Adiciona o cliente ao modelo para ser acessado na view
            return "home"; //Retorna o nome da view será carregado pela ferramenta de templates, nesse caso, o Thymeleaf
        } else {
            //Se o ID do cliente for nulo, redireciona para a página de login
            return "redirect:/api/menu"; 
        }
    }
    
    @GetMapping("/menu") // Anotação para mapear requisições do tipo GET e definição do caminho /login para o método showLoginPage
    public String showLoginPage() { // Método para exibir a página de login 
        return "menu"; // Isso renderiza o arquivo /resources/templates/login.html
    }
    
}