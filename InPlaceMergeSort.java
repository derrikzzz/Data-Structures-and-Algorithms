public class InPlaceMergeSort {

    // Main function to perform in-place merge sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);      // Sort left half
            mergeSort(arr, mid + 1, right); // Sort right half
            merge(arr, left, mid, right);  // Merge the two halves in-place
        }
    }

    // In-place merge function
    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left;       // Pointer for the left subarray
        int j = mid + 1;     // Pointer for the right subarray

        // Perform the merge without extra space
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                i++; // Element is in the correct position
            } else {
                // Shift elements to make space for arr[j]
                int temp = arr[j];
                for (int k = j; k > i; k--) {
                    arr[k] = arr[k - 1];
                }
                arr[i] = temp;

                // Update pointers
                i++;
                j++;
                mid++; // Mid shifts because we inserted an element
            }
        }
    }

    // Helper function to print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array:");
        printArray(arr);

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}