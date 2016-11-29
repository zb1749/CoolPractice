package dynamicProxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Kevin on 2016/11/28.
 */
public class EnhancerDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerDemo.class);
        enhancer.setCallback(new MethodInterceptorImpl());

        EnhancerDemo demo = (EnhancerDemo) enhancer.create();
        demo.test();
        System.out.println(demo);
    }
    /**
     * before invoke public void dynamicProxy.cglib.EnhancerDemo.test()
     * EnhancerDemo test
     * after invoke public void dynamicProxy.cglib.EnhancerDemo.test()
     * before invoke public java.lang.String java.lang.Object.toString()
     * before invoke public native int java.lang.Object.hashCode()
     * after invoke public native int java.lang.Object.hashCode()
     * after invoke public java.lang.String java.lang.Object.toString()
     * dynamicProxy.cglib.EnhancerDemo$$EnhancerByCGLIB$$7eeb3e8a@4c98385c
     */

    public void test() {
        System.out.println("EnhancerDemo test");
    }

    private static class MethodInterceptorImpl implements MethodInterceptor {
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before invoke " + method);
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("after invoke " + method);
            return result;
        }
    }

}
