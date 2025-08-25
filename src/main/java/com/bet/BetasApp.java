package com.bet;

import org.springframework.boot.SpringApplication; //Importa a classe SpringApplication do Spring Boot para iniciar a aplicação
import org.springframework.boot.autoconfigure.SpringBootApplication; //Importa a anotação SpringBootApplication do Spring Boot para definir a classe principal da aplicação

@SpringBootApplication //Anotação para definir a classe atual como sendo a classe main da aplicação Spring Boot (realiza a inicialização do sistema) 
public class BetasApp { //Definição da classe main da aplicação

    public static void main(String[] args) {
        SpringApplication.run(BetasApp.class, args); //Chama o método run da classe SpringApplication para iniciar a aplicação Spring Boot
    }
}