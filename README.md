# cabifyMobileChallenge
First test for cabify android engineer job application

# Android native application using architecture MVVM builded with Kotlin language

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
Cabify Store App, i followed the MVVM pattern, which tries to decouple the retrieval of data, view logic, and presentation into three areas. For software development reason and relax coding i did use the next configuration:

## Data:
I did use "pojos" in kotlin (data class) for the real data representation and object abstraction, these objects used for retrive all types information (database and dummy) to interact with UI and UX. you can find it in "data" folder.

## Repositories:
The repositories have logic for getting, process and share information with domain of App, in my case, i had three big entities of
information: Products of catalog, Order, and Discounts these objects were manipulated using Retrofit for networking, couchbase lite for database persistence and LiveData for synchrony. 

## UI:
The app's User interfaces are stored in folder "ui" you can find a main activity (tabbet for navigation), final activity (dummy paid feedback) and set of Fragments for show cabify products and order's details, also i divided recycleViews adapter in a diferent directory for Readability. 

## ViewModels:
Share LiveData objects between Fragments using viewModels in this folder you can find the classes what i did use for this task.

## Using cabify store application:

- You might pick every product would you like from product catalog, (this info is loaded using Cabify REST api)
- when your products have been selected you can see then at Order tab and remove from list with simple tab over item description.
- Order's discounts and Total will be updated with every product you add or remove from product list or Order's detail list.
- finish your order with tab over "place your order " button an remove all from your cart.