package us.codecraft.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import us.codecraft.proxy.jdk.UserService;
import us.codecraft.proxy.jdk.UserServiceImpl;

public class Main2 {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
       // 使用CGLIB生成代理:
        // 1.声明增强类实例,用于生产代理类
        Enhancer enhancer = new Enhancer();
         // 2.设置被代理类字节码，CGLIB根据字节码生成被代理类的子类
        enhancer.setSuperclass(UserServiceImpl.class);
        // 3.设置回调函数，即一个方法拦截
        enhancer.setCallback(cglibProxy);
 // 4.创建代理:
        UserService o = (UserService)enhancer.create();
        o.getName(1);
        o.getAge(1);
    }
}