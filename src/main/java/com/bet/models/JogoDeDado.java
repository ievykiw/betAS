    package com.bet.models;
    import java.math.BigDecimal;
    import java.util.Random;
    //Jogo do valor implementado com logica aleatoria de dados com base na porcentagem de ganho do Cliente;
    public class JogoDeDado {
        Random random = new Random();
        private int dadoVerde1;
        private int dadoVerde2;
        private int verdeTotal;
        private int azulTotal;
        private int dadoAzul1;
        private int dadoAzul2;
        private int dadoEscolhido;
        private boolean vitoriaDefinida;
        private Cliente cliente;
        private String mensagem;
        private double odd;
        private double valorApostado;
        private double metaDeVitoria;

        //Faz a rolagem de valores armazenando o resultado total deles e enviando de volta para o metodo jogarJogo;
        public void rolagemDeDados(){
                this.dadoAzul1 = random.nextInt(6)+1;
                this.dadoAzul2 = random.nextInt(6)+1;
                this.azulTotal = dadoAzul1 + dadoAzul2;
                this.dadoVerde1 = random.nextInt(6)+1;
                this.dadoVerde2 = random.nextInt(6)+1;
                this.verdeTotal = dadoVerde1 + dadoVerde2;
        }
        /*Faz a rodada usando a chance de ganho calculada de acordo com o tipo de valor que o 
        jogador escolher, enviando direto para o metodo de resultado para validacao de status da aposta*/
        public ResultadoDoJogo jogarJogo(Cliente cliente, double odd, double valorApostado, int dadoEscolhido){
            this.cliente = cliente;
            this.dadoEscolhido = dadoEscolhido;
            this.odd = odd;
            this.valorApostado = valorApostado;
            
            //Lógica de quanto maior for a odd que o cliente quiser apostar, mais dificil vai ser de ganhar
            if(odd == 1.25){
                this.metaDeVitoria = cliente.getFatorProbabilidade();
            }else if( odd == 1.5){
                this.metaDeVitoria = cliente.getFatorProbabilidade() - 0.05;
            }else {
                this.metaDeVitoria = cliente.getFatorProbabilidade() - 0.10;
            }

            //Calcula se o jogo deve ser vencido ou nao com base na probabilidade do cliente
            this.vitoriaDefinida = random.nextDouble() < metaDeVitoria;

            //De acordo com o resultado do calculo da probabilidade o jogo obrigatoriamente ganha ou perde, usando um loop para a certeza do resultado
            if(vitoriaDefinida){
                if(dadoEscolhido == 0){
                    do{
                        rolagemDeDados();  
                    }while(verdeTotal>=azulTotal);
                }else{
                    do{
                        rolagemDeDados();
                    }while(azulTotal>=verdeTotal);
                }
            }else{
                if(dadoEscolhido == 0){
                    do{
                        rolagemDeDados();
                    }while(azulTotal > verdeTotal);
                }else{
                    do{
                        rolagemDeDados();
                    }while(verdeTotal > azulTotal);
                }
            }
            return this.resultado(azulTotal,verdeTotal);
        }
        /*Determina se o jogador venceu a aposta ou nao, modificando o saldo dele automaticamente, apos isso envia o resultado 
        da aposta juntamente com todas as outras informacoes para o ResultadoDoJogo e retorna de volta tudo junto para o ir para o front*/
        public ResultadoDoJogo resultado(int azul, int verde){
            String resultado;
            if(dadoEscolhido == 0){
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
            return new ResultadoDoJogo(resultado,dadoAzul1,dadoAzul2,dadoVerde1,dadoVerde2,valorApostado,odd,cliente.getSaldo());
        }
    }
