**上下文层次结构**

DispatcherServlet期望a WebApplicationContext，一个普通的扩展 ApplicationContext，用于它自己的配置。WebApplicationContext有一个链接到它 ServletContext和Servlet它相关联。它也受限于ServletContext 应用程序可以使用静态方法RequestContextUtils来查找 WebApplicationContext它们是否需要访问它。

对于许多具有单一应用的应用WebApplicationContext来说简单且充分。还可以具有上下文层次结构，其中一个根WebApplicationContext 在多个DispatcherServlet（或其他Servlet）实例之间共享，每个实例具有其自己的子WebApplicationContext配置。有关 上下文层次结构功能的更多信息，请参阅ApplicationContext的其他功能。

根WebApplicationContext通常包含基础架构bean，例如需要跨多个Servlet实例共享的数据存储库和业务服务。这些bean是有效继承的，可以在Servlet特定的子代WebApplicationContext中重写（即重新声明）。


**mvc处理流程**
DispatcherServlet初始化
    DispatcherServlet.onRefresh(); 完成前端控制器组件的初始化 
    FrameworkServlet.onApplicationEvent()  响应application刷新事件
    FrameworkServlet.configureAndRefreshWebApplicationContext()  1、注册ContextRefreshListener() 2、ConfigurableWebApplicationContext.refresh()-->发布刷新事件触发刷新事件   
    FrameworkServlet.createWebApplicationContext()
    FrameworkServlet.initWebApplicationContext()
    FrameworkServlet.initServletBean()
    HttpServletBean.init()
    GenericServlet.init()
    
MVC处理过程
    
    
