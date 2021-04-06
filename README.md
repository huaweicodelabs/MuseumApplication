# Find Nearby Museums and Visit with Virtual Guide Codelab

## Table of Contents

 * [Introduction](#introduction)
 * [Installation](#installation)
 * [Configuration ](#configuration )
 * [Supported Environments](#supported-environments)
 * [Sample Code](#Sample-Code)
 * [License](#license)


## Introduction
    This codelab encapsulates APIs of the HUAWEI Nearby Service, HUAWEI Awareness Kit, HUAWEI Site Kit, HUAWEI Machine Learning - Text to Speech.
    It provides sample Nearby Service Message, Site Kit Nearby Search, Machine Learning Text to Speech
    and Awareness Kit Location and Time Barrier usage for your reference or usage.
    Before you use this codelab, it's assumed that you already have a HUAWEI developer account and have already
    created an app to implement the HMS capabilities.

    If you haven't,    please refer to https://developer.huawei.com/consumer/en/doc/start/introduction-0000001053446472
    and https://developer.huawei.com/consumer/en/doc/development/AppGallery-connect-Guides/agc-introduction.

    model:       The package name which refers to a exhibit object.
    ui:          The package name which shows the interface of the app to the user.
    constant:    The package which contains the constant values of the project.
    utils:       The Package which contains the utility classes.
    services:    The Package which contains the services.


## Installation
    Before using the code, check whether the Android Studio environment has been installed.
    Download the the project by zip or clone in Github.
    Wait for the gradle build in your project.

## Supported Environments
	•	Android Studio 7.0 or later version
	•	Java JDK 1.8 or later version
	•	EMUI 5.0 or later version
	•	HMS Core (APK) 5.0.0.300 or later version

## Configuration
    1. Register and sign in to HUAWEI Developers.
    2. Create a project and then create an app in the project, enter the project package name.
    3. Go to Project Settings > Manage APIs, find the Nearby Service, Awareness Kit, Machine Learning Kit and Site Kit, and enable it.
    4. Go to Project Settings > General information, click Set next to Data storage location under Project information,
    and select a data storage location in the displayed dialog box.
    5. Download the agconnect-services.json file and place it to the app's root directory of the project.
    6. Add the Maven repository address maven {url 'https://developer.huawei.com/repo/'} and plug-in class path
    'com.huawei.agconnect:agcp:1.4.1.300' to the project-level build.gradle file.
    7. Add apply plugin: 'com.huawei.agconnect' to the last line of the app-level build.gradle file.
    8. Configure the dependency 'com.huawei.hms:awareness:1.0.7.303' , 'com.huawei.hms:site:5.0.3.302',
    'com.huawei.hms:nearby:5.0.4.301' and 'com.huawei.hms:ml-computer-voice-tts:2.0.4.300' in the app-level buildle.gradle file.
    9. Synchronize the project.

## Sample Code
    This uses GuideUtils class in the project in order to manage Nearby Service capabilities.
    It also uses SearcUtils class in the project in order to manage Site Kit and Awareness Kit capabilities.
    Machine Learning Text to Speech capabilities are managed by TTSUtils class.
    AwarenessServiceManager helps to create a foreground service for the notification system.
    The following describes methods related with these capabilities in the project.

    1) Search Museums with Site Kit
    You can search museums using this method and a location information.
    Code location src/main/java/com.hms.codelabs.museum/utils/SearchUtils.kt

    2) Add barrier to Awareness Kit
    You can add more than one barriers by combining the barries.
    Code location src/main/java/com.hms.codelabs.museum/utils/SearchUtils.kt

    3) Update Barrier
    You can update Awareness Client with a barrier.
    Code location src/main/java/com.hms.codelabs.museum/utils/SearchUtils.kt

    4) Create a foreground service for awareness service
    You can define a foreground service which handles barrier triggers and a notification system.
    Code location src/main/java/com.hms.codelabs.museum/services/AwarenessServiceManager.kt

    5) Start Scanning Beacons
    You can start beacon discovery
    Code location src/main/java/com.hms.codelabs.museum/utils/GuideUtils.kt

    6) Download exhibit information
    You can download the exhibit information as soon as the beacon is discovered
    Code location src/main/java/com.hms.codelabs.museum/utils/GuideUtils.kt

    7) Remove exhibit information
    You can remove exhibit information as soon as the beacon signal is lost
    Code location src/main/java/com.hms.codelabs.museum/utils/GuideUtils.kt

    8) Retrieve the closest beacon
    You can retrieve the closest beacon information every time a beacon's distance changed
    Code location src/main/java/com.hms.codelabs.museum/utils/GuideUtils.kt

##  License
    Find Nearby Museums and Visit with Virtual Guide Codelab is licensed under the [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
