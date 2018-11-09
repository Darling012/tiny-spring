`定义了资源加载相关的抽象概念，这里的资源包括 xml 配置文件等。
获取某一个地址的Resource，从而获取这个文件的输入流`。因为使用了 Resource 概念，可以使用网络文件或者本地文件。

以 Resource 接口为核心发散出的几个类，都是用于解决 IoC 容器中的内容从哪里来的问题，也就是 配置文件从哪里读取、配置文件如何读取 的问题。


 这里在设计上有一定的问题，ResourceLoader 直接返回了一个 UrlResource，更好的方法是声明一个 ResourceLoader 接口，再实现一个 UrlResourceLoader 类用于加载 UrlResource。