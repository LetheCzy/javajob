package czy.site.job;

import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {
    // 返回异步任务的执行结果
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 10; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "_"
                        + i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
}
