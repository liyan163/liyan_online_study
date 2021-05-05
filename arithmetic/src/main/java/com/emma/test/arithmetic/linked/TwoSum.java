package com.emma.test.arithmetic.linked;

import javax.xml.stream.events.StartDocument;

/**
 * @author Emma
 * @create 2021-04-27 22:39
 * @description 两个链表中的数字相加后的新的链表
 * eg:
 * A: 2 3 6
 * B: 3 5 4
 * 结果： 5 8 0 1  即 632 + 453 = 1085
 */

public class TwoSum {

}


/**
 * 单链表实体类
 */
class StudentNode {
    public int age;
    public String name;
    public StudentNode next;

    public StudentNode(int age, String name) {
        this.age = age;
        this.name = name;
    }
}

class SingleLinked {
    //定义带头链表
    StudentNode head = new StudentNode(0, "");

    //将链表实体添加到最后一位
    public void add(StudentNode studentNode) {
        StudentNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = studentNode;
    }

    //按照实体类中age的大小插入链表实体
    public void addForSort(StudentNode studentNode) {
        StudentNode temp = head;
        if (temp.next == null) {
            temp.next = studentNode;
        }
        //状态位表示要进行最后的插入操作
        boolean flag = true;
        //找到最后或者temp的next大于要插入的数据
        while (studentNode.age > temp.next.age) {
            temp = temp.next;
            if (temp.next == null) {
                temp.next = studentNode;
                flag = false;
            }
        }
        if (flag) {
            studentNode.next = temp.next;
            temp.next = studentNode;
        }
    }

    //遍历单链表
    public StudentNode list() {
        StudentNode temp = head;
        if (head == null) {
            System.out.println("该链表为空");
            return null;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        return head;
    }


    //删除链表中的某一位
    public void del(StudentNode studentNode) {
        //找到指定位置
        StudentNode temp = head;
        //头节点是null
        if (temp == null) {
            System.out.println("该链表为空，不可删除");
            return;
        }
        //要删掉的是头节点
        if (temp.age == studentNode.age){
            head = head.next;
            return;
        }
        if (temp.next == null) {
            System.out.println("链表中没有要删除的节点");
            return;
        }
        while (true) {
            if (temp.next == null) {
                System.out.println("链表中没有要删除的节点");
                return;
            }
            if (temp.next.age == studentNode.age){
                break;
            }
            temp = temp.next;
        }
        //找到了要删除的链表是temp.next
            temp.next  = temp.next.next;

    }

    // 链表排序
    public void sortLinked(){
        StudentNode temp = null;
        
    }


}

