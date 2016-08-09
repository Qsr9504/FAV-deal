# FAV-deal

## 第一部分：版本检测
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/版本检测.gif)
  从服务器获取最新版本信息，与当前版本进行比对
  1.用户可以自愿是否开启版本检测，在个人中心设置
  2.欢迎页是否已经执行过，当版本检测之后，会记录到本地sharePreference中，以免每一次进入都进入欢迎页面
  3.当不开启版本检测时，spalsh页面默认三秒自动进入，如果手动点击屏幕，会立即进入软件主界面（有版本更新执行，将封死点击屏幕快速进入）

  
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

## 第二部分：用户登录注册
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/登录注册.gif)
  从bmob后端云获取用户信息验证并比对注册。
  1.基本的空字符串的验证，其他验证不想写了
  
## 第三部分：物品列表和详情
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/添加购物车.gif)
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/游客添加购物车过滤.gif)
  1.查看物品，有下拉刷新功能，使用的是安卓自带的SwipeRefreshLayout
  2.添加购物车，已经做了重复物品添加的判断，当有重复物品添加时，购物车会将物品数量增加一个
  3.查看物品详情界面，使用了自定义的ScollView，实现仿ios回弹的效果
  4.实现大图加载的压缩，尺寸和质量双重压缩。防止oom
  5.游客添加购物车的过滤，（判断本地sp是否存有用户信息）
  

  
