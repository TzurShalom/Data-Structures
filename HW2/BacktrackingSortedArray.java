
public class BacktrackingSortedArray implements Array<Integer>, Backtrack
{
    private Stack stack;
    public int[] arr; // This field is public for grading purposes. By coding conventions and best practice it should be private.
    private int length;

    // Do not change the constructor's signature
    public BacktrackingSortedArray(Stack stack, int size)
    {
        this.stack = stack;
        arr = new int[size];
        length = 0;
    }

    // The method returns the key stored in the underlying array at index i.
    @Override
    public Integer get(int index)
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {throw new NullPointerException();}
        else if (index < 0 | index >= length) {throw new ArrayIndexOutOfBoundsException();}
        else {return arr[index];}
    }


    // The method search for the key k in the array, and returns the index if found, using binary search.
    @Override
    public Integer search(int k) {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {return -1;}
        int min = 0;
        int max = length - 1;
        int curr;
        while (max >= min) {
            curr = (max + min) / 2;
            if (arr[curr] == k)
                return curr;
            if (arr[curr] > k)
                max = curr - 1;
            else
                min = curr + 1;
        }
        return -1;
    }

    // The method insert the key x to the array in his right place to keep the array sorted.
    @Override
    public void insert(Integer x)
    {
        if (arr.equals(null) || arr.length == 0) {throw new NullPointerException();}
        else if (length >= arr.length) {throw new ArrayIndexOutOfBoundsException();}

        int index = length;
        for (int i = 0; i < length; i++)
        {
            if (arr[i] > x) {index = i; break;}
        }

        int[] array = {x, index, 1};
        stack.push(array);

        for (int i = length; i > index; i--)
        {
            arr[i] = arr[i-1];
        }
        arr[index] = x;
        length++;
    }


    // The method delete the received index from the array.
    @Override
    public void delete(Integer index)
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {throw new NullPointerException();}
        else if (index < 0 | index >= length) {throw new ArrayIndexOutOfBoundsException();}

        int[] array = {arr[index], index, 0};
        stack.push(array);

        for (int i = index; i < length-1; i++)
        {
            arr[i] = arr[i+1];
        }
        length--;
    }

    // The method return the minimum value in the array.
    @Override
    public Integer minimum()
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {throw new NullPointerException();}
        return 0;
    }

    // The method return the maximum value in the array.
    @Override
    public Integer maximum()
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {throw new NullPointerException();}
        return length-1;
    }

    // The method find if exist and return the successor value of the received index in the array.
    @Override
    public Integer successor(Integer index)
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {throw new NullPointerException();}
        else if (index < 0 | index >= length-1) {throw new NullPointerException();}
        return index+1;
    }

    // The method find if exist and return the predecessor value of the received index in the array.
    @Override
    public Integer predecessor(Integer index)
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {throw new NullPointerException();}
        else if (index <= 0 | index > length-1) {throw new NullPointerException();}
        return index-1;
    }

    // The method cancels the last data manipulating action performed by the data structure.
    @Override
    public void backtrack()
    {
        if (!stack.isEmpty())
        {
            int[] array = (int[]) stack.pop();
            if (array[2] == 1)
            {
                for (int i = array[1]; i < length-1; i++)
                {
                    arr[i] = arr[i+1];
                }
                length--;
            }
            else if (array[2] == 0)
            {
                for (int i = length; i > array[1]; i--)
                {
                    arr[i] = arr[i-1];
                }
                arr[array[1]] = array[0];
                length++;
            }
        }
    }

    @Override
    public void retrack()
    {
        /////////////////////////////////////
        // Do not implement anything here! //
        /////////////////////////////////////
    }

    // The method prints the values in the array.
    @Override
    public void print()
    {
        if (!stack.isEmpty())
        {
            String s = "";
            for (int i = 0; i < length; i++)
            {
                s = s + arr[i] + " ";
            }
            System.out.println(s);
        }
    }
}

