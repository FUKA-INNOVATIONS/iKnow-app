# iKnow-app
Adroid app that keeps track of important privacy and cyber security related events.

Notes to techers
- As discussed with Peter, there was no sence to use repository layer although it is added and can be found under data/databse/repository folder.
- For best experience and testing purposes use API 30
- Application has fullfilledall all requirements === 5 starts!

Install

- apikey.properties file
- Enable Fingerprint features in device settings
- App compatible Version <= 30 (some versioning related bugs)


Test URLs

- Vulnerable: https://testsafebrowsing.appspot.com/apiv4/IOS/MALWARE/URL/

- Safe: metropolia.fi



Use cases / how to?




Known buggs

- User must enable fingerprin inorder to use the app
- Filtering events > Should be dropdown and dynamic
- Delete all events > Show success message and close dialog box
- Don't start searching for URL vulernabilites when TextInput is empty
- Airplane mode event > app crashes on api 31, 32, 33
- UI improvements > Not symmetric..
- Reset URL check animation on empty text filed after a search
- Warning user if no network connection on URL check


Theme and usability
- Custom Material 3 theme
- Supports light and dark themes
- Description for screan readers 

Other > functionality ready > add to UI
- Show foud vulernabilites' counter


Further development
- User is able to authenticate using password
- UI improvements
