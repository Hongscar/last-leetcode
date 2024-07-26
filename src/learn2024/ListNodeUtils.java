package learn2024;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表工具类
 */
public class ListNodeUtils {
    public static ListNode one(int val) {
        return new ListNode(val);
    }

    public static ListNode oneListNode(Integer... vals) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val: vals) {
            current.next = one(val);
            current = current.next;
        }
        return dummy.next;
    }

    public static List<ListNode> list(Integer... vals) {
        List<ListNode> list = new ArrayList<>();
        for (Integer val: vals) {
            list.add(one(val));
        }
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        return list;
    }

    public static String print(ListNode listNode) {
        if (listNode == null) {
            return "null";
        }

        return listNode.val + "->" + print(listNode.next);

    }
}
