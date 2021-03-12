package com.thread;

import java.util.Map;

public class Tarefa extends Worker {
    //Calculo do pi monte carlo
    @Override
    public Object handle(Object input) {

        int acertos = 0;
        int pontos = (Integer)input;

        for(int i = 0; i < pontos; i++){
            double x = Math.random();
            double y = Math.random();

            if(Math.sqrt((x * x + y * y)) <= 1.0){
                acertos++;
            }
        }
        System.out.println("Total de pontos dentro do círculo: "+acertos);

        return (double) (4.0 * acertos / pontos);
    }
}
