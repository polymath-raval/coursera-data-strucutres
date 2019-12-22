import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class MaxSlidingWindow2 {
  
  public static void main(String[] args) throws IOException {
    InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        String[] strNumbers = reader.readLine().split(" ");
        for(int i = 0; i < n; i++)
          numbers[i] = Integer.parseInt(strNumbers[i]);
        
        int windowSize = Integer.parseInt(reader.readLine());
        //System.out.printf("%d\n%s\n%d\n",n, Arrays.toString(numbers), windowSize);
        int[] result = new Solution().maxSlidingWindow(numbers, windowSize);
        for(int i = 0; i < result.length; i++) {
          System.out.printf("%d ",result[i]);
        }
  }


  private static class Solution {
    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int [] nums;

    public void clean_deque(int i, int k) {
      // remove indexes of elements not from sliding window
      if (!deq.isEmpty() && deq.getFirst() == i - k)
        deq.removeFirst();

      // remove from deq indexes of all elements 
      // which are smaller than current element nums[i]
      while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])                           deq.removeLast();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
      int n = nums.length;
      if (n * k == 0) return new int[0];
      if (k == 1) return nums;

      // init deque and output
      this.nums = nums;
      int max_idx = 0;
      for (int i = 0; i < k; i++) {
        clean_deque(i, k);
        deq.addLast(i);
        // compute max in nums[:k]
        if (nums[i] > nums[max_idx]) max_idx = i;
      }
      int [] output = new int[n - k + 1];
      output[0] = nums[max_idx];

      // build output
      for (int i  = k; i < n; i++) {
        clean_deque(i, k);
        deq.addLast(i);
        output[i - k + 1] = nums[deq.getFirst()];
      }
      return output;
    }
  }
}