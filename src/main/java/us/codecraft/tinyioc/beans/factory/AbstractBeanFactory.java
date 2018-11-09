package us.codecraft.tinyioc.beans.factory;

import us.codecraft.tinyioc.beans.BeanDefinition;
import us.codecraft.tinyioc.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
   BeanFactory 的一种抽象类实现，规范了 IoC 容器的基本结构，但是把生成 Bean 的具体实现方式留给子类实现。
   IoC 容器的结构：AbstractBeanFactory 维护一个 beanDefinitionMap 哈希表用于保存类的定义信息（BeanDefinition）。
   获取 Bean 时，如果 Bean 已经存在于容器中，则返回之，否则则调用 doCreateBean 方法装配一个 Bean。
   （所谓存在于容器中，是指容器可以通过 beanDefinitionMap 获取 BeanDefinition 进而通过其 getBean() 方法获取 Bean。）
 * @author yihua.huang@dianping.com
 * 1.getBean, 在 beanDefinitionMap 里面得到 bean，如果没有的话，先初始化。（为什么会没有，因为 ApplicationContext 读取 xml 文件时候，只是给 BeanDefinition 赋了类类型，并没有赋值对象，这个对象还是需要 BeanFactory 通过反射生成的）。
   2.createBeanInstance，通过反射，根据 BeanDefinition 的类对象新建实体对象 -> 将得到的 bean 对象赋值给 beandefinition，然后将 BeanDefinition 里面的属性都注入到 Bean 里面，这就完成了 doCreateBean。
   3.initializeBean 就是调用 BeanPostProcessor 的 postProcessBeforeInitilizztion 方法和 postProcessAfterIntilizatin 方法，获取新的 bean，这里会在 aop 中用到。
 */
public abstract class AbstractBeanFactory implements BeanFactory {

	// 类似于 registry，但是他是 BeanFactory 里面私有的，代表的是这个 BeanFactory 里面暂时有哪些 bean 定义
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	// 这个 BeanFactory 里面有哪些 bean（名字）
	private final List<String> beanDefinitionNames = new ArrayList<String>();

    // 代理处理器，AOP 会用到
	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

	//可以获取一个 Bean 对象
	@Override
	public Object getBean(String name) throws Exception {
		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
		if (beanDefinition == null) {
			throw new IllegalArgumentException("No bean named " + name + " is defined");
		}
		Object bean = beanDefinition.getBean();
		if (bean == null) {
			bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
		}
		return bean;
	}

	protected Object initializeBean(Object bean, String name) throws Exception {
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
		}

		// TODO:call initialize method
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
		}
        return bean;
	}

	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
		return beanDefinition.getBeanClass().newInstance();
	}
	// 面向 ApplicationContext，用来将 XML 配置文件里面的 bean 定义注册到 BeanFactory 里面。
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
		beanDefinitionMap.put(name, beanDefinition);
		beanDefinitionNames.add(name);
	}
	// 面向 ApplicationContext，用来在开始的时候，将所有的 bean 都初始化完毕，避免懒加载。
	public void preInstantiateSingletons() throws Exception {
		for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
			String beanName = (String) it.next();
			getBean(beanName);
		}
	}

	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}

	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

	}
    // 添加代理处理器
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
		this.beanPostProcessors.add(beanPostProcessor);
	}
	// 在 BeanFactory 里面，获取某一个类型的所有 bean
	public List getBeansForType(Class type) throws Exception {
		List beans = new ArrayList<Object>();
		for (String beanDefinitionName : beanDefinitionNames) {
			if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
				beans.add(getBean(beanDefinitionName));
			}
		}
		return beans;
	}

}
