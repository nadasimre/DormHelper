# DormHelper

## User guide

(disclaimer: the app is not really useful in itself, it just shows some functionalities and techiques I learned to implement during class)

### Login screen
Username and Password text: you need to type these in, if you leave them empty, or if they are not correct, an error message will show accordingly,
Login button: After you type in the correct username and password pair, you will be redirected to the main menu,
Cancel button: The username and/or the password you typed in will be cleared,
New user? Click here to register button: This will redirect you to the registration screen

### Registration screen
Username, Password and Email text: you need to type these in, if you leave them empty, an error message will show, if you entered your data, if the name or email are taken, there will be an error message as well,
Register button: After you type in your info(and there was no problem with it), it will show a message that the account creation was succesful,
Cancel button: It will redirect you to the Login screen

### Main Menu
Here on the top there are the data of the user (name and email), and a profile icon, which serves as a button. This button leads to the Manage Account screen. Below this is a RecycleView, with the dorm presets that this user previously made (if you tap them, that leads to the Dorm Preset detail screen), and below that there are two butons, the first one is the Add Preset button, this leads to the Add Drom preset screen, the other one is the Delete Presets button, if you tap this, a Dialog box will ask you if you really want to delete all the presets, if you tap no, then nothin will happen, but if you tap yes, then all the presets will be deleted. 

### Manage Account screen
Here you can change the Username, the Password and the Email of the logged in account, there are three textboxes in which you can type the new data, and a separate Change button to each of them (if the box is empty it will show an error button). Below these is two buttons, a cancel button, which will redirect you to the Main Menu, and a Delete User button, which will delete the logged in user permanently.

### Add Dorm Preset screen
Here you can add the presets you want. First you need to enter all the data (How many square meters, how many rooms and the name of the preset), because these are required. Then you can tell if it has a bathroom, is furnitured, has a separate sink, or has an own kitchen with the checkboxes. Below these are again, two buttons, a create button, which will redirect to the Main Menu with the created preset (if a textbox is empty, then it will show an error message), and a Cancel button, which will clear all the textboxes. If you go back to the Main Menu with the back button, then there will be a new preset, but it will be a non configured broken preset, and it will look accordingly.

### Dorm Preset Detail screen
Here you can see all the information about the dorm preset you tapped, it's picture will be according to the number of rooms (or if you exited the Add Dorm Preset screen with the backbutton), it will show what is the name, how many rooms it has and how big it is. It will show icons according to the checkboyes of the Add Dorm Preset screen. It has a Delete Preset button, with this you can delete this preset and be redirected to the Main Menu.

### NawDrawer
There is a hamburger menu at the Login screen, but it can be accessed from anywhere (aside from the network screens) if you swipe right from the left side of the screen.

### About screen
It can be accessed through the NawDrawer, it has a short description about the app.

### Options Menu
It can be accessed from the Main Menu.

### Network screen
It can be accessed from the Options Menu of the Main Menu, it has a RecyclerView, with list items about the Dorms I access trough the web service, these are clickable.

### Network Detail screen
It can be accessed trough the Network screen, It has all the information that can be accessed from the web service.
