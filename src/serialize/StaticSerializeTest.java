package serialize;

import java.io.*;

public class StaticSerializeTest implements Serializable {
    private static final long serialVersionUID = 1L;
    public static int staticVar = 5;
    public static void main(String[] args) {
        try {
            //初始时staticVar为5
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("result.obj"));
            out.writeObject(new StaticSerializeTest());
            out.close();

            //序列化后修改为10
            StaticSerializeTest.staticVar = 10;
            //静态字段值可以这么取 1

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "result.obj"));
            StaticSerializeTest t = (StaticSerializeTest) oin.readObject();
            oin.close();

            StaticSerializeTest t2 = new StaticSerializeTest();

            //再读取，通过t.staticVar打印新的值
            System.out.println(t.staticVar);
            //静态字段值也可以这么取 2
            //这里打印出来的是10，说明之前序列化保存的5并没有被保存
            //这里只是取了静态字段的值

            //再次更改，并不会改变反序列化后属性字段的值
            StaticSerializeTest.staticVar = 15;
            System.out.println(t.staticVar);//15
            //类相关了，和对象怎么new的没关系=。=
            System.out.println(t2.staticVar);//15

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

