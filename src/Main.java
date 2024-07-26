import structure.MyHeap;

import java.util.*;

public class Main {

    public String longestPalindrome(String s) {
        boolean[][] dp = longestPalindromeHelper(s);
        String res = "";
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = i; j < dp.length; j++) {
                if (dp[i][j]) {
                    if (max < j - i + 1) {
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }

    public boolean[][] longestPalindromeHelper(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    dp[i][j] = true;
                }
                // 长度为偶数，或者为奇数并且中间的为true
                else if (s.charAt(i) == s.charAt(j) && (j - i == 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp;
    }

    // lc-350
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num1: nums1) {
            map1.put(num1, map1.getOrDefault(num1, 0) + 1);
        }
        for (int num2: nums2) {
            map2.put(num2, map2.getOrDefault(num2, 0) + 1);
        }
        List<Integer> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map1.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            value = Math.min(value, map2.getOrDefault(key, 0));

            for (int i = 0; i < value; i++) {
                resultList.add(key);
            }
        }
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }


    //324
    public static int[] wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int[] res = new int[nums.length];
        int index = 0;
        while (left < right) {
            res[index++] = nums[left++];
            res[index++] = nums[right--];
        }
        if (left == right) {
            res[index++] = nums[left];
        }
        nums = res.clone();
        return nums;
    }


    public static int findKthLargest(int[] nums, int k) {
        MyHeap myHeap = new MyHeap(nums.length);
        for (int num: nums) {
            myHeap.push(num);
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            res = myHeap.pop();
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));


//        int[] res = wiggleSort(new int[] {1, 5, 1, 1, 6, 4});
//        System.out.println(res);
//
//        List<Integer> list = new ArrayList<>(6);
//        System.out.println(list);


//        System.out.println(longestPalindrome("bbab"));

    }
}