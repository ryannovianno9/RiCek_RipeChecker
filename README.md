# RiCek_RipeChecker

## Team ID : C23-PS174
### Theme : Food Accessibility, Agribusiness, and Food Security

### Mentor : 
1. Ali Naufal Ammarullah - ID B23-A070
2. Henrico Aldy Ferdian - ID B23-A018

### Team Members : 
1. Muhammad Dajuma Fenori - M151DSX1310 - Universitas Brawijaya - Machine Learning - Active
2. Reni Tustiana Surahmat - M295DSY2312 - Universitas Padjadjaran - Machine Learning - Active
3. Ryan Novianno - M169DSX1744 - Universitas Gadjah Mada - Machine Learning - Active
4. Faisal Akbar Junivo Handani - A265DSX2078 - Universitas Muria Kudus - Mobile Development - Active
5. Javier Al Faiza - CC297DSX0799 - Universitas Pamulang - Cloud Computing - Active
6. Rezha Aulia - C360DKX4366 - Universitas Telkom - Cloud Computing - Active

### App Summary : 
RiCek(Ripe Checker) is Android application that has feature to check fruits ripeness specifically for apple, banana, and mango. RiCek can help users to check their ripeness fruit by upload  their fruit image to our application. Next, there will be a pop up that shows level of ripeness of fruits after users upload an image. 

## Machine Learning 

### Libraries : 
1. Tensorflow
2. Flask
3. Keras
4. Numpy

### Test Model
1. Download RiCek model [RiCek](https://drive.google.com/file/d/13rma21dnkfG00UQUbsYsQfZjzO0L0ORL/view?usp=sharing)
2. Put Model and [App.py](https://github.com/juuu28/RiCek_RipeChecker/blob/main/machine_learning/Flask/app.py) in the same folder
3. Run App.py in Python Terminal
4. POST method
```
POST https://ricek1-oes4ruxepa-et.a.run.app/
```
Example :
> Body `form-data`
> 
> key: img | type: file | value: {fruitimage}.jpg


5. Response
```
{
    "confidence": "0.9998049",
    "predicted_class": "fresh_banana"
}
```

## Cloud Computing
Cloud Computing steps can be checked [here](https://github.com/juuu28/RiCek_RipeChecker/blob/main/cloud_computing/cloud_run_deployment/README.md)

## Mobile Development 

### Library :
1. Jetpack Compose
2. Retrofit
3. Coil

### Install 
RiCek APK can be downloaded [here](https://drive.google.com/file/d/1Km-ZB3rHf1V2ff3RqejvLu3WKaunetoE/view?usp=sharing)

### Run
Install APK to Emulator or your Android phone

### Feature : Classifying Fruits Ripeness



