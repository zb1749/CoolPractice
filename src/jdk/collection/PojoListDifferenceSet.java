package jdk.collection;

import com.alibaba.fastjson.JSONArray;
import jdk.collection.pojo.ProductCyclePojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2016/9/24.
 */
public class PojoListDifferenceSet {
    public static void main(String[] args) {

        //remove all pojo object from list
        //removeAll 方法对pojo list并不好用，因为对象不想等，如果重写pojo对象的 hashcode方法和equals方法 没准可以=。=
        String newCycleArrJson = "[{cycleNum:'1',fee:'100',supportPayTypes:'4',clientPrompt:'aa',autoRenewalFlag:'1',appStoreProductId:'123',appStoreApplicationId:'123'},{cycleNum:'3',fee:'300',supportPayTypes:'4',clientPrompt:'n',autoRenewalFlag:'0',appStoreProductId:'123',appStoreApplicationId:'123'},{cycleNum:'5',fee:'500',supportPayTypes:'4',clientPrompt:'r',autoRenewalFlag:'0',appStoreProductId:'123',appStoreApplicationId:'123'}]";
        String oldCycleArrJson = "[{'appStoreApplicationId':'123','appStoreProductId':'123','autoRenewalFlag':'1','clientPrompt':'aa','cycleNum':'1','fee':'100','supportPayTypes':'4'},{'appStoreApplicationId':'123','appStoreProductId':'123','autoRenewalFlag':'0','clientPrompt':'n','cycleNum':'3','fee':'300','supportPayTypes':'4'}]";
        List<ProductCyclePojo> newCycleList = JSONArray.parseArray(newCycleArrJson, ProductCyclePojo.class);
        List<ProductCyclePojo> oldCycleList = JSONArray.parseArray(oldCycleArrJson, ProductCyclePojo.class);
        List<ProductCyclePojo> deleteCycleList = new ArrayList<ProductCyclePojo>();
        System.out.println("newCycleList");
        for (ProductCyclePojo pojo : newCycleList) {
            System.out.println(pojo);
        }
        System.out.println("oldCycleList");
        for (ProductCyclePojo pojo : oldCycleList) {
            System.out.println(pojo);
        }

//        oldCycleList.removeAll(newCycleList);
//        System.out.println("removeAll");
//        for (ProductCyclePojo pojo : oldCycleList) {
//            System.out.println(pojo);
//        }

        System.out.println("remove pojo loop");
        List<ProductCyclePojo> productCycleList = JSONArray.parseArray(newCycleArrJson, ProductCyclePojo.class);
        List<ProductCyclePojo> oldProductCycleList = JSONArray.parseArray(oldCycleArrJson, ProductCyclePojo.class);
        for (ProductCyclePojo oldPojo : oldProductCycleList) {
            for (ProductCyclePojo newPojo : productCycleList) {
                if (oldPojo.equalPojo(newPojo)) {
                    //oldProductCycleList.remove(oldPojo);
                    deleteCycleList.add(oldPojo);
                    break;
                }
            }
        }
        //list 遍历不能直接用remove，因为长度会变，就会直接跳出循环了=。=
        for (ProductCyclePojo pojo : deleteCycleList) {
            System.out.println(pojo);
        }

    }
}
