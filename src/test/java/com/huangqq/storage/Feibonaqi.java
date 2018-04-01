package com.huangqq.storage;

import java.util.HashSet;
import java.util.WeakHashMap;

/**
 * Created by huangqq on 2017/12/25.
 */
public class Feibonaqi {

    public static void main(String[] args) {
        System.out.println(getIndex(18));
    }

    public static int getTotalCount(int monthCount){
        int num = 0;
        while (monthCount >= 0){
            num += getIndex(monthCount);
            monthCount--;
        }

        return num;
    }

     /*
    输入第几个获得
    斐波那契数列那个数
    */
    public static int getIndex(int index){
        if (index == 1 || index == 2){
            return 1;
        }else {
            return getIndex(index - 1) + getIndex(index - 2);
        }
    }
}
