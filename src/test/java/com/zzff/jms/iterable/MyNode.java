package com.zzff.jms.iterable;

public class MyNode {
    public static void main(String[] args) {
        Node node=new Node("tom");
        Node node2=new Node("jack");
        Node node3=new Node("test");
        node.next=node2;
        node2.next=node3;
        node3.pre=node2;
        node2.pre=node;

        Node first=node;
        Node last=node3;

        while (true){
            if(first==null){
                 break;
            }else{
                System.out.println(first);
                first=first.next;
            }
        }
    }
}
class Node{
    Object item;
    Node next;
    Node pre;

    public Node(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "CopyNode{" +
                "item=" + item +
                '}';
    }
}