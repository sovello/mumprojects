### Library Management System


Note: For details about the specifications of this assignment
visit the Wiki

#### Organization of the Packages

##### librarymanager.controllers

This package is for all controllers for the system.
Currently there's one controller, this needs to be broken down into sub-controllers that are more 
succinct and perform only specific tasks like reading data from a form and sending data to storage.
(This wasn't done due to the short time to complete the project)

##### librarymanager.main

This package holds the main class for the system. This is where you should start
running the application

##### librarymanager.menu

Has the setup for all the menu items in the system set out by different roles

##### librarymanager.models

This contains all base classes which build up the system.
All model classes implement the `Model` interface which is seriablizable and they must
implement two methods `public String getKey()` which returns the key for the model
to be used as the key in the HashMaps, and must also implement `public String getSaveType()'
which returns the _String type for this Object_, like Books, LibraryMembers, and CheckoutRecords.

##### librarymanager.storage

This is the data storage API. It mainly has only two methods, 
`public static void save(Model model)` and `public static Object read(String modelType)`
So, everytime you read a model Type, you get back a HashMap object.
This is so to make it easy for the `StorageAccess` class not to be concerned with handling the different types of model objects to read.

The package has a _subdirectory called `data`_ which holds all the serialized data files.

##### librarymanager.ui

This package keeps has all the GUI objects. All the model classes have a corresponding `ModelForm` class. Each form class creates a form object for the model. It is easy to pass a form object from the UI to the controller and make the necessary processes.

All form classes implement a `Form` interface. They must implement `public FTextField[] getRequiredFields()` which returns a list of required fields for a form. The interface has a static method `public static boolean isValid(Form form)` which checks for validity.

The validation and form field rules handling needs to be improved to be more dynamic. The only validation which can be easily done on the fly is checking for required fields. There's a need to have a method like `public FieldRules[] getFormRules(Form form)` which will get a list all the rules defined for the form and do the validation from the user interface.

Classes with _*ListView_ like `BooksListView` just display a list of objects in a tabular format.

The class `MainWindow` is one which creates and maintains the user interface layout. It has one static method `public static BorderPane getBorderPane()` which returns the system's border pane, to be used by the controller and other classes in the `librarymanager.ui` package.


##### librarymanager.user

This package currently has mainly two classes and an enum of user roles.

The `Authentication` class handles authentication and authorization during the login process. The `User` class is the Model class for the user, and the `UserRole` defines an _enum_ of  user roles.

The Authentication class doesn't handle sessions for now.

##### librarymanager.util

This package has utility classes for creating test data. You can add data from the system's user interface, but for testing purposes, you can also create the data by running either an individual `Create*` file in the package or by running the `Util` class to create all the test data at once.

#### space.fugit.jfx

The package contains wrapper classes for the different JavaFX components. It creates an abstration layer to make it easy to handle these components. Each customized component is 
prefixed with F, though there are not many of them.

Moving forward, the plan is to have especially for the _TextFields_ generic fields for handling different primitive data types in Java that are expected to be collected from a form. For a example an `IntegerTextField`, a `StringTextField`, a `TextTextField` and so this way will make it easy to have the rules for the forms much more easy to create and implement.