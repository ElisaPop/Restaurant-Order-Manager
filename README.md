# Restaurant-Order-Manager

The application is meant to implement a restaurant management system. The system should have three types of users: administrator, waiter and chef. The administrator can add, delete and modify existing products from the menu. The waiter can create a new order, add elements from the menu and compute the bill from an order. The chef is notified each time it must cook food ordered through a waiter.

## Implementation

The user interface consists of 3 more packages, each containing a View and a Controller. The Scenes are available to be accessed from one another, because of a ManuBar. Each scene supports the operations specific to the current scene. 

### Menu scene
For the Menu scene, the “Add” button adds the field to the HashSet of MenuItems, the table that is always shown and updated in real time; In order to edit an item: you have to choose an item from the comboBox and click on edit. After editing the fields, click on “Add” to update the table, Delete: an element, by choosing it from the ComboBox.  We can also combine two elements that already exist in the table by selecting them in the ComboBoxes from the bottom part of the interface. Pressing “Combine” will add the combined MenuItem in menu. 


![image](https://drive.google.com/uc?export=view&id=1QLp5BILtEdTnUXvbzK7ssWeujFZ6L5gq)

It’s a relatively simple ui, having used a ManuBar with 3 menu items, two GridPanes in which on GridPane has the table with the title label and the other one has 3 TextFields and 3 ComboBoxes with 3 Labels and 4 Buttons. 
The ID label is always uneditable. The only way it can be changed  is by choosing an item in the ComboBox from the upper right corner and clicking the Edit button. By doing that, all the fields are filled with all the current fields from the specified ID. You can change any field and click on the Add button: This will update the item in the Serialization file. However, each time you want to edit an item, you have to click on the Clear button afterwards because as long as the ID field is not null, the item at the current ID will keep getting edited.

### Chef scene
The Waiter Scene contains a ScrollPane which will add a new TitledPane when you press the “Add Order” button. Afterwards, you can select an order from the upper ComboBox in order to print it, or add a new MenuItem to it by selecting an item from the lower ComboBox. 

![image](https://drive.google.com/uc?export=view&id=1YUuyNVuDRhYThxJwkCZ1-2EhZh3Q7aje)

### Waiter scene
The Chef Scene contains a TextArea which Observes the Orders from Restaurant in order to notify the chef when a new one is added.

![image](https://drive.google.com/uc?export=view&id=150-8wOse8diwLR5zhNvr55vrWOHvAY66)


## Use-case diagram

This is the general architecture of the model. It consists of the model training and application part, tied together with TensorflowLite.
After training, the model is exported as a tflite file and then added to the application's resources folder, where it can be used locally.

![image](https://drive.google.com/uc?export=view&id=13Ivo0aNclDonAyLy4lVoxOUIBRHWIZut)
