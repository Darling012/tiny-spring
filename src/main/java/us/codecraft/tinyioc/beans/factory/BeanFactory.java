package us.codecraft.tinyioc.beans.factory;

/**
 * bean的容器
 * @author yihua.huang@dianping.com
 * 实现这个接口的类，就具有了得到一个 bean 的能力。
 
 接口，标识一个 IoC 容器。通过 getBean(String) 方法来 获取一个对象
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}
