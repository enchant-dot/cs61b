package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomizedTest {

    @Test
    public void randomizedRun() {
        // 你的实现
        ArrayDeque<Integer> myDeque = new ArrayDeque<>();
        // Java 官方的标准答案 (用来做对比)
        java.util.ArrayDeque<Integer> correctDeque = new java.util.ArrayDeque<>();

        int N = 100000; //以此为量级，跑10万次操作
        for (int i = 0; i < N; i++) {
            // 随机生成 0 到 3 的操作指令
            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                myDeque.addFirst(randVal);
                correctDeque.addFirst(randVal);
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                myDeque.addLast(randVal);
                correctDeque.addLast(randVal);
            } else if (operationNumber == 2) {
                // removeFirst (注意先检查非空)
                if (!myDeque.isEmpty() && !correctDeque.isEmpty()) {
                    Integer expected = correctDeque.removeFirst();
                    Integer actual = myDeque.removeFirst();
                    // 对比结果，不一样就报错
                    assertEquals("removeFirst 结果不一样！", expected, actual);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (!myDeque.isEmpty() && !correctDeque.isEmpty()) {
                    Integer expected = correctDeque.removeLast();
                    Integer actual = myDeque.removeLast();
                    assertEquals("removeLast 结果不一样！", expected, actual);
                }
            }

            // 每次操作完，检查 size 是否对得上
            assertEquals("Size 不一致！", correctDeque.size(), myDeque.size());
        }
        System.out.println("恭喜！跑完了 10 万次随机测试，没有发现 Bug！");
    }
}