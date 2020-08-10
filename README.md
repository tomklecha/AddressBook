# NuomAddressBook

Sample contact book application created as a test task.
Application shows empty contact list with a place holder. Addinig new contact require input of for neccesary contact data:
- first name,
- last name,
- email,
- phone number.

This data must be valid before save option is available, 
address input is optional, but it require to be empty, or without special characters.
Added contact is highlighted in the list and with the click we can see all data about the contact.
Also we can search for a contact with search bar, by letters of first name or last name.
Contacts are sorted ascending within the list.

# Technology

- Language: Kotlin - 100%
- Structure - MVVM using LiveData
- Jetpack components - Room (persistance layer), Navigation
- Testing - JUnit, Mockk

# Comments

- Technology used for observing data was Android's Live Data. Since lack of knowledge of RXjava,
i delivered the application in technology, I felt more familliar with.
With more time to study, I would definitely try RXJava.
For the scale of the project, LiveData usage is enough.
Obviously

- With testing the application, i don't have much experience within androidTest,
so the tests attached are more concentrated on validation of inputs of contact.
I would like to work on it more in future, as TDD development is a very
good way of developing apps.
