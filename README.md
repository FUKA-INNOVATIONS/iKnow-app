
# iKnow

Android application that keeps track of important privacy and cyber security related events.

**Notes for teachers**

- As discussed with Peter, there was no sense to use repository layer although it is added and can be found under data/database/repository folder
- For best experience and testing purposes use API 30
- Application has fullfilled all requirements === 5 starts!

# Table of Contents

- [Deployment](#deployment)
- [Features](#features)
- [Known Bugs](#known-bugs)
- [Theme and Usability](#theme-and-usability)
- [Tech Stack](#tech-stack)
- [Further-Development](#further-development)
- [Screenshots](#screenshots)
- [Credits](#credits)
## Deployment

  - App is compatible for API versions 30 and under
  - Enable fingerprint features in device settings
  - Google Safe Browsing Lookup API requires Google API key which can be created [here](https://cloud.google.com/docs/authentication/api-keys?hl=en&ref_topic=6262490&visit_id=638142279115738534-374310817&rd=1#console) 
  - Create apikey.properties file in the root of the project
  - Inside the apikey.properties file add
```bash 
SBL_API_KEY="YOUR_API_KEY" 
```


## Features

- Biometric fingerprint recognition
- Detection of variety of different broadcast events e.g. Wi-Fi or airplane mode state changed
- Sorting of the captured broadcast events by type
- URL address safety check screen
    - Test URLs:
        - Vulnerable: https://testsafebrowsing.appspot.com/apiv4/IOS/MALWARE/URL/
        - Safe: metropolia.fi
- Settings screen where user can delete all of the captured broadcast events


## Known Bugs

- User must enable fingerprint in order to use the app
- Filtering events > Should be dropdown and dynamic
- Delete all events > Show success message and close dialog box
- Don't start searching for URL vulnerabilites when TextField is empty
- Airplane mode event > App crashes on api 31, 32, 33
- UI improvements > Not symmetric..
- Reset URL check animation when the URL text is removed from the TextField 
- Warn user if there's no network connection on URL safety check screen
## Theme and Usability

- Custom Material 3 theme
- Supports light and dark themes
- Description for screen readers
## Tech Stack

**Language:** Kotlin

**Database:** Local Room Database

**Toolkit:** Jetpack Compose 

**API:** Google Safe Browsing Lookup

**Version Control:** Git & Github
## Further Development

- Frontend for counter of found URL vulnerables
- Authentication via password
- UI improvements
## Screenshots

**Login screen**
![StartScreen](https://user-images.githubusercontent.com/93869294/224556683-186a0042-89c1-4d63-a2b7-29d33c1feb47.png)

**Authentication popup**
![StartScreen_authenticationDialog](https://user-images.githubusercontent.com/93869294/224556780-889e5d1e-4c9b-4fde-a298-8a8327802730.png)

**Events screen**
![Events_All](https://user-images.githubusercontent.com/93869294/224556830-db34a0de-f46e-4aa3-84b8-660e60ca2d24.png)

**Notification of a new detected event**
![Notification_MarkAsChecked](https://user-images.githubusercontent.com/93869294/224556860-dd7fa254-3caa-4722-aca9-15143b6deeab.png)

**Settings screen**
![Settings](https://user-images.githubusercontent.com/93869294/224556894-1f4de6a2-236e-4b05-b7e1-d14bb632646b.png)

**Delete events confimation popup**
![Settings_deleteBtnPressed](https://user-images.githubusercontent.com/93869294/224556902-dd5a7b37-fa1f-4a2e-9f3d-6bf95f8932ab.png)

**Safe URL address checked**
![URL_safe](https://user-images.githubusercontent.com/93869294/224556957-38834777-e8d6-45da-9262-0b07abfc602a.png)

**Vulnerable URL address checked**
![URL_unSafe](https://user-images.githubusercontent.com/93869294/224556964-86c418f2-097c-427a-a4f4-edae14cfb149.png)


## Credits

- Fuwad Kalhori: [@FUKA-INNOVATIONS](https://github.com/FUKA-INNOVATIONS)
- Juuso Hyvönen: [@Juuso300](https://github.com/Juuso300)
- Remy Silanto: [@remysi](https://github.com/remysi)
