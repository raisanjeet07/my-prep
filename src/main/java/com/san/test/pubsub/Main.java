package com.san.test.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        String topic = "order";;
//        OrderPublisher publisher = new OrderPublisher();
//        publisher.createOrderAndSend(topic);
//        new OrderSubcriber(topic);
//        publisher.createOrderAndSend(topic);
//        new OrderSubcriber(topic);
//        publisher.createOrderAndSend(topic);
//        topic = "topic2";
//        publisher.createOrderAndSend(topic);
//        new RandomSubs(topic);
//        new RandomSubs(topic);
//        publisher.createOrderAndSend(topic);
//        Thread.sleep(100);
//        System.out.println("I am done");
//        ForkJoinPool fjp = new ForkJoinPool();
//        System.out.println(new RecursiveActionCustom("asdvadfza").fork().join());
//        asdvadfza
//        ASDVADFZA
//        fjp.invoke(new RecursiveActionCustom("asdfghjkl"));
//        Thread.sleep(1000);

        List<Topic3> topics = new ArrayList<>();
        topics.add(new Topic3("zdsa"));
        Topic2 t2 = new Topic2("");
        System.out.println(t2);
        updateList(topics);

    }

    private static void updateList(List<? extends Object> topics){
        List<Subscribers> t = (List<Subscribers>) topics;
        Subscribers t2 = (Subscribers) new Topic("");
        for(Subscribers topic2 : t){
            System.out.println(topic2);
        }
    }

    static class  RecursiveActionCustom extends RecursiveTask<String>{
        String workload = "";
        int THRESHOLD = 1;

        public RecursiveActionCustom(String task){
            this.workload = task;
        }

        private List<RecursiveActionCustom> createSubtasks() {
            List<RecursiveActionCustom> subtasks = new ArrayList<>();

            String partOne = workload.substring(0, workload.length() / 2);
            String partTwo = workload.substring(workload.length() / 2, workload.length());

            subtasks.add(new RecursiveActionCustom(partOne));
            subtasks.add(new RecursiveActionCustom(partTwo));

            return subtasks;
        }

        private String processing(String work) {
            String result = work.toUpperCase();
            System.out.println("This result - (" + result + ") - was processed by "
                    + Thread.currentThread().getName());
            return work.toUpperCase();
        }


        @Override
        protected String compute() {
            if (workload.length() > THRESHOLD) {
                Collection<RecursiveActionCustom> tasks = ForkJoinTask.invokeAll(createSubtasks());
                return tasks.stream().map(task -> task.join()).collect(Collectors.joining(""));
            } else {
                return processing(workload);
            }
        }
    }
}
