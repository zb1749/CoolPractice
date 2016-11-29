package dynamicProxy.jdk;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

/**
 * Created by kevin on 2016/10/11.
 */
public class Invoker implements InvocationHandler {
    AbstractClass ac;

    public Invoker(AbstractClass ac) {
        this.ac = ac;
    }

    public Object invoke(Object proxy, Method method, Object[] arg)
            throws Throwable {
        //调用之前可以做一些处理
        method.invoke(ac, arg);
        //调用之后也可以做一些处理
        return null;
    }
}
