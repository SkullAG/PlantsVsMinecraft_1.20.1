package net.skullag;

import java.util.Timer;
import java.util.TimerTask;

public class TimerHelper {
    public static TimerTask timerTaskWrapper(Runnable r) {
        return new TimerTask() {
            @Override
            public void run() {
                r.run();
            }
        };
    }

    public static void schedule (Runnable r, long delay) {
        new Timer().schedule(timerTaskWrapper(r), delay);
    }
}
