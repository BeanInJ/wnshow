package com.woniu.show.util;

public class PurifyName{
    public static String[] replace(String names){
        names = names.replace("\r","");
        names = names.replace("\n","");
        names = names.replace("\t","");

        names = names.replace("，",",");
        names = names.replace(" ","");
        names = names.replace(".",",");

        String[] nameSplit = names.split(",");
        // 去除重复值
        nameSplit = ifRepeat(nameSplit);

        return nameSplit;
    }

    // 数组去重
    public static String[] ifRepeat(String[] arr){
        //去重后的数组长度、给临时数组作为下标索引
        int t = 0;
        //临时数组
        String[] tempArr = new String[arr.length];
        //遍历原数组
        for(int i = 0; i < arr.length; i++){
            //声明一个标记，并每次重置
            boolean isTrue = true;
            //内层循环将原数组的元素逐个对比
            for(int j=i+1;j<arr.length;j++){
                //如果发现有重复元素，改变标记状态并结束当次内层循环
                if(arr[i]==arr[j]){
                    isTrue = false;
                    break;
                }
            }
            //判断标记是否被改变，如果没被改变就是没有重复元素
            if(isTrue){
                //没有元素就将原数组的元素赋给临时数组
                tempArr[t] = arr[i];
                //走到这里证明当前元素没有重复，那么记录自增
                t++;
            }
        }
        //声明需要返回的数组，这个才是去重后的数组
        String[]  newArr = new String[t];
        //用arraycopy方法将刚才去重的数组拷贝到新数组并返回
        System.arraycopy(tempArr,0,newArr,0,t);
        return newArr;
    }

}