
#### What is this

![alt text](https://raw.githubusercontent.com/vigilancer/RemoveFeedbackSmilesFromAndroidStudioPlugin/master/images/android-studio-smiles.gif)


#### How to use

1. Open project
2. Help -> Find Action...
3. "Remove feedback smiles from status bar"


#### Development

1. Don't use openjdk 11.
Install [JB SDK Bintray Downloader](https://intellij-support.jetbrains.com/hc/en-us/articles/206544879),
download latest 1.8 JDK and set it for plugin project. This will automatically setup suitable jdk
 to test plugin in sandboxed Android Studio. Otherwise you will have all kind of issues.

2. Don't trust documentation about plugin development. Despite misleading official tutorials,
classes should be placed inside packages, don't place them on top level (wrong: `/main/java/some.java`).

3. When developing plugin for Android Studio it would be better to use for development IntelliJ IDEA version
that matches version latest Android Studio is based on (or version you testing locally).
[Choose yours](https://www.jetbrains.com/intellij-repository/releases).


#### Contribute

Sadly by now you have to remove smiles with "Remove feedback smiles from status bar" every time you open project in Android Studio.
I would love to add automatic removing. If you have any ideas how to achieve this please create an issue.
