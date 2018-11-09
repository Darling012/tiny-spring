package us.codecraft.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource是spring内部定位资源的接口。
 * @author yihua.huang@dianping.com
 *  接口，标识一个外部资源。通过 getInputStream() 方法 获取资源的输入流 。
    这个接口的类就是一个抽象的资源，可以获取这个资源的输入流，从而获取其中的内容
 */


public interface Resource {

    InputStream getInputStream() throws IOException;
}
