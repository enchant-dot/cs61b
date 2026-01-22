package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import net.sf.saxon.expr.instruct.Debugger;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
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

        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operation = StdRandom.uniform(0, 3);

            if (operation == 0) {
                int randval = StdRandom.uniform(0, 100);
                correctList.addLast(randval);
                buggyList.addLast(randval);
            } else if (operation == 1) {
                if (correctList.size() == 0) {
                    continue;
                }
                int lastcorrect = correctList.getLast();
                int lastbuggy = correctList.getLast();
                assertEquals(lastbuggy, lastcorrect);
            } else if (operation == 2) {
                if (buggyList.size() == 0) {
                    continue;
                }
                int removcorrect = correctList.removeLast();
                int removbuggy = buggyList.removeLast();
                assertEquals(removbuggy, removcorrect);
            }
        }
    }
//    @Test
//    public void randomizedTest() {
//        AListNoResizing<Integer> L = new AListNoResizing<>();
//
//        int N = 500;
//        for (int i = 0; i < N; i += 1) {
//            int operationNumber = StdRandom.uniform(0, 2);
//            if (operationNumber == 0) {
//                // addLast
//                int randVal = StdRandom.uniform(0, 100);
//                L.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
//            } else if (operationNumber == 1) {
//                // size
//                int size = L.size();
//                System.out.println("size: " + size);
//            }
//        }
//    }


}
