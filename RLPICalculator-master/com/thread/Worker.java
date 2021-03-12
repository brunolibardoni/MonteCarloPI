package com.thread;

import java.util.Map;
import java.util.Queue;

public class Worker implements Runnable {
    //FILA DA TASK
    protected Queue<Object> workQueue;

    //CONJUNTO DE RESULTADO PROCESSADOS PELA SUBTASK
    protected Map<String, Object> resultMap;

    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    //Pegando a entrada dos pontos que sera fornecida
    public Object handle(Object input) {
        return input;
    }

    @Override
    public void run() {
        while(true) {
            //PEGANDO AS TASKS
            Object input = workQueue.poll();
            if(input == null) break;

            //LIDANDO COM AS TASKS
            Object result = handle(input);

            //ARMAZENANDO O RESULTADO DO PROCESSAMENTO (problema)
            resultMap.put(Integer.toString(input.hashCode()), result);

        }
    }
}
