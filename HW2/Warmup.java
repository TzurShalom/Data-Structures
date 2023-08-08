
import java.util.Arrays;

public class Warmup {

    // The method receives an unsorted array of integers arr.
    // The method searches for the index of the first occurrence of the value x.
    public static int backtrackingSearch(int[] arr, int x, int forward, int back, Stack myStack) {
        // TODO: implement your code here
        if (arr == null || arr.length == 0)
            return -1;
        int index = 0;
        while (index + forward < arr.length) {
            for (int toPush = 0; toPush < forward; toPush++, index++) {
                if (arr[index] == x)
                    return index;
                myStack.push(arr[index]);
            }
            for (int toPop = 0; toPop < back; toPop++, index--)
                myStack.pop();
        }
        while (index < arr.length) {
            if (arr[index] == x)
                return index;
            myStack.push(arr[index]);
            index++;
        }
        return -1;
    }

    // The method receives a sorted array of integers
    // The method searches for the index of the occurrence of the value x by using the binary search algorithm.
    public static int consistentBinSearch(int[] arr, int x, Stack myStack) {
        // TODO: implement your code here
        if (arr == null || arr.length == 0)
            return -1;
        int min = 0;
        int max = arr.length - 1;
        int curr;
        int inconsistencies = 0;
        while (max >= min) {
            if (inconsistencies == 0) {
                curr = (max + min) / 2;
                if (arr[curr] == x)
                    return curr;
                myStack.push(new int[]{min, curr, max});
                if (arr[curr] > x)
                    max = curr - 1;
                else
                    min = curr + 1;
            } else {
                while (inconsistencies > 0) {
                    int[] prev = (int[]) myStack.pop();
                    min = prev[0];
                    curr = prev[1];
                    max = prev[2];
                    if (arr[curr] == x)
                        return curr;
                    inconsistencies--;
                }
            }
            inconsistencies = Consistency.isConsistent(arr);
        }
        return -1;
    }
}