# hotkey

正在京东APP后台灰度内测中，小幅度压测过，3台worker每秒接收16万个key，持续10几个小时，大概处理了60多亿个key，平稳无异常，cpu15%无大波动。


#### 介绍
对任意突发性的无法预先感知的热点请求，包括并不限于热点数据（如突发大量请求同一个商品）、热用户（如爬虫、刷子）、热接口（突发海量请求同一个接口）等，进行毫秒级精准探测到。
然后对这些热数据、热用户等，推送到该应用部署的所有机器JVM内存中，以大幅减轻对后端数据存储层的冲击，并可以由客户端决定如何使用这些热key（譬如对热商品做本地缓存、对热用户进行拒绝访问、对热接口进行熔断或返回默认值）。
这些热key在整个应用集群内保持一致性。

核心功能：热数据探测并推送至集群各个服务器

适用场景：

1 mysql热数据本地缓存

2 redis热数据本地缓存

3 黑名单用户本地缓存

4 爬虫用户限流

5 接口、用户维度限流

6 单机接口、用户维度限流限流

7 集群用户维度限流

8 集群接口维度限流


#### 尚未完成
控制台功能缺失如下：

1.rule的定时保存，当etcd更换时需要能一键导入原有配置

2.对/jd/count/cartsoa/ 目录下信息进行展示，代表的是每个worker连接的client数量（已完成）

3.对/jd/caffeineSize/ 目录进行展示，里面是每个worker内caffeine缓存的数量

4.筛选功能，对记录表里做筛选，按时间范围筛选key出现次数大于xx次的数据

5.导出功能，将key排重后导出的功能，按时间范围筛选

6.还有很多

本人不会前端，只能靠外援来做页面。有兴趣参与开发的联系我qq 272551766。

该开源项目战略意义重大，要经历百万级并发，参与京东开源中间件项目建设，一直在等你。

#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
