1. 根据实际情况修改test.cmd中的JAVA_HOME,JDK版本1.4（含）以上。
2. 运行test.cmd(windows)或者test.sh(Linux),可以运行演示程序。
3. APITest2.java为Demo源码，做为应用开发的参考。
4. TestData中为测试过程中用的证书，其中test.pfx密码为1;test.jks文件中密码为6个1。sm2pfx.SM2的密码为1.
5. 提供的dll和so文件供采用JNI方式进行SM2签名验签，这些文件均为64位，只能在64位机器使用.
5. 若想在Linux环境下，使用JNI的方式进行SM2签名验签，需要配置动态搜索库路径，可以使用export LD_LIBRARY_PATH命令进行设置。
   windows只要dll在当前工程目录下即可。