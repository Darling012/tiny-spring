package us.codecraft.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理类的实现
public class MyInvocationHandler implements InvocationHandler {
    /**
     * 要被代理的目标对象
     */
    private Object target;

    MyInvocationHandler() {
        super();
    }

    MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }


    /**
     * 创建代理类
     *
     * @return
     */
    public UserService createProxy() {
        /**
         * newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
         * @param loader 类加载器，一般传递目标对象(A类即被代理的对象)的类加载器
         * @param interfaces 目标对象(A)的实现接口
         * @param h 回调处理句柄 InvocationHandler的实现类
         */
        return (UserService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 调用被代理类(目标对象)的任意方法都会触发invoke方法
     *
     * @param proxy  代理类
     * @param method 被代理类的方法
     * @param args   被代理类的方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("getName".equals(method.getName())) {
            System.out.println("++++++before " + method.getName() + "++++++");
            Object result = method.invoke(target, args);
            System.out.println("++++++after " + method.getName() + "++++++");
            return result;
        } else {
            Object result = method.invoke(target, args);
            return result;
        }

    }
}