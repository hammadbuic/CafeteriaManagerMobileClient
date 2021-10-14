# CafeteriaManagerMobileClient
This repository contains the source code for mobile client of cafeteria manager


Locate the code in branch section under master branch
If you are running application in deployment/published mode then you've the ip http://-.-.-.:Some Port
you've to add that link in the android project
open android studio and open the downloaded code init
Now in RetrofitClient.java under java/com/example/project you've to change the BASE_URL Address string and set the ip you've
also in the files
![image](https://user-images.githubusercontent.com/50557442/137401636-d695635e-d5d9-4fc7-8259-7ff1cf56e12b.png)

you've to change the ip address as shown above before "/api/ItemImages"
also for 
![image](https://user-images.githubusercontent.com/50557442/137401904-79926987-366d-48fb-a8c5-2379bfbc801d.png)


For Development Serve/ by using code you've to install Conveyor by Keyuoti in Visual Studio Refer to Backend Repository
after that you can get the ip Address from there to change Ip For the App.


After doing that you can build APK file and publish it for the apps.
->App is tested on physical device not on emulator but i think it will work on emulator too
->App is a bit unstable
->Feel free to improve it and guide me
