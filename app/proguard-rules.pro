# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-dontwarn
-dontskipnonpubliclibraryclassmembers
-ignorewarnings
-useuniqueclassmembernames
-allowaccessmodification
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod


#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}

# 泛型与反射
-keepattributes Signature
-keepattributes EnclosingMethod
-keepattributes *Annotation*

#保持继承自系统类的class不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep interface android.support.v7.app.** { *; }
-keep class android.support.v7.** { *; }
-keep public class * extends android.support.v7.**
-keep public class * extends android.app.Fragment
-keep class * extends android.**{*;}
-keep class SQLite3.**{*;}


-keep class javax.annotation.** { *; }
-keep class javax.servlet.**{*;}
-keep class com.f2prateek.rx.**{*;}
-keep class org.w3c.dom.**{*;}
-keep class sun.misc.**{*;}
-keep class android.support.**{*;}
-keep class org.achartengine.**{*;}
-keep class org.codehaus.**{*;}
-keep class jcifs.**{*;}
-keep class com.baidu.**{*;}

-keep class org.greenrobot.eventbus.**{*;}
-keep class butterknife.internal.**{*;}
-keep class com.bumptech.**{*;}
-keep class com.google.gson.**{*;}
-keep class org.hamcrest.**{*;}
-keep class com.squareup.**{*;}
-keep class rx.android.**{*;}
-keep class com.jakewharton.**{*;}
-keep class com.orm.**{*;}
-keep class rx.**{*;}
-keep class com.uniopay.**{*;}
-keep class unionay.**{*;}
-keep class android.databinding.**{*;}

-keep class com.cocosw.favor.** { *; }
-dontwarn com.cocosw.favor.**

-keep class butterknife.** { *; }
-keep class **$$ViewBinder { *; }
-keep class cn.com.ssii.aknowledge.model.entity.**{*;}
-keep class cn.com.ssii.core.**{*;}
-dontwarn cn.com.ssii.core.**

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keep class org.litepal.** {
    *;
}

-keep class * extends org.litepal.crud.LitePalSupport{
    *;
}

-dontwarn com.squareup.picasso.**

-dontwarn butterknife.internal.**
-dontwarn jcifs.http.**
-dontwarn org.codehaus.**
-dontwarn rx.internal.util.**
-dontwarn okhttp3.**
-dontwarn android.databinding.**

-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-keep class m.framework.**{*;}
-dontwarn cn.sharesdk.**
-dontwarn com.sina.**
-dontwarn com.mob.**
-dontwarn **.R$*

-dontwarn com.f2prateek.rx.preferences2.**
-keep class com.f2prateek.rx.preferences2.** { *;}
