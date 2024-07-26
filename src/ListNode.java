import java.util.ArrayList;
import java.util.List;

/**
 * @author liangjinghong
 * @description
 * @date 2024/6/25 下午8:43
 */
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }


  }

  class ListNodeUtils {
      static ListNode one(int val) {
          return new ListNode(val);
      }

      static List<ListNode> list(Integer... vals) {
          List<ListNode> list = new ArrayList<>();
          for (Integer val: vals) {
              list.add(one(val));
          }
          for (int i = 0; i < list.size() - 1; i++) {
              list.get(i).next = list.get(i + 1);
          }
          return list;
      }

      static String print(ListNode listNode) {
          if (listNode == null) {
              return "null";
          }

          return listNode.val + "->" + print(listNode.next);

      }
  }