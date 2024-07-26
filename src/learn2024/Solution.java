package learn2024; /**
 * @author liangjinghong
 * @description
 * @date 2024/6/25 下午8:42
 */

import java.util.*;

/**
 * Definition for singly-linked list.
 * public class learn2024.ListNode {
 *     int val;
 *     learn2024.ListNode next;
 *     learn2024.ListNode() {}
 *     learn2024.ListNode(int val) { this.val = val; }
 *     learn2024.ListNode(int val, learn2024.ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {
    static List<ListNode> list = ListNodeUtils.list(1, 2, 3, 4, 5, 6);

    // LC-92 迭代
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode leftBeginNode = head, rightBeginNode = head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = null;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }
        ListNode beginNode = prev;
        ListNode tmpNext = null;
        current = prev.next;


        for (int i = left; i <= right; i++) {

            tmpNext = current.next;
            if (i == left) {
                leftBeginNode = current;
            }
            if (i == right) {
                rightBeginNode = current;
            }
            current.next = prev;
//            prev.next = null;
            prev = current; //aka current.necessary to set prev.next to null?
            // 这里一开始想着要不要 prev.next = null，但其实会把原本节点的结构破坏掉，看到环就最后解决，别提前破坏
            current = tmpNext;
        }
//        System.out.println(learn2024.ListNodeUtils.print(list.get(3)));
//        System.out.println(learn2024.ListNodeUtils.print(list.get(4)));
//        System.out.println(learn2024.ListNodeUtils.print(list.get(5)));
//        System.out.println(learn2024.ListNodeUtils.print(list.get(2)));
//        System.out.println(learn2024.ListNodeUtils.print(list.get(0)));
        beginNode.next = rightBeginNode;
//        rightBeginNode.next = null;
        leftBeginNode.next = tmpNext;   // next level already
         return dummy.next;
//        return prev;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current.left != null) {
                stack.addFirst(current);
                current = current.left; // 不断找 left 结点，并且压栈
            }
            else {
                TreeNode element1 = stack.removeFirst();
                list.add(element1.val);
                current = current.right;
            }
        }
        return list;
    }

    // error -24-06-28 21:50
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        HashSet<TreeNode> handleSet = new HashSet<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.getFirst();
            if (handleSet.contains(current.left) && handleSet.contains(current.right)) {
                handleSet.add(current);
                list.add(current.val);
                stack.removeFirst();
            } else {
                if (current.right != null) {
                    stack.addFirst(current.right);
                } else {
                    stack.addFirst(current.left);
                }
            }
        }
        return list;
    }

    // LC-560
    public static int subarraySum(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSumMap.put(i, sum);
        }
//        prefixSumMap.put(0, 0);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                res++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (prefixSumMap.get(j) - prefixSumMap.get(i) == k - nums[i]) {
                    res++;
                }
            }
        }
        return res;
    }

    // LC 680
    public static boolean validPalindrome(String s) {
        return helper(s, 0, s.length() - 1, true);
    }

    public static boolean helper(String s, int leftBegin, int rightBegin, boolean flag) {
        if (rightBegin - leftBegin + 1 <= 1) {
            return true;
        }
        int left = leftBegin, right = rightBegin;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else if (flag) {
                return helper(s, left + 1, right, false) || helper(s, left, right - 1, false);
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num: nums)
            sum += num;
        if (sum % 2 != 0)
            return false;
        int weight = sum / 2;
        boolean[][] dp = new boolean[nums.length][weight + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= weight; j++) {
                if (i == 0) {
                    dp[i][j] = nums[i] == j;
                    continue;
                }
                if (nums[i] <= j)
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[nums.length - 1][weight];
    }

    public static int numDecodings(String s) {
        // dp[i] = 前 i 个字符可组成的解码数
        // dp[i] = dp[i - 1] + dp[i - 2] (第 i 个字符是否与 i - 1 个字符相连)
        int[] dp = new int[s.length() + 1];
        if (s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            char cur = s.charAt(i - 1);
            char prev = s.charAt(i - 2);
            if (cur == '0' && (prev != '1' && prev != '2')) {
                return 0;
            }
            else if (cur == '0' && (prev == '1' || prev == '2')) {
                dp[i] = dp[i - 2];
            }
            else if (prev >= '3' || (prev == '2' && (cur >= '7'))) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[s.length()];

    }

    // lcs # 1143
    public static int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j] text1前 i 个字符和 text2 前 j 个字符的 LCS
        // if text1.charAt(i) == text2.charAt(j) dp[i][j] = dp[i - 1][j - 1] + 1
        // else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
        int[][] dp = new int[text1.length()][text2.length()];
        char t1 = text1.charAt(0);
        char t2 = text2.charAt(0);
        for (int i = 0; i < text1.length(); i++) {
            if (text1.charAt(i) == t2) {
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < text2.length(); i++) {
            if (text2.charAt(i) == t1) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }

    public static int maxProfit(int k, int[] prices) {
        int[][][] dp = new int[prices.length][k + 1][2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[0];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }
        return dp[prices.length - 1][k][0];
    }


    public static void main(String[] args) {

        System.out.println(maxProfit(2, new int[]{2, 4, 1}));

//        System.out.println(longestCommonSubsequence("abcde", "ace"));

//        System.out.println(numDecodings("2101"));

//        System.out.println(canPartition(new int[]{4}));
//
//        String str = "abca";
//        System.out.println(validPalindrome(str));


//        int[] nums = {1,2,1,2,1};
//        int k = 3;
//        System.out.println(subarraySum(nums, k));


//        learn2024.ListNode listNode = reverseBetween(list.get(0), 3, 5);
//        System.out.println(learn2024.ListNodeUtils.print(list.get(0)));
//        System.out.println(learn2024.ListNodeUtils.print(listNode));
    }
}