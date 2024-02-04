
set temp=temp

mkdir %temp%
attrib +h %temp%
copy /Y src %temp%

cd %temp%
javac GuiWindow.java ListRightClickListener.java Main.java SaveLoad.java Task.java TaskCellRenderer.java TaskManager.java

cd ..

del todo_list.jar

jar cfm todo_list.jar src\META-INF\MANIFEST.MF -C %temp% .
rmdir /S /Q %temp%
java -jar todo_list.jar

pause
