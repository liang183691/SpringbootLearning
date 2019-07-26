package org.spring.springboot.suanfa;

public class Sort {
    //冒泡排序
    public void bubbleSort(int[] array){
        int temp = 0;
        for(int i = 0;i<array.length-1;i++)
            for(int j = 0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
    }

    private  void quickSort(int[] a,int low, int high) {
        if(low<high){ //如果不加这个判断递归会无法退出导致堆栈溢出异常
            int middle=getMiddle(a,low,high);
            quickSort(a,  0,  middle-1);          //递归对低子表递归排序
            quickSort(a,   middle + 1, high);        //递归对高子表递归排序
        }
    }

    public int getMiddle(int[] a, int low, int high) {

        int key = a[low];//基准元素，排序中会空出来一个位置
        while (low < high) {
            while (low < high && a[high] >= key) {//从high开始找比基准小的，与low换位置
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= key) {//从high开始找比基准小的，与low换位置
                low++;
            }
            a[high] = a[low];
        }
        a[low] = key;//此时low=high 是基准元素的位置，也是空出来的那个位置
        return low;
    }
}
