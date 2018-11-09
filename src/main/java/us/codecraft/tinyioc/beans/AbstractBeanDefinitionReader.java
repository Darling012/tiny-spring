package us.codecraft.tinyioc.beans;

import us.codecraft.tinyioc.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 从配置中读取BeanDefinition
 * 上面刚说了实现了BeanDefinitionReader接口的类，具有将某一个文件中描述的 bean 定义载入的功能，
   AbstractBeanDefinitionReader就实现了这样一个抽象功能。它的作用就是定义，载入到哪和如何载入的问题
 * @author yihua.huang@dianping.com

 实现 BeanDefinitionReader 接口的抽象类（未具体实现 loadBeanDefinitions，而是规范了 BeanDefinitionReader 的基本结构）。
 内置一个 HashMap rigistry，用于保存 String - beanDefinition 的键值对。
 内置一个 ResourceLoader resourceLoader，用于保存类加载器。用意在于使用时，只需要向其 loadBeanDefinitions() 传入一个资源地址，
 就可以自动调用其类加载器，并把解析到的 BeanDefinition 保存到 registry 中去。
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    // registry是一个注册表，他保存的就是所有的 Bean 定义，Map 结构，key 是 bean 的名字，value 就是 BeanDefinition。
    private Map<String,BeanDefinition> registry;
    /*
     描述了如何载入
     */
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<String, BeanDefinition>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
