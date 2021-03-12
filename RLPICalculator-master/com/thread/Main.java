package com.thread;

import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // FIXADO 4 WORKERS
        Integer work = 4;
        Master master = new Master(new Tarefa(), work);
        for(int i=1; i<=work; i++) { //enviar os pontos para os determinados workers
            master.submit(100000000);
        }
        master.execute(); //executando a tarefa

        Map<String, Object> resultMap = master.getResultMap();

        Double resul = Double.valueOf(0);

        while(true) {
            Set<String> keys = resultMap.keySet(); // são 10000 pontos então sao 10000 indices
            String key = null;
            for(String k : keys) {
                key = k; // colocando as chaves q estao sendo pega do resultmap, e colocando na key
                break;
            }
            Double i = null;
            if(key != null)
                i = (Double) resultMap.get(key);

            if(i != null)
                resul = i; //resultado final

            if(key != null)
                resultMap.remove(key);//removendo os itens que ja foram computados

            if(master.isComplete() && resultMap.size()==0)
                break;

        }
        //System.out.println(resul);


    }
}  