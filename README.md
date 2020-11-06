## 直播lint检查工具仓库

编译完成后，将"/lint/build/libs"的"lint.jar"文件复制到（mac下）".android/lint"目录下（没有lint目录，新建一个）即可。</br>

如果没有出现实时的静态检测提示，rebuild项目，仍然失败重新Android Studio</br>

如果多次尝试仍然没有实时提示，只能使用```./gradlew lint```命令进行手动检查。</br>

**app/build/reports/lint-results.html**生成的该文件中可以查看检查的详细结果。</br>