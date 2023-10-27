# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\new\AppData\Local\Android\sdk6/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-ignorewarnings
#-dontwarn org.apache.commons.**
#-keep class org.apache.http.** { *; }
#-dontwarn org.apache.http.**
#-dontwarn com.squareup.okhttp.**
#-dontwarn okio.**
#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#-keepattributes Signature
#-keepattributes Exceptions
#-keepnames class com.fasterxml.jackson.databind.** { *; }
#-dontwarn com.fasterxml.jackson.databind.**
#-keepattributes InnerClasses,
#
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
#-keep @org.parceler.Parcel class * { *; }
#-keep class **$$Parcelable { *; }
#-keep interface org.parceler.Parcel
#-keepattributes Signature,InnerClasses,EnclosingMethod

#-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
#    boolean mShiftingMode;
# }


# -keepclasseswithmembers class * {
#     @retrofit2.http.* <methods>;
# }