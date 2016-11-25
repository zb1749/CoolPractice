package jdk.collection.different;

import com.alibaba.fastjson.JSONArray;
import jdk.collection.different.pojo.ProductCyclePojo;

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
        //removeAll 方法对pojo list并不好用，因为对象不想等，如果重写pojo对象的 hashcode方法和equals方法 没准可以=。=
        String newCycleArrJson = "[{cycleNum:'1',fee:'100',supportPayTypes:'4',clientPrompt:'aa',autoRenewalFlag:'1',appStoreProductId:'123',appStoreApplicationId:'123'},{cycleNum:'3',fee:'300',supportPayTypes:'4',clientPrompt:'n',autoRenewalFlag:'0',appStoreProductId:'123',appStoreApplicationId:'123'},{cycleNum:'5',fee:'500',supportPayTypes:'4',clientPrompt:'r',autoRenewalFlag:'0',appStoreProductId:'123',appStoreApplicationId:'123'}]";
        String oldCycleArrJson = "[{'appStoreApplicationId':'123','appStoreProductId':'123','autoRenewalFlag':'1','clientPrompt':'aa','cycleNum':'1','fee':'100','supportPayTypes':'4'},{'appStoreApplicationId':'123','appStoreProductId':'123','autoRenewalFlag':'0','clientPrompt':'n','cycleNum':'3','fee':'300','supportPayTypes':'4'}]";
        List<ProductCyclePojo> newCycleList = JSONArray.parseArray(newCycleArrJson, ProductCyclePojo.class);
        List<ProductCyclePojo> oldCycleList = JSONArray.parseArray(oldCycleArrJson, ProductCyclePojo.class);
        System.out.println("newCycleList");
        for (ProductCyclePojo pojo : newCycleList) {
            System.out.println(pojo);
        }
        System.out.println("oldCycleList");
        for (ProductCyclePojo pojo : oldCycleList) {
            System.out.println(pojo);
        }

        oldCycleList.removeAll(newCycleList);
        System.out.println("removeAll");
        for (ProductCyclePojo pojo : oldCycleList) {
            System.out.println(pojo);
        }

        System.out.println("remove pojo loop");
        List<ProductCyclePojo> productCycleList = JSONArray.parseArray(newCycleArrJson, ProductCyclePojo.class);
        List<ProductCyclePojo> oldProductCycleList = JSONArray.parseArray(oldCycleArrJson, ProductCyclePojo.class);
        for(ProductCyclePojo oldPojo : oldProductCycleList){
            for(ProductCyclePojo newPojo : productCycleList){
                if(oldPojo.equalPojo(newPojo)){
                    oldProductCycleList.remove(oldPojo);
                    break;
                }
            }
        }
        for (ProductCyclePojo pojo : oldProductCycleList) {
            System.out.println(pojo);
        }

    }
}
