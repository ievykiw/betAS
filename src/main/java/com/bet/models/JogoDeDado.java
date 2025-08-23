    package com.bet.models;
    import java.math.BigDecimal;
    import java.util.Random;
    //Jogo do valor implementado com logica aleatoria de dados com base na porcentagem de ganho do Cliente;
    public class JogoDeDado {
        Random random = new Random();
        private int valorVerde1;
        private int valorVerde2;
        private int verdeTotal;
        private int azulTotal;
        private int valorAzul1;
        private int valorAzul2;
        private int valorEscolhido;
        private boolean vitoriaDefinida;
        private Cliente cliente;
        private String mensagem;
        private double odd;
        private double valorApostado;
        private double metaDeVitoria;

        //Faz a rolagem de valores armazenando o resultado total deles e enviando de volta para o metodo jogarJogo;
        public void rolagemDevalores(){
                this.valorAzul1 = random.nextInt(6)+1;
                this.valorAzul2 = random.nextInt(6)+1;
                this.azulTotal = valorAzul1 + valorAzul2;
                this.valorVerde1 = random.nextInt(6)+1;
                this.valorVerde2 = random.nextInt(6)+1;
                this.verdeTotal = valorVerde1 + valorVerde2;
        }
        /*Faz a rodada usando a chance de ganho calculada de acordo com o tipo de valor que o 
        jogador escolher, enviando direto para o metodo de resultado para validacao de status da aposta*/
        public ResultadoDoJogo jogarJogo(Cliente cliente, double odd, double valorApostado, int valorEscolhido){
            this.cliente = cliente;
            this.valorEscolhido = valorEscolhido;
            this.odd = odd;
            this.valorApostado = valorApostado;
            if(odd == 1.25){
                this.metaDeVitoria = cliente.getFatorProbabilidade();
            }else if( odd == 1.5){
                this.metaDeVitoria = cliente.getFatorProbabilidade() - 5;
            }else {
                this.metaDeVitoria = cliente.getFatorProbabilidade() - 10;
            }

            this.vitoriaDefinida = random.nextDouble() < metaDeVitoria;

            if(vitoriaDefinida){
                if(valorEscolhido == 0){
                    do{
                        rolagemDevalores();  
                    }while(verdeTotal>=azulTotal);
                }else{
                    do{
                        rolagemDevalores();
                    }while(azulTotal>=verdeTotal);
                }
            }else{
                if(valorEscolhido == 0){
                    do{
                        rolagemDevalores();
                    }while(azulTotal > verdeTotal);
                }else{
                    do{
                        rolagemDevalores();
                    }while(verdeTotal > azulTotal);
                }
            }
            return this.resultado(azulTotal,verdeTotal);
        }
        /*Determina se o jogador venceu a aposta ou nao, modificando o saldo dele automaticamente, apos isso envia o resultado 
        da aposta juntamente com todas as outras informacoes para o ResultadoDoJogo e retorna de volta tudo junto para o ir para o front*/
        public ResultadoDoJogo resultado(int azul, int verde){
            String resultado;
            if(valorEscolhido == 0){
                if(azul > verde){
                    cliente.ganharAposta(BigDecimal.valueOf(valorApostado).multiply(BigDecimal.valueOf(odd)));
                    resultado = "Ganhou!";
                }else{
                    cliente.perderAposta(BigDecimal.valueOf(valorApostado));
                    resultado = "Perdeu!";
                }
            }else{
                if(verde > azul){
                    cliente.ganharAposta(BigDecimal.valueOf(valorApostado).multiply(BigDecimal.valueOf(odd)));
                    resultado = "Ganhou!";
                }else{
                    cliente.perderAposta(BigDecimal.valueOf(valorApostado));
                    resultado = "Perdeu!";
                }
            }
            return new ResultadoDoJogo(resultado,valorAzul1,valorAzul2,valorVerde1,valorVerde2,valorApostado,odd,cliente.getSaldo());
        }
    }
