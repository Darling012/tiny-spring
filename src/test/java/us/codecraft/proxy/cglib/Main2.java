package us.codecraft.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import us.codecraft.proxy.jdk.UserService;
import us.codecraft.proxy.jdk.UserServiceImpl;

public class Main2 {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
 
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(cglibProxy);
 
        UserService o = (UserService)enhancer.create();
        o.getName(1);
        o.getAge(1);
    }
}