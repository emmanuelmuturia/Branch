# Branch

<center>
  ![The Branch Logo](https://github.com/emmanuelmuturia/Branch/assets/55001497/4f47e342-82aa-4425-ab95-6426aeb1b9f0)
</center>

## Overview
Branch is an Android application that utilises a REST API built and maintained by Branch International to fetch and interact with customer data...

## Table of Contents

1. [Background](#Background)
2. [Architecture](#Architecture)
3. [Testing](#Testing)
4. [Credits](#Credits)
5. [Trivia](#Trivia)
6. [Future](#Future)
7. [License](#License)

## 1) Background
- This project is an implementation of the Take Home Assignment offered by Branch International...
- It utilises [The Branch API](https://android-messaging.branch.co/) which has the following endpoints:

### a) /api/messages
- This endpoint allows the user to fetch customer messages using a GET request...
- It also allows for the addition of a message as an agent using a POST message...

### b) /api/login
- This is the authentication endpoint that provides an authentication token if the username and password provided match the configured criteria...

### c) /api/reset
- To reset the customer messages to their original state, this endpoint can be called using a POST request...

## 2) Architecture
- Branch is a multi-module Android app implemented using the Model-View-ViewModel (MVVM) Architectural Pattern...
- It is divided into the following modules:

### a) :app
- This is the main module that houses MainActivity and BranchApplication...
- MainActivity acts as the entry-point of the app following the Dependency Injection implementation while BranchApplication initialises the app and any other required dependencies...

### b) :authentication
- This module contains the Login Screen and its ViewModel...
- It provides the Login functionality by making an API call using the POST method to retrieve an authentication token from the REST API server...

### c) :commons
- It contains all of the shared resources within the app such as shared UI components, state screens, and the theming materials...

### d) :messages
- This module houses both the Messages Screen and the Conversation Screen as well as their ViewModels...
- They both implement the Repository pattern to perform a GET request in order to retrieve customer messages from the remote server...

### e) :navigation
- This module has both the NavGraph and Routes implementations which allow for navigation between different screens using [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)...

### f) :network
- This is the core module of the app that contains all of the network-related implementations...
- It provides all the configurations and resources needed to make successful API calls using Retrofit2, OkHTTP3, and Kotlin Serialization...

### g) :settings
- This is an auxiliary module that contains the About Screen and its ViewModels...
- It provides the app's information as well as the Terms & Conditions and Privacy Policy for Branch International...

## 3) Testing
- Branch has been tested based on the following categories:

### a) Unit Tests
- The Unit Tests were written using MockK and Robolectric:

![The Unit Tests](https://github.com/emmanuelmuturia/Branch/assets/55001497/a2433afd-2e9f-4f45-b97e-5ccbac38e0b8)


### b) UI Tests
- The UI Tests have been performed using a Robo Test by Firebase Test Lab:

![The Robo Test](https://github.com/emmanuelmuturia/Branch/assets/55001497/ecb85b7d-ff64-406b-9d48-2fc93824d3eb)


### c) Security Tests
- The Security Tests were conducted using Snyk:

![The Security Test](https://github.com/emmanuelmuturia/Branch/assets/55001497/40d86620-fb4c-4200-8769-15e3f17b5f22)


## 4) Credits
- Branch has been built using the following Tools, Technologies, and Libraries:

### a) Jetpack Compose
- The app has utilised Google's modern UI toolkit ([Jetpack Compose](https://developer.android.com/jetpack/compose)) for its User Interface that prioritises the Kotlin-first approach of Android App Development (AAD)...

### b) Dagger-Hilt
- To provide dependencies in an easy and modular fashion, [Dagger-Hilt](https://dagger.dev/hilt/) has been used in conjunction with:

### c) Kotlin Symbol Processing (KSP)
- For faster builds regarding Annotations, [KSP](https://kotlinlang.org/docs/ksp-overview.html) has been implemented as a plugin...

### d) Retrofit2
- As a core library, [Retrofit2](https://square.github.io/retrofit/) has been utilised for performing remote calls to the REST API through a level of abstraction...

### e) OkHTTP3
- In addition to Retrofit2, [OkHTTP3](https://square.github.io/okhttp/) has also been implemented to aid in configuring the remote calls by providing an Interceptor and specifying the expected data type as JSON...

### f) Kotlin Serialization
- During the remote calls, the returned data has been serialized and deserialized using the [Kotlin Serialization Plugin](https://kotlinlang.org/docs/serialization.html)...

### g) Splash Screen
- The app has implemented its Splash Screen using Google's [Splash Screen API](https://developer.android.com/develop/ui/views/launch/splash-screen) which uses its custom theme alongside the app's theme...

### h) LeakCanary
- To detect and fix memory leaks, [LeakCanary](https://square.github.io/leakcanary/) has been implemented...

### i) MockK, Kotlin Coroutines Test, and Robolectric
- During testing [MockK](https://mockk.io/) has been used to mimic the dependencies to be tested resulting in a close-to-real test case scenario...
- In addition to MockK, [Robolectric](https://robolectric.org/) has been utilised to provide a simulated Android environment to run test cases that rely on Android-specific dependencies...
- The Unit Tests used the [Kotlin Coroutines Test](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/) library to provide the Coroutine Scopes for the suspending functions that were being tested...

### j) Firebase
- Branch has incorporated [Firebase Performance](https://firebase.google.com/docs/perf-mon/get-started-android) and [Firebase Analytics](https://firebase.google.com/docs/analytics/get-started?platform=android) for conducting app analysis as well as [Firebase Test Lab](https://firebase.google.com/docs/test-lab/android/get-started) for UI Testing...

## 5) Trivia
- Branch has used a hybrid modularisation technique where each of the modules listed above has implemented sub-modules that match the Presentation, Domain, and Data Layers in Android...

## 6) Future
- Branch has been fully built and no future developments are in place...

## 7) License
```
MIT License

Copyright (c) 2024 Emmanuel Muturia™

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
