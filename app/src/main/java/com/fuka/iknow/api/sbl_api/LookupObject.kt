package com.fuka.iknow.api.sbl_api

data class LookupObject(
    val clientId: String = "iknow",
    val clientVersion: String = "1.7.0",
    val threatTypes: List<String> = listOf("MALWARE", "SOCIAL_ENGINEERING"),
    val platformTypes: List<String> = listOf("WINDOWS"),
    val threatEntryTypes: List<String> = listOf("URL"),
    // voi ehkä tulla jotain ongelmaa jos url on string muodossa eikä url
    // tai kun url ei ole lista muodossa
    val threatEntry: String
)

/*
"threatInfo": {
    "threatTypes":      ["MALWARE", "SOCIAL_ENGINEERING"],
    "platformTypes":    ["WINDOWS"],
    "threatEntryTypes": ["URL"],
    "threatEntries": [
    {"url": "http://www.urltocheck1.org/"},
    {"url": "http://www.urltocheck2.org/"},
    {"url": "http://www.urltocheck3.com/"}
    ]
}

 */