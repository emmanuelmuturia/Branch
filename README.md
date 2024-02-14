# Branch



## Overview
Branch is an Android application that utilises a REST API built and maintained by Branch International to fetch and interact with customer data...

## Table of Contents

1. [Background](#Background)
2. [Architecture](#Architecture)
3. [Testing](#Testing)
4. [Screenshots](#Screenshots)
5. [Credits](#Credits)
6. [Trivia](#Trivia)
7. [License](#License)

## Background
- This project is an implementation of the Take Home Assignment offered by Branch International...
- It utilises [The Branch API](https://android-messaging.branch.co/) which has the following endpoints:

### 1. /api/messages
- This endpoint allows the user to fetch customer messages using a GET request...
- It also allows for the addition of a message as an agent using a POST message...

### 2. /api/login
- This is the authentication endpoint that provides an authentication token if the username and password provided match the configured criteria...

### 3. /api/reset
- To reset the customer messages to their original state, this endpoint can be called using a POST request...

## Architecture
- Branch is a multi-module Android app implemented using the Model-View-ViewModel (MVVM) Architectural Pattern...
- It is divided into the following modules:

### 1. :app
- This is the main module that houses MainActivity and BranchApplication...
- MainActivity acts as the entry-point of the app following the Dependency Injection implementation while BranchApplication initialises the app and any other required dependencies...

### 2. :authentication
- This module contains the Login Screen and its ViewModel...
- It provides the Login functionality by making an API call using the POST method to retrieve an authentication token from the REST API server...

### 3. :commons
- It contains all of the shared resources within the app such as shared UI components, state screens, and the theming materials...

### 4. :messages
- This module houses both the Messages Screen and the Conversation Screen as well as their ViewModels...
- They both implement the Repository pattern to perform a GET request in order to retrieve customer messages from the remote server...

### 5. :navigation
- This module has both the NavGraph and Routes implementations which allow for navigation between different screens using [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)...

### 6. :network
- This is the core module of the app that contains all of the network-related implementations...
- It provides all the configurations and resources needed to make successful API calls using Retrofit2, OkHTTP3, and Kotlin Serialization...

### 7. :settings
- This is an auxiliary module that contains the About Screen and its ViewModels...
- It provides the app's information as well as the Terms & Conditions and Privacy Policy for Branch International...

## Testing
- Branch has been tested based on the following categories:

### 1. Unit Tests


### 2. Robo Tests


### 3. Security Tests


## Screenshots


### Credits
Branch has been built using the following Tools, Technologies, and Libraries:

### 1. Jetpack Compose
- The app has utilised Google's modern UI toolkit ([Jetpack Compose]()) for its User Interface that prioritises the Kotlin-first approach of Android App Development (AAD)...

### 2. Dagger-Hilt
- To provide dependencies in an easy and modular fashion, [Dagger-Hilt]() has been used in conjunction with:

### 3. Kotlin Symbol Processing (KSP)
- For faster builds in regards to Annotations, [KSP]() has been implemented in form of a plugin...

### 4. Retrofit2
- As a core library, [Retrofit2]() has been utilised for performing remote calls to the REST API through a level of abstraction...

### 5. OkHTTP3
- In addition to Retrofit2, [OkHTTP3]() has also been implemented to aid in the configuration of the remote calls by providing an Interceptor and specifying the expected data type as JSON...

### 6. Kotlin Serialization
- During the remote calls, the returned data has been serialized and deserialized using the [Kotlin Serialization Plugin]()...

### 7. Splash Screen
- The app has implemented its Splash Screen using Google's [Splash Screen API]() that uses its own custom theme alongside the app's theme...

### 8. LeakCanary
- To detect and fix memory leaks, [LeakCanary]() has been implemented...

### 9. MockK, Kotlin Coroutines Test, and Robolectric
- During testing [MockK]() has been used to mimic the dependencies that are to be tested resulting in a close-to-real test case scenario...
- In addition to MockK, [Robolectric]() has been utilised to provide a simulated Android environment to run test cases that rely on Android-specific dependencies...
- The Unit Tests used the [Kotlin Coroutines Test]() library to provide the Coroutine Scopes for the suspending functions that were being tested...

### 10. Firebase
- Branch has incorporated Firebase Performance and Firebase Analytics for conducting app analysis...

## Trivia
- Branch has used a hybrid modularisation technique where each of the modules listed above has implemented sub-modules that match the Presentation, Domain, and Data Layers in Android...

## License
