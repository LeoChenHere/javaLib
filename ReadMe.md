# Read Me:
[TOC]

# Setup for building
1. create file: gradle.properties @ project root
```text
repoUrl: [URL]
servicelibPath: /repository/[PATH]
mavenCentralPath: /repository/maven-public/
repoUser: [dev account]
repoPassword: [dev password]
```

# 說明
0. sample:
  - 專門放一些使用/測試案例的
1. entity: 
  - 放數據庫物件基本欄位的代碼配置
2. input: 
  - 服務層輸入項的綜合類包，取代 ALHM，更物件導向一些
3. result: 
  - 返回值基本欄位的代碼配置
4. statics: 
  - 常數、枚舉
5. utils: 
  - 一般常用、好用方法，特別是 Sleep 這個，很常用吧，但不要當作生產版本使用，更多的目的是方便開發調適使用

# 待辦
**整理 GitHub內所有Library 類的項目**
* 建立一個項目，專門講述目前工程規劃與進展的說明項目
* Java library : 歸到 Utility
  * JavaLibrary
  *javaLIB
* Spring / Web / Service : 歸到 ServiceLib
  * ServiceFramework
  * webserviceFW
* Android : 歸到 androidLib
  * androidLIB

# 補充
## 未來可能會進行拆分，把 Service 相關的組件都移出去
