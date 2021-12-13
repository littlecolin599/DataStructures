package com.Colin.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        // 创建单向链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        //添加
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        // 按照顺序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        System.out.println("添加数据后的链表：");
        singleLinkedList.list();

        // 逆序打印链表
        System.out.println("逆序打印链表");
        singleLinkedList.reversePrint(singleLinkedList.getHead());
        singleLinkedList.list();


        // 反转链表
        System.out.println("反转链表：");
        singleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        // 修改节点
        HeroNode newHeroNode = new HeroNode(2,"小卢","玉麒麟~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改数据后的链表：");
        singleLinkedList.list();

        // 删除节点
        singleLinkedList.deleteHero(1);
        System.out.println("删除数据后的链表：");
        singleLinkedList.list();

        // 删除节点
        singleLinkedList.deleteHero(4);
        System.out.println("删除数据后的链表：");
        singleLinkedList.list();

        System.out.println("有效的节点数量："+singleLinkedList.getLength(singleLinkedList.getHead()));

        // 查找倒数第k个节点
        System.out.println("倒数第2个节点：\n"+ singleLinkedList.findLastKNode(singleLinkedList.getHead(),2));






    }
}

// 定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; // 指向下一个节点

    // 构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "HeroNode[no=" + no + ", name = " + name + ", nickName = " + nickName+"]";
    }
}
// 创建SingleLinkedList
class SingleLinkedList {
    // 初始化一个头节点，不存放具体数据
    private HeroNode head = new HeroNode(0,"","");

    // 返回头节点
    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单向链表
    // 当不考虑标号顺序时
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        // head节点不能动
        HeroNode temp = head;
        // 遍历链表，找到最后
        while(true) {
            if(temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 当退出while循环时，temp指向链表的最后
        temp.next = heroNode;
    }

    // 第二种添加方式，根据序号插入到指定的位置
    // 如果原始链表中包含了改序号，则添加失败，给出提示
    public void addByOrder(HeroNode heroNode) {
        // 通过辅助节点寻找
        // temp是在要加入位置的前一个节点
        HeroNode temp = head;
        boolean flag = false; // 判断原始链表中序号是否存在
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no > heroNode.no) { // 位置找到
                break;
            }
            else if(temp.next.no == heroNode.no) { // 序号已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag
        if(flag) {
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n",heroNode.no);
        }
        else {
            // 插入到链表中
            heroNode.next = temp.next;
            temp.next= heroNode;
        }
    }

    // 显示链表【遍历】
    public void list() {
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while(true) {
            if(temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将temp后移 ！！！
            temp = temp.next;
        }
    }
    // 修改节点信息，根据序号来修改，no不能改
    public void update(HeroNode newHeroNode) {
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if(temp == null) {
                break; // 遍历结束
            }
            if(temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag判断是否找到需要修改的节点
        if(flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }
        else {
            System.out.printf("没有找到编号为%d的英雄\n",newHeroNode.no);
        }
    }

    // 删除节点
    public void deleteHero(int no) {
        // 判断链表是否为空
        if(head.next == null) {
            System.out.println("当前链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if(temp.next == null) { // 没找到
                break;
            }
            if(temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.next = temp.next.next;
        }
        else{
            System.out.println("没有找到需要删除的节点");
        }

    }

    // 获取单链表中的节点的个数（不计算头节点）
    public static int getLength(HeroNode head) {
        if(head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while(cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    // 查找单链表中的倒数第k个节点
    // 1. 编写一个方法，接受head节点，同时接受一个index
    // 2. index 表示是倒数第index个节点
    // 3. 先把链表从头到尾遍历，得到链表的总长度getLength
    // 4. 得到size后，我们从链表的第一个开始遍历(size - index)个，就可以得到
    public static HeroNode findLastKNode(HeroNode head, int index) {
        if(head.next == null) {
            return  null; // 没有找到
        }
        // 第一次遍历得到链表的长度
        int size = getLength(head);
        // 第二次遍历 size-index的位置
        if(index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for(int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }
    // 将单链表反转
    public static HeroNode reverseList(HeroNode head) {
        if(head.next == null || head.next.next == null) {
            return head;
        }
        // 定义辅助指针
        HeroNode cur = head.next;
        HeroNode next = null; // 当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0," "," ");

        while(cur != null) {
            next = cur.next;
            cur.next = reverseHead.next; // 将cur的下一个节点指向新链表的最前端
            reverseHead.next = cur; // 将cur连接到新的链表上
            cur = next; // 让cur后移
        }
        // 将head.next 指向reverseHead.next
        head.next = reverseHead.next;
        return reverseHead;
    }

    // 逆序打印单链表
    public static void reversePrint(HeroNode head) {
        if(head.next == null) {
            return; // 空链表
        }
        // 创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;

        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 将栈中的节点进行打印
        while(stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    // 合并两个有序链表
    public static HeroNode mergeLinkedList(HeroNode head1, HeroNode head2) {
        if(head1.next == null) {
            return head2;
        }
        else if(head2.next == null) {
            return head1;
        }
        HeroNode mergeList = new HeroNode(0,"","");
        

        return mergeList;
    }

}

