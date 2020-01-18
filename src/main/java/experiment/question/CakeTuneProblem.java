package experiment.question;

/**
 * @author liulei
 **/
public class CakeTuneProblem {

    public static int max;//记录所需要翻转的最少次数
    public static int estimateMin;//记录所需要翻转的最少次数
    public static int minNum;//记录所需要翻转的最少次数
    public static int[] cakeArray;//饼的数组序列
    public static int[] resultArray;//饼的数组序列
    public static int[] tempArray;//饼的数组序列
    public static int count = 0;

    //烙饼当前状最少翻转次数
    public static int lowBound(int[] cakeArray){
        int reduce = 0;
        int min = 0;
        for(int i = 0; i < cakeArray.length-1; i++){
            reduce = cakeArray[i] - cakeArray[i+1];
            if(reduce == 1 || reduce == -1){
            }
            else{
                min++;
            }
        }
        return min;
    }

    //翻转函数，将0-index翻转
    public static void reverse(int[] cakeArray, int index){
        int i = 0;
        int j = index;
        int temp;
        while(i < j){
            temp = cakeArray[i];
            cakeArray[i] = cakeArray[j];
            cakeArray[j] = temp;
            i++;
            j--;
        }
    }

    //判断翻转结果是否达到要求
    public static boolean isSorted(int[] cakeArray){
        for(int i = 1; i < cakeArray.length; i++){
            if(cakeArray[i-1] > cakeArray[i]){
                return false;
            }
        }
        return true;
    }

    //翻转主函数，递归求翻转过程，实际上是一棵搜索树
    public static void search(int[] cakeArray, int depth){
        count++;
        estimateMin = lowBound(cakeArray);
        //减支函数
        if((depth + estimateMin) > max){
            return;
        }
        if(isSorted(cakeArray)){
            if(max > depth){
                max = depth;
                resultArray = tempArray;
                System.out.println("当前最少翻转次数cur_min = " + max);
                for(int i = 1; i <= max; i++){
                    System.out.print(resultArray[i]+1 + " ");
                }
                System.out.println();
            }
            return;
        }
        for(int i = 1; i < cakeArray.length; i++){
            if(depth != 0 && tempArray[depth] == i){
                continue;
            }
            reverse(cakeArray,i);
            tempArray[depth + 1] = i;
            search(cakeArray,depth+1);
            reverse(cakeArray,i);
        }
    }

    public static void main(String[] args) {
        cakeArray = new int[] {3,2,1,6,5,4,9,8,7,0};
        resultArray = new int[cakeArray.length * cakeArray.length];
        tempArray = new int[cakeArray.length * cakeArray.length];
        for(int i = 0; i < 2 * cakeArray.length; i++){
            tempArray[i] = 0;
            resultArray[i] = 0;
        }
        max = 2 * cakeArray.length;
        search(cakeArray,0);
        System.out.println("\n最终最少翻转次数final_min = " + max);
        System.out.println("\nTotal Run Times: " + count);
    }
}
