package travel2.utils;

public class CPUDefectTask implements Runnable {
    @Override
    public void run() {
        int i = 1;
        while (i > 0) {
            //System.out.println("This is a CPU defect test thread!");
            i = i + 2 - 2;
        }
    }
}
