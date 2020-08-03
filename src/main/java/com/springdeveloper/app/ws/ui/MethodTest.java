package com.springdeveloper.app.ws.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodTest {

    public Boolean containsCommonItems(ArrayList<Integer> list1, ArrayList<Integer> list2) {

        Map returnMap = new HashMap();
        Boolean isContains = false;
        list1.forEach(item -> {
            if(!returnMap.keySet().contains(item)) {
                returnMap.put(item, "true");

            }
        });

        System.out.println(returnMap);
        for(Integer item2 : list2)
        {
            if(returnMap.keySet().contains(item2)) {
                isContains = true;
                return isContains;
            }
        };
        return isContains;
    }

    public static void main(String[] args){

//        ArrayList<Integer> items = new ArrayList<>();
//        items.add(1);
//        items.add(2);
//        items.add(3);
//        items.add(4);
//        items.add(5);
//
//        ArrayList<Integer> items2 = new ArrayList<>();
//        items2.add(6);
//        items2.add(7);
//        items2.add(8);
//        items2.add(9);
//        items2.add(10);
//
//        MethodTest mt = new MethodTest();
//        Boolean aBoolean = mt. containsCommonItems(items, items2);
//        System.out.println(aBoolean);
//
//        String[] loots = new String[] {"a", "b", "c", "d"};

        int[] intArray = new int[7];
        intArray[0] = 20;
        intArray[1] = 35;
        intArray[2] = -15;
        intArray[3] = 7;
        intArray[4] = 55;
        intArray[5] = 1;
        intArray[6] = -22;
        int index = 0;
        for (int i= 0; i< intArray.length; i++){
            if (intArray[i] == 7){
                index =i;
                break;
            }

        }
        System.out.println("index"+ index);






    }
}
