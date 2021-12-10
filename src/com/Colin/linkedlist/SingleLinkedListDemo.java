package com.Colin.linkedlist;

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
}
