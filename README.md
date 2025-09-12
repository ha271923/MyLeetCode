# MyLeetCode

### A. Build Environment
1. Android Studio Iguana 2023.2.1 patch2 <br>
2. Settings\Build Execution, Deployment\Build Tools\Gradle\Gradle Projects:GRADLE_LOCAL_JAVA_HOME\jbr 17.0.9 <br>
3. Project Structure\Project\Android Gradle Plugin Version\Gradle Version:8.4 <br>

### B. Run Unit Test
1. Right-Click on test\XXXX.java <br>
2. Select Run 'XXXX' <br>
3. See the XXXX execution result in Run Window if you called System.out.println API in XXXX.java <br>

### Troubleshooting
Q: When I use another version of Android Studio IDE, I cannot see the Run 'XXXX' option when right-clicking on test\XXXX.java. <br>
A: Create a new Android project in this Android Studio IDE and check the default ExampleUnitTest.java. If the option appears and runs successfully, copy your existing test\XXXX.java files into this new project. <br>
