# AddressBook

Sample contact book application created as a test task.
Application shows empty contact list with a place holder. Adding new contact requires input of the following neccesary contact data:
- first name,
- last name,
- email,
- phone number.

This data must be valid before save option is available, 
address input is optional and can be left blank but it cannot contain special characters.
Added contact is highlighted in the list and by clicking one can see all the contact data.
Also we can search for a contact with search bar, via letters included in either first or last name.
Contacts are sorted ascending within the list.

# Technology

- Language: Kotlin - 100%
- Structure - MVVM using LiveData
- Jetpack components - Room (persistance layer), Navigation
- Testing - JUnit, Mockk
