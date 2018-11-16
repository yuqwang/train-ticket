package seat.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CPUDefect {
    public static void injectCPUDefect() {
        int THREAD_POOL_SIZE = 5;
        ThreadPoolExecutor threadPoolExecutor =  new  ThreadPoolExecutor(THREAD_POOL_SIZE, THREAD_POOL_SIZE,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(2 * THREAD_POOL_SIZE));

        for (int i= 0; i < THREAD_POOL_SIZE; i++) {
            CPUDefectTask task = new CPUDefectTask();
            threadPoolExecutor.execute(task);
        }
    }
}
