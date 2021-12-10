package com.Colin.queue;

import java.util.Scanner;

public class circleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArray arrayQueue = new CircleArray(4); // 队列有效最大数据量为3
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key)  {
                case 's' :
                    arrayQueue.showQueue();
                    break;
                case 'a' :
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'e':
                    scanner.close();
                    loop = false;
                    break;


            }
        }
        System.out.println("程序退出");

    }
}

class CircleArray {
    private int maxSize; // 队列的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr;

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull() { return (rear + 1) % maxSize == front; }
    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        if(isFull()) {
            System.out.println("队列满，不能添加");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        if(isEmpty()) {
            throw new RuntimeException("队列空，不能获取数据.");
        }
        // front指向队列的第一个元素
        // 1. 先把front对应的值保留到一个临时变量
        // 2. 将front后移，考虑取模
        // 3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }

    // 显示队列的所有数据
    public void showQueue() {
        if(isEmpty()) {
            System.out.println("队列为空");
            return;
        }

        // 从front开始遍历
        for(int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize, arr[i%maxSize]);
        }
    }

    // 获取队列中有效数据的大小
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示头部元素
    public int headQueue() {
        if(isEmpty()) {
            throw new RuntimeException("");
        }
        return arr[front];
    }

}