# FAV-deal

##本次项目使用的框架有：
  ```java 
  1.图片加载
    - picasso 
    - facebook.fresco
  2.工具
    - jakewharton:butterknife:7.0.1
  3.网络
    - async-http
  4.指示器
    - ViewPagerIndicator
    - compile 'com.github.chenupt.android:springindicator:1.0.2@aar'
    - //水滴指示器compile 'com.google.android.gms:play-services-appindexing:8.1.0'
  5.json
    - Gson
  6.列表
    - recyclerview
    - cardview
    - design
  7.数据传递
    - eventbus
  8.头像选择器
    - compile 'com.yancy.imageselector:imageselector:1.3.3'
  9.后端云服务器
    - Bmob
    - bmob后端云所需
      - compile 'cn.bmob.android:http-legacy:1.0'//兼容6.0
      - compile 'cn.bmob.android:bmob-sdk:3.4.7-aar'
  感谢以上所有的github开源框架。
    
  ```
  
  
## 第一部分：版本检测

![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/版本检测.gif)
```java 
  从服务器获取最新版本信息，与当前版本进行比对
  1.用户可以自愿是否开启版本检测，在个人中心设置
  2.欢迎页是否已经执行过，当版本检测之后，会记录到本地sharePreference中，以免每一次进入都进入欢迎页面
  3.当不开启版本检测时，spalsh页面默认三秒自动进入，如果手动点击屏幕，会立即进入软件主界面（有版本更新执行，将封死点击屏幕快速进入）
```
  
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
  ```java 
  1.基本的空字符串的验证，其他验证不想写了
  ```
  
## 第三部分：物品列表和详情\添加购物车
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/添加购物车.gif)
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/游客添加购物车过滤.gif)
  ```java 
  1.查看物品，有下拉刷新功能，使用的是安卓自带的SwipeRefreshLayout
  2.添加购物车，已经做了重复物品添加的判断，当有重复物品添加时，购物车会将物品数量增加一个
  3.查看物品详情界面，使用了自定义的ScollView，实现仿ios回弹的效果
  4.实现大图加载的压缩，尺寸和质量双重压缩。防止oom
  5.游客添加购物车的过滤，（判断本地sp是否存有用户信息）
  ```
  
## 第四部分：抽屉侧滑，分类果蔬的查看
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/抽屉分类果蔬.gif)
  ```java 
  1.使用的是安卓自带的DrawerLayout，并写了一个Drawlayout的demo，其中有一些注意事项，有兴趣的可以去看一下
  ```

## 第五部分：推荐列表的实现
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/推荐列表.gif)
  ```java 
  1.使用的是封装出来的控件randomLayout，也会写成demo分享。
  ```
  
## 第六部分：购物车的功能实现
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/购物车一键清空和重复物品的判断.gif)
  ```java 
  1.使用自定义popwindow写长按删除该物品。
  2.物品数量最低为1的验证，
  3.计算当前购物车的总价，以及优惠的价格，（用户默认为会员）
  4.一键清空购物车操作
  5.提交订单时二次用户是否登陆的验证
  6.购物车缓存在本地
  ```
## 第七部分：生成订单，选取该用户的收货地址，并确认信息
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/生成订单.gif)
  ```java 
  1.主要就是两个界面间集合的传递，我此处用了Gson，json
  ```
  
## 第八部分：收货地址的增删改查，（必须要登陆）
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/地址管理的增删改查.gif)
  ```java 
  1.主要是对bmob后端云的一个数据操作
  2.使用了RecyclerView和CardView
  ```
## 第九部分：本地图库和照相机选取头像
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/头像本地选择和拍照上传bmob.gif)
  ```java 
  1.主要使用了compile 'com.yancy.imageselector:imageselector:1.3.3'  谢谢原始作者。
  2.bmob上传图片的一些操作封装
  ```
  
##第十部份：用户反馈
![image](https://github.com/Qsr9504/FAV-deal/blob/master/gif演示图/反馈.gif)




