package example.nodeexample;

public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    private boolean isListEmpty() {
        if (size <= 0) {
            return true;
        }
        return false;
    }

    private void init(Node newNode){
        // Node 생성
        head = newNode;
        tail = newNode;

        head.leftNode = newNode;
        head.rightNode = newNode;
    }


    // first
    public void firstInsertNode(String data) {
        Node newNode = new Node(data); // 새로운 노드 생성
        Node temp;
        if (isListEmpty()) {
            init(newNode);
        } else {
            temp = head;
            newNode.leftNode = newNode; // 왼쪽은 자기자신
            newNode.rightNode = temp; // 오른쪽 head
            head = newNode; // head 갱신
        }
    }

    public void lastInsertNode(String data){
        Node newNode = new Node(data);
        Node temp;
        if(isListEmpty()){
            init(newNode);
        }else{
            temp = tail;
            
        }
    }


}
