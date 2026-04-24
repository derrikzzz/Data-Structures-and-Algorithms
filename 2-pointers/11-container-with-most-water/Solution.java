public class Solution {
    public Integer max_area(int[] heights) {
        int left = 0;
        int right = heights.length - 1;

        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int smallerHeight = Math.min(heights[left], heights[right]);

            int area = width * smallerHeight;
            maxArea = Math.max(maxArea, area);
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}