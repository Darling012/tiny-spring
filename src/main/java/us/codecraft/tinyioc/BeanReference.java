package us.codecraft.tinyioc;

/**
 * @author yihua.huang@dianping.com
 */

//指的是引用类型的 Bean，而不是实体类。
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
