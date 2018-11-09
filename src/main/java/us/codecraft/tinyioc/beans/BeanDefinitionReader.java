package us.codecraft.tinyioc.beans;

/**
 * 从配置中读取BeanDefinition
 * @author yihua.huang@dianping.com
 * 实现这个接口的类，具有将某一个文件中的所有 bean 定义载入的功能
 * 在哪载入 bean 定义，至于载入到哪里、如何载入，稍后看具体实现

 解析 BeanDefinition 的接口。通过 loadBeanDefinitions(String) 来从一个地址加载类定义。
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
