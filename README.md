# PluginLab Android插件化研究学习

## 插件化需要的“思想”

>- 主App被系统“安装”调用，这个过程中由系统提供，而插件apk并非被系统安装，简而言之，需要将插件APK看成一个 “非apk”文件，只是一个结构略显复杂的文件。调用插件即用某种特殊的方式打开这个文件。
> - 分析主APP的加载
	  主APP打包后，会形成dex,images,xml资源
	  dex靠PathClassLoader加载
	  图片以及xml资源靠Resource加载
> - 代码实现思路
	  创建dexClassLoader加载插件代码
	  创建Resource加载资源文件
	  管理插件Activity生命周期

学习视频：
[网易云课堂插件化详解](https://study.163.com/course/courseLearn.htm?courseId=1209230809#/learn/live?lessonId=1278874566&courseId=1209230809 "网易云课堂插件化详解")


