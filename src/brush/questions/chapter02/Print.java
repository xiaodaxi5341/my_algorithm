package brush.questions.chapter02;

import java.util.HashMap;

/**
 * @program: my_algorithm
 * @description 已知一个消息流会不断地吐出整数1~N，但不一定按照顺序依次吐出，如果上次打印的序号为i， 那么当i+1出现时
 * 请打印i+1及其之后接收过的并且连续的所有数，直到1~N全部接收并打印完，请设计这种接收并打印的结构
 * @author: 34371
 * @create: 2022-05-11 21:07
 **/
public class Print {

    static class Node{
        String content;
        Node next;

        public Node( String content) {
            this.content = content;
        }
    }

    static class MessageBox{

        HashMap<Integer,Node> head = new HashMap<>();
        HashMap<Integer,Node> tail = new HashMap<>();
        int waitNum=1;

        public void receive(int id,String content){
            Node newNode = new Node(content);
            head.put(id,newNode);
            tail.put(id,newNode);
            if (head.get(id-1)!=null){
                head.get(id-1).next = newNode;
                tail.remove(id);
                head.remove(id-1);
            }
            if (tail.get(id+1)!=null){
                newNode.next = tail.get(id+1);
                head.remove(id);
                tail.remove(id+1);
            }

            if (id == waitNum){
                Node cur = newNode;
                while (cur!=null){
                    head.remove(waitNum);
                    tail.remove(waitNum);
                    System.out.print(cur.content+"\t");
                    cur = cur.next;
                    waitNum++;
                }
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        MessageBox box = new MessageBox();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1
        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10
        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11
    }

}
