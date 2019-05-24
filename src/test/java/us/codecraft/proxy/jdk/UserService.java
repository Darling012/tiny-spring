package us.codecraft.proxy.jdk;
// 自定义的接口类，JDK动态代理的实现必须有对应的接口类
public interface UserService {
    public String getName(int id);
 
    public Integer getAge(int id);
}