# ShortUrl
Java分布式短网址服务，服务器间使用Netty通信。分布式架构练手项目。<br />
<br />
1.ShortUrlServer，负责短网址的创建和查询，处理Dns和Web的请求。<br />
2.ShortUrlDns，内置Jetty，负责短网址解析跳转，调用Server。<br />
3.ShortUrlWeb，负责对外提供调用接口，调用Server。<br />
<br />
演示地址：http://dwz.youshusoft.com:8080/short_url/<br />
<br />
本地快速运行：<br />
1.执行 script/StartAll.bat (会启动2个redis,4个Server,1个DNS)<br />
2.把ShortUrlWeb.war放入tomcat启动<br />


