package jdk.collection;

import com.alibaba.fastjson.JSONArray;
import jdk.collection.pojo.ProductCyclePojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2016/9/21.
 */
public class ListDifferenceSet {
    public static void main(String[] args) {

        //remove all string list  -- correct
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        List<String> list2 = new ArrayList<String>();
        list2.add("3");
        list2.add("4");

        System.out.println(list1.removeAll(list2));

        for (String val : list1) {
            System.out.println(val);
        }

        //remove all pojo object from list
        String newCycleArrJson = "[{cycleNum:'1',fee:'100',supportPayTypes:'4',clientPrompt:'aa',autoRenewalFlag:'1',appStoreProductId:'123',appStoreApplicationId:'123'},{cycleNum:'3',fee:'300',supportPayTypes:'4',clientPrompt:'n',autoRenewalFlag:'0',appStoreProductId:'123',appStoreApplicationId:'123'},{cycleNum:'5',fee:'500',supportPayTypes:'4',clientPrompt:'r',autoRenewalFlag:'0',appStoreProductId:'123',appStoreApplicationId:'123'}]";
        String oldCycleArrJson = "[{'appStoreApplicationId':'123','appStoreProductId':'123','autoRenewalFlag':'1','clientPrompt':'aa','cycleNum':'1','fee':'100','supportPayTypes':'4'},{'appStoreApplicationId':'123','appStoreProductId':'123','autoRenewalFlag':'0','clientPrompt':'n','cycleNum':'3','fee':'300','supportPayTypes':'4'}]";
        List<ProductCyclePojo> newCycleList = JSONArray.parseArray(newCycleArrJson, ProductCyclePojo.class);
        List<ProductCyclePojo> oldCycleList = JSONArray.parseArray(oldCycleArrJson, ProductCyclePojo.class);
        oldCycleList.removeAll(newCycleList);
        System.out.println("removeAll");
        for (ProductCyclePojo pojo : oldCycleList) {
            System.out.println(pojo);
        }
        System.out.println("remove pojo loop");

    }
}
