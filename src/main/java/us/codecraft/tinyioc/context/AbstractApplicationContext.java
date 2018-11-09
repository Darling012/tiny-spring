package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.beans.BeanPostProcessor;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * @author yihua.huang@dianping.com
 * 它的构造函数接收入参 BeanFactory，所以说 ApplicationContext 内部具有一个 BeanFactory
 *
 *
 * ApplicationContext 的抽象实现，内部包含一个 BeanFactory 类。主要方法有 getBean() 和 refresh() 方法。getBean() 直接调用了内置 BeanFactory 的 getBean() 方法，refresh() 则用于实现 BeanFactory 的刷新，也就是告诉 BeanFactory 该使用哪个资源（Resource）加载类定义（BeanDefinition）信息，该方法留给子类实现，用以实现 从不同来源的不同类型的资源加载类定义 的效果
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
	protected AbstractBeanFactory beanFactory;

	public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public void refresh() throws Exception {
		// 这个是一个模板方法，需要子类实现，它的作用就是从某一个地方读取 BeanDefinition，然后写入到 ApplicationContext 自己的 BeanFactory 里面，这就是 ApplicationContext 与 BeanFactory 之间的联系，也就是 ApplicationContext 还负责了读取定义
		loadBeanDefinitions(beanFactory);
		// 这个就是在 BeanFactory 里面找到 BeanPostProcessor，然后将他们放到 BeanFactory 的 beanPostProcessors 容器里面，方便 BeanFactory 初始化使用
		registerBeanPostProcessors(beanFactory);
		// 初始化一遍所有的 bean
		onRefresh();
	}

	protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

	protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
		List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
		for (Object beanPostProcessor : beanPostProcessors) {
			beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
		}
	}

	protected void onRefresh() throws Exception{
        beanFactory.preInstantiateSingletons();
    }

	@Override
	public Object getBean(String name) throws Exception {
		return beanFactory.getBean(name);
	}
}
