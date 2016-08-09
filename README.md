# FAV-deal

## 第一部分：版本检测
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/版本检测.gif)
  从服务器获取最新版本信息，与当前版本进行比对
####获取当前版本号：
```java 
PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(),0);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return info.versionCode;
        //info.versionNama;
        //info.packageName;
        //info.signatures;
        
  
```


  
