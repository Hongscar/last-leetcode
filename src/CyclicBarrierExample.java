import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("所有线程到达屏障点，继续执行"));

        // 创建并启动第一个线程  
        new Thread(() -> {
            try {
                System.out.println("线程1正在执行任务");
                Thread.sleep(3000); // 假设任务需要3秒钟  
                System.out.println("线程1到达屏障点");
                barrier.await(); // 等待其他线程  
                System.out.println("线程1继续执行");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // 创建并启动第二个线程  
        new Thread(() -> {
            try {
                System.out.println("线程2正在执行任务");
                Thread.sleep(5000); // 假设任务需要5秒钟  
                System.out.println("线程2到达屏障点");
                barrier.await(); // 等待其他线程  
                System.out.println("线程2继续执行");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // 创建并启动第三个线程  
        new Thread(() -> {
            try {
                System.out.println("线程3正在执行任务");
                Thread.sleep(4000); // 假设任务需要4秒钟  
                System.out.println("线程3到达屏障点");
                barrier.await(); // 等待其他线程  
                System.out.println("线程3继续执行");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}