package b4.obj;

import lombok.Data;
import lombok.extern.java.Log;

import java.util.concurrent.TimeUnit;

@Log
@Data
public class TimeKeeper implements Runnable {
    private int maxAllowedSeconds;

    public TimeKeeper(int maxAllowedSeconds) {
        this.maxAllowedSeconds = maxAllowedSeconds;
    }

    @Override
    public void run() {
        int secondsPassed = 0;
        while (secondsPassed < maxAllowedSeconds) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException exception) {
                log.warning("Error at putting timekeeper daemon to sleep for 5 s.");
            }
            secondsPassed += 2;
            log.info("Time left to play: " + (maxAllowedSeconds - secondsPassed));
        }
        log.info("Time's up! Game over!");
        System.exit(0);
    }
}
