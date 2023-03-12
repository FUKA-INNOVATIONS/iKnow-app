# iKnow-app
Adroid app that keeps track of important privacy and cyber security related events.

Notes to techers
- As discussed with Peter, there was no sence to use repository layer although it is added and can be found under data/databse/repository folder.


Install

- apikey.properties file

-- Enable Fingerprint features in device settings
-- App compatible Version <= 30


Test URLs

- Vulnerable URL: 
estsafebrowsing.appspot.com/apiv4/IOS/MALWARE/URL/

- Safe URL:  
metropolia.fi


Known buggs

- User must enable fingerprin inorder to use the app
- Filtering events > Should be dropdown and dynamic
- Delete all events > Show success message and close dialog box
- Don't start searching for URL vulernabilites when TextInput is empty
- Airplane mode event > app crashes on api 31, 32, 33
- UI improvements > Not symmetric..
- Reset URL check animation


Theme and usability
- Custom Material 3 theme
- Supports light and dark themes
- Description for screan readers 

Other > functionality ready > add to UI
- Show foud vulernabilites' counter


Further development
- User is able to authenticate using password
- UI improvements
