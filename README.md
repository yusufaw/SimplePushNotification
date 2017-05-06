# SimplePushNotification
Simple push notification in Android using Firebase Cloud Messaging (FCM)

Send to Firebase using API

```
POST /fcm/send HTTP/1.1
Host: fcm.googleapis.com
Content-Type: application/json
Authorization: key=<Your Server Key>

{
   "to":"/topics/news",
   "data":{
      "title":"App Update ya",
      "body":"Please update..."
   }
}
```
