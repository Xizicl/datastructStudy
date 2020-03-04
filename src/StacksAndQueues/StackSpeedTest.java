package StacksAndQueues;

import StacksAndQueues.Stacks.ArrayStack;
import StacksAndQueues.Stacks.LinkedListStack;
import StacksAndQueues.Stacks.Stack;

import java.util.Random;

public class StackSpeedTest {
    private static double testStack(Stack<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.pop();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
    public static void main(String[] args) {

        int opCount = 10000000;


        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack,opCount);
        System.out.println("arrayStack , time: "+time1+" s");


        LinkedListStack<Integer> listStack = new LinkedListStack<>();
        double time2 = testStack(listStack,opCount);
        System.out.println("listStack , time: "+time2+" s");

    }
}
