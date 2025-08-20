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
        private boolean vitoria;
        private Cliente cliente;
        private String mensagem;
        private double odd;
        private double valorApostado;

        //calcula se na rodada o Cliente vai ter uma chance justa ou menor aleatoriamente;
    public void chanceVitoria(Cliente cliente){
            if(random.nextDouble()<cliente.getFatorProbabilidade()){
                vitoria = true;
            }else{
                vitoria = false;
            }
    }
    //faz a rolagem de dados armazenando o resultado total deles e enviando de volta para o metodo jogarJogo;
    public void rolagemDeDados(int maxAzul,int maxVerde){
            dadoAzul1 = random.nextInt(maxAzul)+1;
            dadoAzul2 = random.nextInt(maxAzul)+1;
            azulTotal = dadoAzul1 + dadoAzul2;
            dadoVerde1 = random.nextInt(maxVerde)+1;
            dadoVerde2 = random.nextInt(maxVerde)+1;
            verdeTotal = dadoVerde1 + dadoVerde2;          
    }
    /*Faz a rodada usando a chance de ganho calculada de acordo com o tipo de dado que o 
    jogador escolher, enviando direto para o metodo de resultado para validacao de status da aposta*/
        public ResultadoDoJogo jogarJogo(Cliente cliente, double odd, double valorApostado, int dadoEscolhido){
            this.dadoEscolhido = dadoEscolhido;
            this.chanceVitoria(this.cliente = cliente);
            this.odd = odd;
            this.valorApostado = valorApostado;
            if(vitoria){
                if(dadoEscolhido == 0){
                    rolagemDeDados(3, 6);        
                }else{
                    rolagemDeDados(6, 3);
                }
            }else{
                rolagemDeDados(6, 6);
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
