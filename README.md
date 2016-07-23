# Scope Bar

## 简介
Scope Bar 是一个模仿 iOS 中的 UISegmentedControl 的 Android 组件。

作为一个 Android 开发者，并不推荐在 Android 程序中使用这种样式的控件，但如果因为一些不可控因素必须要用的话，那可以使用这个库来减少码不快乐的代码的时间。

## 效果图
![效果图](http://i.imgur.com/ptbINNo.gif)

## 使用说明

### 导入 Library
在 Project 的 gradle 文件中添加依赖：
````gradle
maven{
    url "http://dl.bintray.com/lanko-x/maven"
}
````
然后在 Module 的 gradle 文件中添加依赖：
````gradle
compile 'com.lanko.scopebar:library:1.0.0'
````

### 在 XML 中定义
````xml
<com.lanko.scopebar.ScopeBar
    android:id="@+id/sb_main_scope_bar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_centerVertical="true"
    android:layout_marginLeft="@dimen/activity_horizontal_margin"
    android:layout_marginRight="@dimen/activity_horizontal_margin"
    app:scopeColor="@color/colorPrimary"
    app:scopeRadius="4dp" />
````

自定义属性说明

| 属性 | 描述 | 
|:------|:----------|
| scopeColor | Scope Bar 的高亮颜色，默认是 #FF007AFF（效果图中的颜色是蓝色） |
| scopeRadius | Scope Bar 四个圆角的度数，默认是 4dp |

### 在 Java 代码中使用
````java
ScopeBar scopeBar;
        
scopeBar = (ScopeBar) findViewById(R.id.sb_main_scope_bar);
scopeBar.addScope("All").addScope("Post").addScope("User");
scopeBar.setOnTabChangeListener(new ScopeBar.OnTabChangeListener() {
    @Override
    public void onTabSelected(int position) {
        mainPager.setCurrentItem(position, false);
    }
});
````

