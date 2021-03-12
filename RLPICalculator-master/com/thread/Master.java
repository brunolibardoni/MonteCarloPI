package com.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
    //fila da tarefa
    protected Queue<Object> workQueue =
            new ConcurrentLinkedQueue<Object>();

    //fila dos processos dos workers
    protected Map<String, Thread> threadMap =
            new HashMap<String, Thread>();

    //Processamento do resultado das tasks
    protected Map<String, Object> resultMap =
            new ConcurrentHashMap<String, Object>();

    //Cada worker recebera 1 thread
    //Parametro o worker e a quantidades de workes q irao "trabalhar"
    public Master(Worker worker, int countWorker) {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for(int i=0; i<countWorker; i++) {
            threadMap.put(Integer.toString(i),
                    new Thread(worker, Integer.toString(i)));
        }
    }

    //VERIFICAÇÃO SE TODAS AS TASKS ESTÃO INTRODUZIDAS
    public boolean isComplete() {
        for(Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if(entry.getValue().getState() != Thread.State.TERMINATED)

                return false;
        }
        return true;
    }

    //Enviar uma task
    public void submit(Object job) {
        workQueue.add(job);
    }

    //retornar os resultados das tasks dos workers
    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    //executar todos os processos dos workers
    public void execute() {
        for(Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            entry.getValue().start();
        }
    }
}  