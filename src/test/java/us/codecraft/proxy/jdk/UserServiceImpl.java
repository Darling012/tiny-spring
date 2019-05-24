package us.codecraft.proxy.jdk;

import us.codecraft.proxy.jdk.UserService;
// 实现UserService接口类
public class UserServiceImpl implements UserService {
    @Override
    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }
 
    @Override
    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }
}