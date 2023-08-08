public class BacktrackingArray implements Array<Integer>, Backtrack
{
    private Stack stack;
    private int[] arr;
    private int length;

    // Do not change the constructor's signature
    public BacktrackingArray(Stack stack, int size)
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

    // The method search for the key k in the array, and returns the index if found.
    @Override
    public Integer search(int k)
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {return -1;}
        for (int i = 0; i < length; i++)
        {
            if (arr[i] == k) {return i;}
        }
        return -1;
    }

    // The method insert the key x to the array.
    @Override
    public void insert(Integer x)
    {
        if (arr.equals(null) || arr.length == 0) {throw new NullPointerException();}
        else if (length >= arr.length) {throw new ArrayIndexOutOfBoundsException();}

        int[] array = {x, 0, length, 1};
        stack.push(array);

        arr[length] = x;
        length++;
    }

    // The method delete the received index from the array.
    @Override
    public void delete(Integer index)
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {throw new NullPointerException();}
        else if (index < 0 | index >= length) {throw new ArrayIndexOutOfBoundsException();}

        int[] array1 = (int[]) stack.pop();
        int[] array2 = new int[4];

        if(index == length-1)
        {
            array2[0] = 0;
            array2[1] = arr[index];
            arr[index] = 0;
        }
        else
        {
            array2[0] = arr[length-1];
            array2[1] = arr[index];
            arr[index] = array2[0];
        }

        array2[2] = index;
        array2[3] = 0;
        length--;

        stack.push(array1);
        stack.push(array2);
    }

    // The method return the minimum value in the array.
    @Override
    public Integer minimum()
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {throw new NullPointerException();}
        int min = 0;
        for (int i = 1; i < length; i++)
        {
            if (arr[min] > arr[i]) {min = i;}
        }
        return min;
    }

    // The method return the maximum value in the array.
    @Override
    public Integer maximum()
    {
        if (arr.equals(null) || arr.length == 0 | stack.isEmpty()) {throw new NullPointerException();}
        int max = 0;
        for (int i = 1; i < length; i++)
        {
            if (arr[max] < arr[i]) {max = i;}
        }
        return max;
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

            arr[length] = array[0];
            arr[array[2]] = array[1];

            if (array[3] == 1) {length--;}
            else if (array[3] == 0) {length++;}
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


