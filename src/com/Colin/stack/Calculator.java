package com.Colin.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        // 创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        while(true) {
            // 一次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            if(operStack.isOper(ch)) {
                // 判断当前符号栈是否为空
                if(!operStack.isEmpty()) {
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算的结果压入数栈
                        numStack.push(res);
                    }
                    else {
                        // 如果当前操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }
                else {
                    // 如果为空，直接入栈
                    operStack.push(ch);

                }
            }
            else {
                // 如果是数，直接入数栈
//                numStack.push(ch - 48);
                // 当处理多位数是，不能发现是一个数就立即入栈，可能是一个多位数
                // 在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                // 需要定义一个字符串变量
                keepNum += ch;
                if(index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                }
                else {
                    // 判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，就入栈
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
                        // 如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        // 重要,keepNum 清空
                        keepNum = "";
                    }
                }

            }
            // 让index + 1，并判断是否扫描到expression最后
            index++;
            if(index >= expression.length()) {
                break;
            }
        }
        // 当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while(true) {
            // 如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if(operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s = %d",expression,numStack.pop());
    }
}

// 创建栈
// 定义一个类ArrayStack，表示栈，需要扩展功能
class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈
    private int top = -1; //表示栈顶
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    // 判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }
    // 栈空
    public  boolean isEmpty() {
        return top == -1;
    }
    // 入栈
    public void push(int value) {
        if(isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    // 出栈
    public int pop() {
        // 判断是否为空
        if(isEmpty()) {
            // 抛出异常
            throw new RuntimeException("占空，没有数据");
        }
        int value = stack[top];
        top--;
        return  value;
    }

    // 增加一个方法，可以返回当前栈顶的值，但是不是pop
    public int peek() {
        return stack[top];
    }
    // 遍历栈,遍历时，需要从栈顶开始显示数据
    public void list() {
        if(isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for(int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
    // 返回运算符的优先级，优先级由自己确定，优先级使用数字表示
    // 数字越大，优先级越高
    public int priority(int oper) {
        if(oper == '*' || oper == '/') {
            return 1;
        }
        else if(oper == '+' || oper == '-')  {
            return 0;
        }
        else {
            return -1;
        }
    }
    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}

