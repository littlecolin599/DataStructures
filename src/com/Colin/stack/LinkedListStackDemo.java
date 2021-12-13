package com.Colin.stack;
import java.util.Scanner;
import java.util.Stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        // 测试一把
        // 先创建一个ArrayStack对象表示栈
        SingleLinkedList stack = new SingleLinkedList();
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:添加数据");
            System.out.println("pop:取出数据");
            System.out.println("输入选择：");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        System.out.println("程序退出~");
    }
}
class LinkedListStack {
    private int value;
    private LinkedListStack next;
    public LinkedListStack(int value) {
        this.value = value;
    }
    public LinkedListStack getNext() {
        return this.next;
    }
    public void setNext(LinkedListStack stack) {
        this.next = stack;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
class SingleLinkedList {
    // 初始化一个头节点，不存放具体数据
    private LinkedListStack head = new LinkedListStack(0);

    // 返回头节点
    public LinkedListStack getHead() {
        return head;
    }
    // 显示栈show
    public void show() {
        LinkedListStack temp = head.getNext();
        if(temp == null) {
            System.out.println("空栈，没有数据");
            return;
        }
        int i = 0;
        while(temp != null) {
            System.out.printf("Stack[%d] : %d\n",i++,temp.getValue());
            temp = temp.getNext();
        }
    }
    // 添加数据push
    public void push(int value) {
        LinkedListStack add = new LinkedListStack(value);
        LinkedListStack temp = head.getNext();
        head.setNext(add);
        add.setNext(temp);
    }
    // 取出数据pop
    public int pop() {
        LinkedListStack temp = head.getNext();
        if(temp == null) {
            throw new RuntimeException("栈空，无法取出");
        }
        if(temp.getNext() == null) {
            head.setNext(null);
        }
        else {
            head.setNext(temp.getNext());
        }
        return temp.getValue();
    }







}
