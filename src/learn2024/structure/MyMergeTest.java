package learn2024.structure;

import learn2024.ListNode;
import learn2024.ListNodeUtils;

import java.util.ArrayList;
import java.util.List;

public class MyMergeTest {

    // LC-23 无情递归
    public static ListNode mergeKLists(ListNode[] lists) {
        List<ListNode> notEmptyList = new ArrayList<>();
        for (ListNode list: lists) {
            if (list != null) {
                notEmptyList.add(list);
            }
        }
        if (notEmptyList.size() == 0) {
            return null;
        }
        if (notEmptyList.size() == 1) {
            return notEmptyList.get(0);
        }
        ListNode minList = notEmptyList.get(0);
        ListNode[] otherLists = new ListNode[notEmptyList.size()];
        int index = 0;
        for (ListNode list: notEmptyList) {
            if (list == null || list == minList) {
                continue;
            }
            if (list.val < minList.val) {
                otherLists[index++] = minList;
                minList = list;
            } else {
                otherLists[index++] = list;
            }
        }
        otherLists[index++] = minList.next;
        minList.next = mergeKLists(otherLists);
        return minList;
    }

    public static void main(String[] args) {
        ListNode listNode1 = ListNodeUtils.oneListNode(1, 4, 5);
        ListNode listNode2 = ListNodeUtils.oneListNode(1, 3, 4);
        ListNode listNode3 = ListNodeUtils.oneListNode(2, 6);
        ListNode[] list = new ListNode[] {listNode1, listNode2, listNode3};
        ListNode listNode = mergeKLists(list);
        System.out.println(ListNodeUtils.print(listNode));
    }
}
