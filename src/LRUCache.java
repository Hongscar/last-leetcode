import java.util.HashMap;
import java.util.Map;




public class LRUCache {
    class DLinkedNode {
        private int key;
        private int value;
        private DLinkedNode prev;
        private DLinkedNode next;
        private DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkedNode> map;
    private int capacity;
    private int size;
    private DLinkedNode head;
    private DLinkedNode tail;

    LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode(-1, -1); // dummy head node
        tail = new DLinkedNode(-1, -1); // dummy tail node
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        DLinkedNode resNode = map.get(key);
        moveToHead(resNode);
        return resNode.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DLinkedNode oldNode = map.get(key);
            oldNode.value = value;
            map.put(key, oldNode);
            moveToHead(oldNode);
            return;
        }
        if (capacity == size) {
            size--;
            DLinkedNode dLinkedNode = deleteTail();
            map.remove(dLinkedNode.key);
        }
        size++;
        DLinkedNode newNode = new DLinkedNode(key, value);
        map.put(key, newNode);
        addToHead(newNode);
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private DLinkedNode deleteTail() {
        DLinkedNode currentTail = tail.prev;
        removeNode(currentTail);
        return currentTail;
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */