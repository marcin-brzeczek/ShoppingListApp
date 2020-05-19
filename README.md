# Shopping List App
Simple shopping list application based on MVI architecture with RxJava

## Requirements
  - \[x] Done
  1. The app should consist of three views
 - list of current shopping lists
 - list of archived shopping lists
 - shopping list details where you can add/delete items etc. Please notice that those
operations should be only possible when a list isn’t archived.

  2. Use a database to store apps’ data.
  3. Lists should be sorted by date.
  4. Set min SDK version to 21
  5. The project must be written in:
  5. If Senior: Kotlin (it would be good to have one class (that's not a model) in Java)

  7. Use Android Studio / Gradle
  8. Support any screen size & resolution
  9. The app should work in landscape and portrait
  10. Write unit tests for one class -> 
  [DetailsListViewModelTest](https://github.com/martinshare/ShoppingListApp/blob/master/app/src/test/java/mbitsystem/com/shopping/DetailsListViewModelTest.kt), [ShoppingItemDaoTest (local db test)](https://github.com/martinshare/ShoppingListApp/blob/master/app/src/androidTest/java/mbitsystem/com/shopping/ShoppingItemDaoTest.kt)
  11. Please prepare proper architecture design in this project MVVM + RX
  
  ### Creating lists
![creating_lists](https://user-images.githubusercontent.com/24879552/82318258-d50e8500-99cf-11ea-8c55-3980837f7e43.gif)

  ### Modifying details shopping items
![modified_details_list](https://user-images.githubusercontent.com/24879552/82318357-ff604280-99cf-11ea-92de-e155fef72455.gif)

  ### Adding shopping list to archive
  ![adding_to_archive](https://user-images.githubusercontent.com/24879552/82318721-9a591c80-99d0-11ea-8415-5d604be6eeec.gif)
