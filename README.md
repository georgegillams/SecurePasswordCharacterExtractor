# Secure Password Character Extractor

I got fed up of banks (and others) asking for specific characters out of my password. Having to put my whole password in plain-text to count through the letters is slow and insecure, so I made this utility to help.

This tool is provided as is, with no guarantees. Someone could potentially use data you've stored in this app to access your accounts. I will assume you have additional protections in place (HDD encryption etc). Don't blame me if they get your "deets".


### How it works:

**The application uses a directory which it creates at first run to store all data.**

When first run, it asks you to set a user-pin.
Once the user pin is set, every time you launch the app it will ask for the pin again.
Pressing "+" allows you to enter a new password. Each character is separately encrypted using the current session pin (the one you enter at the start).
To get specific characters out of a password, select it from the dropdown, type the characters you want, and hit enter. For example, for characters 2 6 and 10, type "2 6 10" and hit enter.
To remove a password, simply click the "-" button. It will turn red. Click a second time to confirm removal.

### Download jar (all platforms)
Download the jar [here](https://www.example.com/)
The jar file can be run using `java -jar SecurePasswordCharacterExtractor,jar`

### Download for macOS
Download the macOS app [here](https://www.example.com/)
Simply copy the extracted `.app` to `/Applications/` and you're good to go!
