# LogisticsManagement
一个简单的物流管理系统，《移动开发技术》结课作业

## 功能点
1. 用户登录功能：用户使用工号和密码进行登录，并在登录之后可以退出登录
2. 信息展示功能：在登陆成功后会简单展示个人信息及时间
3. 获取在线运单：通过给定的网址获取在线运单
4. 管理本地运单：可以查看及添加本地运单

## 补充信息
1. 在模拟器上运行时，因为网络问题而无法获取在线运单信息，因此需要在实体机上调试和运行，并且可能需要调整手机上的显示大小
2. 所有日志的tag均以`Application log`开头

## 目录结构
```
app/manifests：用于Android的配置信息，包含必要的权限及允许HTTP请求
app/java(com.example.logisticsmanagement)：
    activities：应用的各种界面，以及封装的组件和公共操作
    data：运行时需要的实体类、DAO（用于管理本地数据）、Service（用于获取在线数据）以及数据库文件
    ui.theme：对应用主题进行配置
    Constant.kt：定义了一些常量，包括各种@Composable的导航以及在线运单地址等信息
    MainActivity.kt：应用执行的入口文件，用于创建NavHost以及加载页面
app/res：包括应用的图标
```

## 用到的库
* Jetpack compose：一个声明式UI库，可以加快界面的开发
* Room：ORM，用于管理本地数据
* Retrofit：HTTP库，用于获取并加载在线数据
* navigation-compose：用于在@Composable函数之间进行导航

## 应用的不足
#### 关于过时的API
在Retrofit中，支持解析XML数据的Converter只有三个，分别是converter-jaxb、converter-jaxb3和converter-simplexml。其中converter-simplexml被标记为过时的API，而前两个库又不支持在Android上运行，因此使用了废弃的converter-simplexml的API。
#### 关于应用性能问题
为了界面的美观，应用当中使用了`material-icons-extended`库作为Jetpack Compose自带图标库的扩展。但是这个库中的大部分图标并没有用到，在使用R8优化后仍然在打包后的APK文件中，造成安装包过大（37.4MB）以及冷启动时间较长（1~1.5s）。

## 关于相关的知识点
在开发过程中总结出来的经验以及技巧会通过博客的形式发出来
[CSDN博客](https://blog.csdn.net/qq_47993287)