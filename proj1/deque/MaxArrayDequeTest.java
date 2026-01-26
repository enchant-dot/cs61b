package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    // 1. 定义一个简单的比较器（比如比整数大小）
    // 这种写法是 Java 8 之前的“老派”写法，CS61B 有时会要求这样写
    public static class IntComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    // 或者定义一个比字符串长度的比较器
    public static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void testMax() {
        // 创建一个裁判
        Comparator<Integer> c = new IntComparator();
        // 创建队列，带上裁判
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(c);

        mad.addFirst(10);
        mad.addLast(5);
        mad.addLast(20); // [10, 5, 20]

        // 测试默认裁判
        assertEquals((Integer) 20, mad.max());

        // 测试指定裁判（比如反着比，找最小的）
        // 这里的 Lambda 写法 (a, b) -> b - a 也是可以的
        assertEquals((Integer) 5, mad.max((a, b) -> b - a));
    }
}