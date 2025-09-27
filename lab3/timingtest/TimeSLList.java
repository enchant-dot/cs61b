package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int[] NsArray = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        int M = 10000; // 每次测试调用 getLast 的次数

        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        for (int N : NsArray) {
            // 1. 创建 SLList 并添加 N 个元素（构建阶段，不计时）
            SLList<Integer> slist = new SLList<>();
            for (int i = 0; i < N; i++) {
                slist.addLast(i);
            }

            // 2. 启动计时器
            Stopwatch sw = new Stopwatch();

            // 3. 执行 M 次 getLast
            for (int i = 0; i < M; i++) {
                slist.getLast();
            }

            // 4. 记录时间
            double timeInSeconds = sw.elapsedTime();

            Ns.addLast(N);
            times.addLast(timeInSeconds);
            opCounts.addLast(M);
        }

        // 5. 打印表格
        printTimingTable(Ns, times, opCounts);
    }
}
