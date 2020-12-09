package pro.bolshakov.edu.concurrency.async;

import pro.bolshakov.edu.concurrency.ThreadUtils;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) {

        final boolean firstError = false;
        final boolean secondError = false;

        System.out.println("Creating future");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            public String get() {
                ThreadUtils.wait(1);
                if(firstError){
                    throw new RuntimeException("First Error");
                }
                System.out.println("Execution task");
                return "GUID -> " + UUID.randomUUID().toString();
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                if(secondError){
                    throw new RuntimeException("First Error");
                }
                System.out.println("Executed after");
                System.out.println("Result -> " + s);
                return s + " updated";
            }
        }).handle(new BiFunction<String, Throwable, String>() {
            @Override
            public String apply(String s, Throwable throwable) {
                System.out.println("handled");
                if(throwable != null){
                    System.out.println("ERROR");
                    return "hz";
                }
                return s;
            }
        });

        System.out.println("Finished");

        try {
            String result = future.get();
            System.out.println("Final result ->" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }

}
