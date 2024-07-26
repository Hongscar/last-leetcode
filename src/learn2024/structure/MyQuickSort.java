package learn2024.structure;

public class MyQuickSort {

    public static int quickSelect(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    public static int[] quickSort(int[] nums) {
        return quickSort(nums, 0, nums.length - 1);
    }

    public static int[] quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivot = partition(nums, left, right);
            quickSort(nums, left, pivot - 1);
            quickSort(nums, pivot + 1, right);
        }
        return nums;
    }

    public static int quickSelect(int[] nums, int left, int right, int index) {
        if (left < right) {
            int pivot = partition(nums, left, right);
            if (pivot == index) {
                return nums[index];
            }
            quickSort(nums, left, pivot - 1);
            quickSort(nums, pivot + 1, right);
        }
        return nums[index];
    }

    public static int partition(int[] nums, int left, int right) {
        int pivotKey = left;
        while (left < right) {
            pivotKey = left;    // 起始 pivot
            while (left < right && nums[pivotKey] <= nums[right]) {
                right--;
            }
            swap(nums, left, right);    // 将右侧小的数移动到左侧
            pivotKey = right;   // 调整 pivot
            while (left < right && nums[pivotKey] >= nums[left]) {
                left++;
            }
            swap(nums, left, right);    // 将左侧大的数移动到右侧
        }

        return left;
    }

    public static void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }

    public static void main(String[] args) {
        int[] ints = quickSort(new int[]{7,6,5,4,3,2,1});
        for (int i: ints) {
            System.out.println(i);
        }
    }
}
