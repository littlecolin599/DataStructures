package com.Colin.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        // 测试一把
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        // 测试节点出环
        circleSingleLinkedList.countBoy(1,2,5);
    }
}
// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first节点，当前无编号
    private Boy first = new Boy(-1);
    // 添加小孩节点，构成一个环形的链表
    public void addBoy(int nums) {
        // 对num进行数据校验
        if(nums < 2) {
            System.out.println("num数值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针
        // 使用for循环创建环形链表
        for(int i = 1; i <= nums; i++) {
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个节点
            if(i == 1) {
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first; // 让curBoy指向第一个节点
            }
            else {
                curBoy.setNext(boy); // !!!
                boy.setNext(first);
                curBoy = boy;
            }

        }
    }
    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if(first == null) {
            System.out.println("环形链表为空~");
        }
        Boy curBoy = first;
        while(true) {
            System.out.printf("小孩的编号%d \n",curBoy.getNo());
            if(curBoy.getNext() ==first) {
                break;
            }
            curBoy = curBoy.getNext(); // 后移
        }
    }

    // 根据用户输入，计算出节点出环的顺序

    /**
     *
     * @param startNum 表示从第几个小孩开始数数
     * @param countNum 表示每次数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNum, int countNum, int nums) {
        // 先对数据进行校验
        if(first == null || startNum < 1 || startNum > nums) {
            System.out.println("参数输入有误~");
            return;
        }
        // 创建辅助指针,并让helper指向最后的节点
        Boy helper = first;
        while(true) {
            if(helper.getNext() == first) {
                // helper指向最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先让first和helper移动k-1次
        for(int j = 0;j < startNum - 1; j++) {
            first  = first.getNext();
            helper = helper.getNext();
        }
        //
        //
        while(true) {
            if(helper == first) {
                // 圈中只有一个节点
                break;
            }
            // 让helper和first同时移动countNum - 1
            for(int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n",first.getNo());
            // 这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("留在圈中的小孩编号%d",first.getNo());

    }
}

// 创建一个Boy类，表示一个节点

class Boy {
    private int no; // 编号
    private Boy next; // 指向下一个节点，默认Null
    public Boy(int no) {
        this.no = no;
    }
    public int getNo(){
        return no;
    }
    public void setNext(Boy boy) {
        this.next = boy;
    }
    public Boy getNext() {
        return this.next;
    }
}
