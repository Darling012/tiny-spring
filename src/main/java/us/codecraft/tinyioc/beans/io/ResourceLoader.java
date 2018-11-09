package us.codecraft.tinyioc.beans.io;

import java.net.URL;

/**
	资源加载类。通过 getResource(String) 方法获取一个 Resouce 对象，是 获取 Resouce 的主要途径 
 * @author yihua.huang@dianping.com
 */
public class ResourceLoader {

    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
