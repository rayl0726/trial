package demo.sort;

/**
 * @author : liulei
 **/
public class Sort {


    public static void exchange(int a, int b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void print(int[] array) {
        for (int temp: array) {
            System.out.println(temp);
        }
    }

    //冒泡排序
    //外部循环遍历确定起始位置，内部循环遍历两个两个元素比较，注意遍历的长度要减去外部的起始位置遍历。
    public static int[] bubble(int[] array) {
        int length = array.length;
        for (int i = 0; i < length ; i++) {
            for (int j = 0; j < length-1-i; j++) {
                if(array[j] > array[j+1]) {
                    exchange(j, (j+1), array);
                }
            }
        }
        return array;
    }

    //选择排序
    //先在一次循环中默认当前的元素是最小，在剩下的元素中一个一个和第一个比较，出现较小的交换下标，剩余元素遍历结束后，当最小元素下标不同时，交换元素
    public static void selection(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i+1; j < length; j++) {
                if(array[min] > array[j]) {
                    min = j;
                }
            }
            if(min != i) {
                exchange(min, i, array);
            }
        }

    }

    //插入排序
    //将数组中的所有元素依次跟前面已经排好的元素相比较，如果选择的元素比已排序的元素小，则交换，直到全部元素都比较过为止
    public static void insert(int[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            int temp = array[i];
            for (int j = i; j >= 0; j--) {
                if(j > 0 && array[j-1] > temp) {
                    array[j] = array[j-1];
                } else {
                    array[j] = temp;
                    //前面是已经排序完成的元素，不需要再继续执行
                    break;
                }
            }
        }
    }

    //希尔排序，带步长的插入排序，突破O（n2）
    public static void shell(int[] array) {
        int length = array.length;
        for (int gap = length/2; gap > 1; gap /= 2) {
            for (int i = gap + 1; i  < length; i++) {
                for (int j = i - gap; j > 0 && array[j] < array[j+gap] ; j = j-gap) {
                    exchange(j, j+gap, array);
                }
            }
        }
    }

    public  static void shell2(int[] array) {
        int number = array.length / 2;
        int i;
        int j;
        int temp;
        while (number >= 1) {
            for (i = number; i < array.length; i++) {
                temp = array[i];
                j = i - number;
                while (j >= 0 && array[j] > temp) { //需要注意的是，这里array[j] > temp将会使数组从小到到排序。
                    array[j + number] = array[j];
                    j = j - number;
                }
                array[j + number] = temp;
            }
            number = number / 2;
        }
    }

    //快速排序
    public static void quick(int[] array, int low, int high) {
        int length = array.length;
        if(length == 0 ) return;
        if(low > high) return;
        int left = low;
        int right = high;
        int temp = array[left];
        while(left < right) {
            while(left < right && array[right] >= temp) {
                right--;
            }
            array[left] = array[right];
            while(left < right && array[left] <= temp) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = temp;
        quick(array, low, left-1);
        quick(array, left + 1, high);
    }

    public static void main(String[] args) {
        int[] list = {4,3,23,1234,324,435,567,567,4567,5};
//        bubble(list);
//        selection(list);
//        insert(list);
        shell2(list);
//        quick(list, 0, list.length-1);
        print(list);
    }
}
