# DexDumper Dump 加壳app 的 dex文件

|类型|结果|
|--|--|
|乐固 |√
|360 |√
|爱加密|未测试
|梆梆 |未测试
|梆梆企业版 |×


# 使用方法
## 1.一个root手机
## 2.安装Xposed Installer
## 3.修改Dumper.java 文件将 your.package.name 替换为要dump的包名，并给该应用读写内存权限。
```
if ("your.package.name".equals(loadPackageParam.processName)) {
...
}
```
## 4.启动DexDumper Xposed模块
## 5.重启手机，确保DexDumper模块成功启动
## 6.启动要dump的应用。
## 7.在console log中可以看到如下日志，说明dump成功。

```java
2019-03-12 15:43:49.123 2300-2300/your.package.name E/AoS ******: Begin dump dex :/storage/emulated/0/dump/dump_your.package.name1.dex  size :4022044
2019-03-12 15:43:49.154 2300-2300/your.package.name E/AoS ******: Finished dump dex :/storage/emulated/0/dump/dump_your.package.name1.dex  size :4022044
2019-03-12 15:43:51.058 2300-2300/your.package.name E/AoS ******: Begin dump dex :/storage/emulated/0/dump/dump_your.package.name2.dex  size :8809440
2019-03-12 15:43:51.118 2300-2300/your.package.name E/AoS ******: Finished dump dex :/storage/emulated/0/dump/dump_your.package.name2.dex  size :8809440
2019-03-12 15:43:51.129 2300-2300/your.package.name E/AoS ******: Begin dump dex :/storage/emulated/0/dump/dump_your.package.name3.dex  size :4186260
2019-03-12 15:43:51.157 2300-2300/your.package.name E/AoS ******: Finished dump dex :/storage/emulated/0/dump/dump_your.package.name3.dex  size :4186260
2019-03-12 15:43:54.903 2300-3031/your.package.name E/AoS ******: Begin dump dex :/storage/emulated/0/dump/dump_your.package.name4.dex  size :6645372
2019-03-12 15:43:54.940 2300-3031/your.package.name E/AoS ******: Finished dump dex :/storage/emulated/0/dump/dump_your.package.name4.dex  size :6645372
```
## 8.dex文件dump在了 文件管理中的 /storage/emulated/0/dump 目录下
## 9.结束
