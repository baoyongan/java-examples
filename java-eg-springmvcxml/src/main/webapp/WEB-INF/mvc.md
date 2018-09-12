**上下文层次结构**

    DispatcherServlet期望a WebApplicationContext，一个普通的扩展 ApplicationContext，用于它自己的配置。WebApplicationContext有一个链接到它 ServletContext和Servlet它相关联。它也受限于ServletContext 应用程序可以使用静态方法RequestContextUtils来查找 WebApplicationContext它们是否需要访问它。
    对于许多具有单一应用的应用WebApplicationContext来说简单且充分。还可以具有上下文层次结构，其中一个根WebApplicationContext 在多个DispatcherServlet（或其他Servlet）实例之间共享，每个实例具有其自己的子WebApplicationContext配置。有关 上下文层次结构功能的更多信息，请参阅ApplicationContext的其他功能。
    根WebApplicationContext通常包含基础架构bean，例如需要跨多个Servlet实例共享的数据存储库和业务服务。这些bean是有效继承的，可以在Servlet特定的子代WebApplicationContext中重写（即重新声明）。


**mvc处理流程**

**DispatcherServlet初始化**

    DispatcherServlet.onRefresh(); 完成前端控制器组件的初始化 
    FrameworkServlet.onApplicationEvent()  响应application刷新事件
    FrameworkServlet.configureAndRefreshWebApplicationContext()  1、注册ContextRefreshListener() 2、ConfigurableWebApplicationContext.refresh()-->发布刷新事件触发刷新事件   
    FrameworkServlet.createWebApplicationContext()
    FrameworkServlet.initWebApplicationContext()
    FrameworkServlet.initServletBean()
    HttpServletBean.init()
    GenericServlet.init()
    
**DispatcherServlet处理过程**
    
    1、组建初始化
        其中initHandlerMappings、initHandlerAdapters、initHandlerExceptionResolvers、initViewResolvers会从祖先上下文中搜索对应的实例。
    2、请求处理过程（一般情况）          
                                    
                                    渲染给定的ModelAndView
                                render()处理程序返回要渲染的视图
                                如果是异常，做异常视图处理
                             processDispatchResult() 处理处理程序选择和处理程序调用的结果，即要么将ModelAndView或Exception解析为ModelAndView。
                             应用注册拦截器的postHandle方法。
                             如果视图未添加，添加默认的视图
                             实际上调用处理程序。（适配器程序执行，调用处理程序的handler）
                             执行注册拦截器的preHandle方法。
                             如果处理程序支持，最后修改头。
                             确定当前请求的处理程序适配器。
                             确定当前请求的处理程序。（返回此请求的处理程序和任何拦截器。可以做出选择根据请求URL，会话状态或实现类选择的任何因素。
                                                   返回的HandlerExecutionChain包含一个处理程序Object，而不是甚至是标记接口，因此处理程序不受任何限制）
                             检查请求中的文件，做处理 
           DispatcherServlet.doDispatch()  
           DispatcherServlet.doService();  公开DispatcherServlet特定的请求属性和委托给doDispatch
           FrameworkServlet.doService();
           FrameworkServlet.processRequest()  组装localeContext
           FrameworkServlet.doPost()
    
    
**WebAsyncManager 的作用**

**拦截器和Advice**
    
    拦截器 在处理程序前后或者 请求处理完成添加逻辑 
    advice 对于拦截器的postHandle对于在之前编写和提交响应的方法@ResponseBody和ResponseEntity方法不太有用。这意味着对响应进行任何更改都太晚了，例如添加额外的标头。对于此类方案，您可以实现并将其声明为Controller Advice bean或直接对其进行配置 
    注意使用advice 要在当前的WebApplicationContext可以找打
    
