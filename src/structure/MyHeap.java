package structure;

/**
 * 简化版大顶堆 only Integer
 */
public class MyHeap {

    public int[] values;

    public int capacity;

    public int size;

    public MyHeap(int capacity) {
        values = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public int parent(int node) {
        return (node - 1) / 2;
    }

    public int left(int node) {
        return 2 * node + 1;
    }

    public int right(int node) {
        return 2 * node + 2;
    }

    public Integer peek() {
        return size > 0 ? values[0] : null;
    }

    public Integer pop() {
        if (size == 0) {
            return null;
        }
        int root = values[0];
        swap(0, --size);
        sink(0);
        return root;
    }

    public void push(int i) {
        values[size++] = i;
        swim(size - 1);
    }

    public void swap(int i, int j) {
        int tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }


    // 下沉
    public void sink(int node) {
        while (node < size) {
            int left = left(node);
            int right = right(node);
            int maxIndex = node;
            if (left < size && values[left] > values[node]) {
                maxIndex = left;
            }
            if (right < size && values[right] > values[maxIndex]) {
                maxIndex = right;
            }
            if (maxIndex == node) {
                break;  // 结束循环
            }
            swap(node, maxIndex);
            node = maxIndex;    // 下一轮循环
        }
    }

    // 上浮
    public void swim(int node) {
        while (node >= 0) {
            int parent = parent(node);
            if (values[parent] < values[node]) {
                swap(node, parent);
                node = parent;
            } else {
                break;
            }
        }
    }
}
