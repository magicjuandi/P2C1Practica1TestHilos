package services.impl;

import services.ThreadsService;

import java.util.concurrent.CompletableFuture;

public class ThreadsServiceimpl implements ThreadsService {

    @Override
    public void slowProcess() {
        System.out.println("Getting Data...");
        sleeper(5000);
        System.out.println("Data Ready");
    }
    public void sleeper(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
