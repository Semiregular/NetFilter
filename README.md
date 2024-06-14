
# 网络抓包程序说明

![](https://img.shields.io/github/commit-activity/m/Semiregular/NetFilter?color=6cf) ![](https://img.shields.io/github/repo-size/Semiregular/NetFilter?color=red) ![](https://img.shields.io/github/issues-closed/Semiregular/NetFilter?color=green) ![](https://img.shields.io/github/issues-pr-closed/Semiregular/NetFilter?color=gold)  ![](https://img.shields.io/github/stars/Semiregular/NetFilter?style=flat) 


## 项目架构

采用`Pcap4j`抓包，使用`JavaFX`构建UI界面，使用`Maven`管理依赖。

## 项目目录

启动类：`MainApplication.java`

页面视图控制类：`MainController.java`

抓包配置类：`PacketCapture.java`

网络包结构：`packet`目录下`PacketFormat.java`, `TcpPacketFormat.java`,`UdpPacketFormat.java`

监听器类：`listener`目录下`AllListener.java`,`TCPListener.java`,`UDPListener.java`

页面UI：`resources`目录下`main.fxml`

## 主要思路

1. 抓包进程默认会阻塞主进程，所以需要另起一个线程来执行抓包。
2. 抓包得到的数据包会通过监听器类进行分类，然后分别保存到对应的监听器类中。
3. 数据通过字段绑定到`TableColumn`中，然后通过`ObservableList`自动更新。
4. 使用`JavaFX`的`TableView`来展示数据，并用`JavaFX`的`TabPane`来切换抓包类型。
5. 可以指定源地址，源端口或目标地址，目的端口进行抓包。
=======
# 网络抓包程序说明

## 项目架构

采用`Pcap4j`抓包，使用`JavaFX`构建UI界面，使用`Maven`管理依赖。

## 项目目录

启动类：`MainApplication.java`

页面视图控制类：`MainController.java`

抓包配置类：`PacketCapture.java`

网络包结构：`packet`目录下`PacketFormat.java`, `TcpPacketFormat.java`,`UdpPacketFormat.java`

监听器类：`listener`目录下`AllListener.java`,`TCPListener.java`,`UDPListener.java`

页面UI：`resources`目录下`main.fxml`

## 主要思路

1. 抓包进程默认会阻塞主进程，所以需要另起一个线程来执行抓包。
2. 抓包得到的数据包会通过监听器类进行分类，然后分别保存到对应的监听器类中。
3. 数据通过字段绑定到`TableColumn`中，然后通过`ObservableList`自动更新。
4. 使用`JavaFX`的`TableView`来展示数据，并用`JavaFX`的`TabPane`来切换抓包类型。
5. 可以指定源地址，源端口或目标地址，目的端口进行抓包。
6. 支持数据保存，使用`Jackson`进行序列化。