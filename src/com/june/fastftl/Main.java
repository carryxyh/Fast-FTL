package com.june.fastftl;

import com.june.fastftl.impl.Render;
import com.june.fastftl.impl.Server;
import com.june.fastftl.model.Config;

/**
 * Created by june on 2017/1/22.
 */
public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
        Config config = new Config(args);
        Render render = new Render(config.getRoot());

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadManager.INSTANCE.shutDown();
            }
        }));

        System.out.printf("[D] built in " + config.getPort());
        new Server(config.getPort(), render).start();
    }
}
