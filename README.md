# Secure Password Character Extractor

I got fed up of banks (and others) asking for specific characters out of my password. Having to put my whole password in plain-text to count through the letters is slow and insecure, so I made this utility to help.

### Disclaimer
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

### How it works:

**The application uses a directory which it creates at first run to store all data.**

When first run, it asks you to set a user-pin.
Once the user pin is set, every time you launch the app it will ask for the pin again.
Pressing "+" allows you to enter a new password. Each character is separately encrypted using the current session pin (the one you enter at the start).
To get specific characters out of a password, select it from the dropdown, type the characters you want, and hit enter. For example, for characters 2 6 and 10, type "2 6 10" and hit enter.
To remove a password, simply click the "-" button. It will turn red. Click a second time to confirm removal.

### Download jar (all platforms)
Download the jar [here](https://github.com/georgegillams/SecurePasswordCharacterExtractor/releases/download/1.0.0/SecurePasswordCharacterExtractor.jar)
The jar file can be run using `java -jar SecurePasswordCharacterExtractor,jar`

### Download for macOS
Download the macOS app [here](https://github.com/georgegillams/SecurePasswordCharacterExtractor/releases/download/1.0.0/Secure_Password_Character_Extractor.zip)
Simply copy the extracted `.app` to `/Applications/` and you're good to go!

### Credits
With thanks to Freepik for the app icon.
Icon made by [Freepik](http://www.freepik.com/) from [www.flaticon.com](www.flaticon.com )

Thanks also to [João Ricardo Lourenço](https://github.com/Jorl17) for [jar2app](https://github.com/Jorl17/jar2app)
and to [Moin Uddin](https://github.com/moinism) for [png2icns](https://www.npmjs.com/package/png2icns)
