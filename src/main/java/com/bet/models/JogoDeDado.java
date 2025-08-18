package com.bet.models;
import java.util.Random;
//Jogo do dado implementado com logica aleatoria de dados com base na porcentagem de ganho do Cliente
public class JogoDeDado {
    Random random = new Random();
    private int dadoVermelho1 = random.nextInt(6)+1;
    private int dadoVermelho2 = random.nextInt(6)+1;
    private int vermelhoTotal = dadoVermelho1 + dadoVermelho2;
    private int azulTotal;
    private int dadoAzul1;
    private int dadoAzul2;
    private boolean vitoria;

    //calcula se na rodada o Cliente vai ter uma chance justa ou menor aleatoriamente
   public void chanceVitoriaBasico(){
        if(random.nextDouble()<0.3){
            vitoria = true;
        }else{
            vitoria = false;
        }
   }
   public void chanceVitoriaPremium(){
        if(random.nextDouble()<0.5){
            vitoria = true;
        }else{
            vitoria = false;
        }
   }
   //Faz a rodada usando a chance de ganho calculada
    public void jogarJogo(){
        if(vitoria){
            dadoAzul1 = random.nextInt(3)+1;
            dadoAzul2 = random.nextInt(3)+1;
            azulTotal = dadoAzul1 + dadoAzul2;
        }else{
            dadoAzul1 = random.nextInt(6)+1;
            dadoAzul2 = random.nextInt(6)+1;
            azulTotal = dadoAzul1 + dadoAzul2;
        }
    }
}
