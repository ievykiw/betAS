    package com.bet.models;
    import java.math.BigDecimal;
    import java.util.Random;
    //Jogo do dado implementado com logica aleatoria de dados com base na porcentagem de ganho do Cliente;
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

        //faz a rolagem de dados armazenando o resultado total deles e enviando de volta para o metodo jogarJogo;
        public void rolagemDeDados(){
                this.dadoAzul1 = random.nextInt(6)+1;
                this.dadoAzul2 = random.nextInt(6)+1;
                this.azulTotal = dadoAzul1 + dadoAzul2;
                this.dadoVerde1 = random.nextInt(6)+1;
                this.dadoVerde2 = random.nextInt(6)+1;
                this.verdeTotal = dadoVerde1 + dadoVerde2;
        }
        /*Faz a rodada usando a chance de ganho calculada de acordo com o tipo de dado que o 
        jogador escolher, enviando direto para o metodo de resultado para validacao de status da aposta*/
        public ResultadoDoJogo jogarJogo(Cliente cliente, double odd, double valorApostado, int dadoEscolhido){
            this.dadoEscolhido = dadoEscolhido;
            this.odd = odd;
            this.valorApostado = valorApostado;
            double metaDeVitoria = cliente.getFatorProbabilidade();

            this.vitoriaDefinida = random.nextDouble() < metaDeVitoria;

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
                    cliente.perderAposta(BigDecimal.valueOf(valorApostado).multiply(BigDecimal.valueOf(odd)));
                    resultado = "Perdeu!";
                }
            }else{
                if(verde > azul){
                    cliente.ganharAposta(BigDecimal.valueOf(valorApostado).multiply(BigDecimal.valueOf(odd)));
                    resultado = "Ganhou!";
                }else{
                    cliente.perderAposta(BigDecimal.valueOf(valorApostado).multiply(BigDecimal.valueOf(odd)));
                    resultado = "Perdeu!";
                }
            }
            return new ResultadoDoJogo(resultado,dadoAzul1,dadoAzul2,dadoVerde1,dadoVerde2,valorApostado,odd,cliente.getSaldo());
        }
    }
