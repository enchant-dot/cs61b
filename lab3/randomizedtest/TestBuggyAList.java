package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeRemove() {
        AListNoResizing<Integer> correctList =  new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();

        correctList.addLast(4);
        buggyList.addLast(4);

        correctList.addLast(5);
        buggyList.addLast(5);

        correctList.addLast(6);
        buggyList.addLast(6);

        assertEquals(correctList.removeLast(), buggyList.removeLast());
        assertEquals(correctList.removeLast(), buggyList.removeLast());
        assertEquals(correctList.removeLast(), buggyList.removeLast());

    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correctList =  new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();
        int N = 1000;
        for (int i = 0; i < N; i++) {
            int opType = StdRandom.uniform(0, 3);
            switch (opType) {
                case 0:
                    int randVal = StdRandom.uniform(1000);
                    correctList.addLast(randVal);
                    buggyList.addLast(randVal);
                    assertEquals(correctList.size(), buggyList.size());
                    break;
                case 1:
                    if (correctList.size() > 0) {
                        int correctVal = correctList.removeLast();
                        int buggyVal = buggyList.removeLast();

                        assertEquals(correctVal, buggyVal);
                        assertEquals(correctList.size(), buggyList.size());
                    }
                    break;
                case 2:
                    if (correctList.size() > 0) {
                        int randIndex = StdRandom.uniform(0, correctList.size());
                        int correctVal = correctList.get(randIndex);
                        int buggyVal = buggyList.get(randIndex);
                        // 校验指定索引的值是否一致
                        assertEquals(correctVal, buggyVal);
                    }
                    break;
            }
        }
    }


}
