package pro.bolshakov.edu.concurrency.async;

import pro.bolshakov.edu.concurrency.ThreadUtils;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) {

        System.out.println("Creating future");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            public String get() {
                ThreadUtils.wait(1);
                System.out.println("Execution task");
                return "GUID -> " + UUID.randomUUID().toString();
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                System.out.println("Executed after");
                System.out.println("Result -> " + s);
                return s + " updated";
            }
        });
        System.out.println("Finished");


    }

}
