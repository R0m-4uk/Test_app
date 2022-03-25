public class ok {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int[] newArray = new int[3];
        System.arraycopy(array,0,newArray,0,3);
        array = newArray;
        //changeArraySize(array, 3);
        System.out.println(array.length);
        for (int num = 0; num != array.length; num++) {
            System.out.println(array[num]);
            String expression = "12347,501 + 105 / 2017.3";
        }

    }

    public static void changeArraySize(int[] array, int size){
        array = new int[size];
        //System.arraycopy(array,0,newArray,0, size);

        System.out.println(array.length);
    }

    public static void lox() {
        System.out.println("lox");
    }
}