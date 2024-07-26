package learn2024.structure;

import java.util.ArrayList;
import java.util.List;

public class MyMergeTest {


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
            if (list == null) {
                continue;
            }
            if (list.val < minList.val) {
                otherLists[index++] = minList;
                minList = list;
            }
        }
        otherLists[index++] = minList.next;
        minList.next = mergeKLists(otherLists);
        return minList;
    }

    public static void main(String[] args) {

    }
}
