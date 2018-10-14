# Unconnectify-Re

This is an attempt to rewrite the [Unconnectify android App](https://github.com/mdevlab/unconnectify) using newer APIs, especially the [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager/).

The Unconnectify app allows setting alarms for disabling and re-enabling connections (Wifi, Bluetooth and Hotspot), while offering some flexibility as to the time, the date and the frequency of these alarms.


## Libraries used in this project
- [CardView](https://developer.android.com/reference/android/support/v7/widget/CardView)
- [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView)
- [Material Design](https://developer.android.com/guide/topics/ui/look-and-feel/)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LivaData](https://developer.android.com/topic/libraries/architecture/livedata)
- [ConstraintLayout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout)
- [Room](https://developer.android.com/topic/libraries/architecture/room)
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager/)
- [Koin](https://github.com/InsertKoinIO/koin) (Light-weight dependency injection library)
- [Barista](https://github.com/SchibstedSpain/Barista) (UI testing library)


## Key improvements
- For job scheduling, this project uses `WorkManager` compared to the previously used [Evernote Android job](https://github.com/evernote/android-job). `WorkManager` chooses the appropriate way and best option to schedule background tasks depending on the device API level, included dependencies and the app's state. (Checkout [this link](https://github.com/firebase/firebase-jobdispatcher-android#comparison-to-other-libraries) for a comparison of the other available options for job scheduling)
- For data persistence, this project uses `Room` compared to the previously used low-level [SQLite](https://developer.android.com/training/data-storage/sqlite). `Room` guarantees compile-time verification of the SQL queries and eliminates the need to write boilerplate code for converting between SQL queries and data objects.
- This project stands on a better architecture: the alarms list screen was built using the repository pattern, `ViewModel` was used to handle UI related data in a lifecycle-aware manner, `LiveData` to post data changes to its decoupled observers and dependency injection is used with the help of [Koin](https://github.com/InsertKoinIO/koin).
- This project contains both unit and UI tests for many of its components.
- This project uses `ConstrainLayout` in order to flatten and simplifiy the -XML- layouts.


## Missing features
- Notifications weren't implemented in this project (which would be a great opportunity to work with the latest [Notifications API](https://developer.android.com/guide/topics/ui/notifiers/notifications) features)
- Hotspot handling is not implemented due to changes made in Android Oreo regarding controlling a device's Hotspot.


## Challenges
- APIs for controlling conectivity features on Android devices are pretty restricted, and can varry depending on the Android api level. Controlling cellular data is not possible on [non-rooted devices](https://stackoverflow.com/questions/31120082/latest-update-on-enabling-and-disabling-mobile-data-programmatically) for example, and as mentioned above, hotspot handling changed in Android Oreo.
- With `WorkManager`, it isn't possible to create a `PeriodicWorkRequest` with an initial delay (For example, scheduling a job X that would start after 2 hours and repeat every week). A workaround I used to solve this is to use a `OneTimeWorkRequest` with the initial delay that then schedules the `PeriodicWorkRequest` (With the previous example, this means to schedule a job Y that is triggered after 2 hours that then schedules job X to be repeated every week).


## Demo
![alt text](https://github.com/husaynhakeem/UnconnectifyRe/blob/master/app/src/art/art.png)
