package com.Colin.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试：");
        // 创建节点
        HeroNode1 hero1 = new HeroNode1(1,"宋江","及时雨");
        HeroNode1 hero2 = new HeroNode1(2,"卢俊义","玉麒麟");
        HeroNode1 hero3 = new HeroNode1(3,"吴用","智多星");
        HeroNode1 hero4 = new HeroNode1(4,"林冲","豹子头");

        // 创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        // 直接添加
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        // 按照顺序添加
        System.out.println("按照顺序添加：");
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);

        doubleLinkedList.list();
        // 修改测试
        HeroNode1 newHeroNode = new HeroNode1(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改之后的链表");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.deleteHero(3);
        System.out.println("删除后的情况");
        doubleLinkedList.list();
    }
}
// 创建一个双向链表
class DoubleLinkedList {
    // 初始化一个头节点，不存放具体数据
    private HeroNode1 head = new HeroNode1(0,"","");

    // 返回头节点
    public HeroNode1 getHead() {
        return head;
    }

    // 显示链表【遍历】
    public void list() {
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode1 temp = head.next;
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

    // 添加节点到双向链表的最后
    public void add(HeroNode1 heroNode) {
        // head节点不能动
        HeroNode1 temp = head;
        // 遍历链表，找到最后
        while(true) {
            if(temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 当退出while循环时，temp指向链表的最后
        // 形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 第二种添加方式，根据序号插入到指定的位置
    // 如果原始链表中包含了改序号，则添加失败，给出提示
    public void addByOrder(HeroNode1 heroNode) {
        // 通过辅助节点寻找
        // temp是在要加入位置的前一个节点
        HeroNode1 temp = head;
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
            heroNode.pre = temp;
            if(temp.next != null) {
                temp.next.pre = heroNode;
            }
        }
    }

    // 修改节点信息，根据序号来修改，no不能改
    public void update(HeroNode1 newHeroNode) {
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode1 temp = head;
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

    // 从双向链表中删除节点
    public void deleteHero(int no) {
        // 判断链表是否为空
        if(head.next == null) {
            System.out.println("当前链表为空");
            return;
        }
        HeroNode1 temp = head.next;
        boolean flag = false;
        while (true) {
            if(temp == null) { // 没找到
                break;
            }
            if(temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.pre.next = temp.next;
            // 若是最后一个节点，下面这句话有问题
//            temp.next.pre = temp.pre;
            if(temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }
        else{
            System.out.println("没有找到需要删除的节点");
        }

    }

}

// 定义HeroNode1，每个HeroNode对象就是一个节点
class HeroNode1 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode1 next; // 指向下一个节点，默认为null
    public HeroNode1 pre; // 指向前一个节点，默认为null

    // 构造器
    public HeroNode1(int no, String name, String nickName) {
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