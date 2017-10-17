package jvm.GC;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.apache.struts2.components.Bean;
import org.junit.Test;

/**
 * -XX:PermSize=2M -XX:MaxPermSize=4M -XX:PrintGCDetails
 */
public class TestPermClassGC {
    static MyClassLoader cl = new MyClassLoader();

    @Test
    public void testNewClassLoad() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        for (int i=0;i< Integer.MAX_VALUE;i++){
            CtClass c = ClassPool.getDefault().makeClass("Geym"+i);
            c.setSuperclass(ClassPool.getDefault().get("org.apache.struts2.components.Bean"));
            Class clz = c.toClass(cl, null);
            Bean v = (Bean)clz.newInstance();
            if(i%10==0){
                cl = new MyClassLoader();//销毁上一个classLoader
            }
        }
    }

    private static class MyClassLoader extends ClassLoader{
    }
}
