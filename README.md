# Mobile_Application

Hello Sir,

You will find here all the work done for this project. This application provides multipal functionnalities.

Requirements
This application must be available offline.
A refresh button allows the user to update its accounts.
Access to the application is restricted
Exchanges with API must be secure ( with TLS)
This application is available offline. Every account is stored in a database and thus accessible while offline. You can only consult your account when offline. You will need connection the first time in order to download the accounts and store them in your local database.

This application has various functionnalities. First, you must confirm your identity using either a pin code or a fingerprint (biometric access was implemented and works only on phones that have this option). Then when connected to the internet, you can refresh the app to get all the accounts stored online on the API.

Communication with the API is secured as we use Retrofit which is a client used for reting API. This client can only communicate with secure APIs (APIs that hava a SSL certificat) If an API does not have a certificate then the connection is refused. We will also use Retrofit to send the created account to the API.

Indeed, we added the possibility to create accounts, specifying the Name, Amount, IBAN and currency (id is auto-generated). This account is then sent on the resting API. You can cancel the creation procedure anytime using the top left button.

Regarding other aspects of security. We used proguard to secure our code and especially the URL of the API. We also hid the file containing this sensible information. Retrofit is doing all the needed part regarding communication security.

Here are some Screen shots of my APP. Hope you will like it !

I had a lot of fun discorvering mobile developpment and the android studio IDE


![Alt text](https://github.com/HattorINT/Mobile_Application/blob/main/Login.PNG)

![Alt text](https://github.com/HattorINT/Mobile_Application/blob/main/Accounts.PNG)

![Alt text](https://github.com/HattorINT/Mobile_Application/blob/main/AddingAccounts.PNG)

![Alt text](https://github.com/HattorINT/Mobile_Application/blob/main/Refresh_Display.PNG)
