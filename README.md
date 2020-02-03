# cabifyMobileChallenge
First test for cabify android engineer job application

# Android native application using architecture MVVM builded in Kotlin


## Attends Google design recommendations with
- LiveData
- ViewModels
- Observable Objects

## structure and design:
[![An old rock in the desert](https://miro.medium.com/max/567/1*4EgdWEoVDFtQxQiU9Dk-eg.png)](https://medium.com/m)


Also I did use:

- Couchbase Lite 2.5 for data persistence
- Retrofit2 for http requests
- Anko Layouts for look&Feel
- Android API 29 for debugging

## App description:
in Cabify app, i follow the MVVM pattern, which tries to decouple the retrieval of data, view logic, and presentation into three  areas. For software development reason i did use the next configuration for relax coding.

## Data:
I did use "pojos" in kotlin (data class) for the real data representation and object abstraction, these objects used for retrive all types information (database and dummy) to interact with UI and UX. you can find it in "data" folder.

## Repositories:

## UI:

## ViewModels:
