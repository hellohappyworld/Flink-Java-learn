import com.scallion.utils.TimeUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * created by gaowj.
 * created on 2021-05-14.
 * function:
 * origin ->
 */
public class TimerDemo {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(TimeUtil.getTimestampToDate(System.currentTimeMillis()));
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 2000);
    }
}
