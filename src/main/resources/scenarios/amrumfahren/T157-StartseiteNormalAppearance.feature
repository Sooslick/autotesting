Feature: Amrum A nach B

  @Test @T157 @AmrumFahren
  Scenario: Startseite - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://www.amrumfahren.de/"
    * "Amrum A nach B - startseite" page opens
    * The active tab has a title "Amrum A nach B, ihr Fahrdienst auf und nach Amrum"
    * The page has header meta with name "description" and value "Kein Taxi auf Amrum? Lösung: Amrum A nach B! Fahrdienst, Shuttle, Krankenfahrten, Inselrundfahrten."

    # Step 2
    * List "Header Navigation panel" consists of items, where "Link" has text
      | Startseite           |
      | Dienstleistungen     |
      | Preise               |
      | FAQ                  |
      | Feste und Events     |
      | Über uns             |
      | Impressum            |
      | Datenschutzerklärung |
    * Element "Header Navigation panel -> Parent element" has a CSS-property "justify-content" with value "flex-end"

    # Step 3
    * "Link" with text "Startseite" in list "Header Navigation panel" has following CSS-properties
      | border-bottom-width | 2px                  |
      | border-bottom-style | solid                |
      | border-bottom-color | rgba(238, 92, 64, 1) |

    # Step 4
    * Element "Page content -> Header section" has a CSS-property "padding-bottom" with value "100px"
    * Element "Page content -> Header clipped area" has a CSS-property "clip-path" with value "polygon(0px 100px, 100% 0px, 100% 100%, 0px 100%, 0px 100px)"
    * Element "Page content -> Header image" has a CSS-property "background" with value "[substring] url("https://www.amrumfahren.de/wp-content/uploads/go-x/u/b0b12103-e4be-4215-a5b4-8de54ca4fb6d/image.jpg")"
    * A user sends a HTTP-request to "https://www.amrumfahren.de/wp-content/uploads/go-x/u/b0b12103-e4be-4215-a5b4-8de54ca4fb6d/image.jpg" and writes the response to variable "r"
      | method | GET |
    * HTTP response from variable "r" has following properties
      | code | 200 |

    * List "Main block" consists of items, where "List Item" has text
      | Amrum A nach B                                      |
      | Ihr Fahrdienst auf und nach Amrum                   |
      | Tel. 0171 3287237                                   |
      | Erreichbar sind wir:                                |
      | [normalize space] Mo.- Fr.: von 8:00 bis 21:00 Uhr, |
      | Sa., So. und Feiertage: von 9:00 bis 21:00 Uhr      |
      | Fahrten zu anderen Zeiten nach Vereinbarung         |

    # Step 5
    * Element "Welcome block" is visible
    * Element "Welcome block -> Welcome header" has a text "Herzlich willkommen bei Amrum A nach B,"
    * Element "Welcome block -> Company subheader" has a text "Ihrem kleinen Personentransportunternehmen auf der schönsten Nordseeinsel!"
    * List "Welcome block -> Description lines" consists of items, where "List Item" has text
      | [normalize space] Kein Taxi auf Amrum? Kein Problem!                          |
      | [normalize space] Wir bieten umfangreichen Fahrservice an.                    |
      | Wenn Sie eine Fahrt auf oder nach Amrum brauchen, sind Sie bei uns richtig :) |
    * Image "Welcome block -> Company logo" has a valid source
    * Element "Welcome block -> Company logo" has an attribute "src" with value "https://www.amrumfahren.de/wp-content/uploads/go-x/u/3479d3bd-fa64-46c8-a655-c74058206342/l245,t187,w1359,h1359/image-768x768.png"
    * Element "Welcome block -> Background" has a CSS-property "background-color" with value "rgba(255, 255, 255, 1)"

    # Step 6
    * Element "Services block" is visible
    * Element "Services block -> Services header" has a text "Unser Service"
    * Element "Services block -> Offer link" has a text "Das bieten wir unseren Gästen an:"
    * Element "Services block -> Offer link" has an attribute "href" with value "https://www.amrumfahren.de/dienstleistungen"
    * List "Services block -> Services list" has items, where "List Item" has text
      | Fahrten "von A nach B" |
      | Shuttle-Service        |
      | Krankenfahrten         |
      | Kurierdienstleistungen |
      | Fernfahrten            |
      | Flughafentransfer      |
      | Inselrundfahrten       |
    * Image "Services block -> Left image" has a valid source
    * Element "Services block -> Left image" has an attribute "src" with value "https://www.amrumfahren.de/wp-content/uploads/go-x/u/784f5932-1c33-411a-b68d-6ca2bdf275f6/l481,t0,w658,h1333/image-384x778.jpg"
    * Image "Services block -> Right image" has a valid source
    * Element "Services block -> Right image" has an attribute "src" with value "https://www.amrumfahren.de/wp-content/uploads/go-x/u/8d64bfc1-ac7e-4ffe-ac8b-8760ae974617/l615,t0,w653,h1333/image-384x784.jpg"
    * Element "Services block -> Background" has a CSS-property "background-color" with value "rgba(255, 255, 255, 1)"

    # Step 7
    * Element "Page footer -> Background" has a CSS-property "background-color" with value "rgba(0, 20, 74, 1)"
    * List "Page footer -> Navigation panel" consists of items, where "Link" has text
      | Startseite       |
      | Dienstleistungen |
      | Preise           |
      | FAQ              |
      | Über uns         |
      | Impressum        |
    * Element "Page footer -> Copyright text" has a text "©Copyright. Alle Rechte vorbehalten."
    * Element "Page footer -> Datenschutzerklärung link" has a text "Datenschutzerklärung"
    * Element "Page footer -> Datenschutzerklärung link" has an attribute "href" with value "[substring] datenschutzerklarung"

    # Step 8
    * Element "Page content -> Block before footer" has a CSS-property "padding-bottom" with value "100px"
    * Element "Page footer -> Clipped area" has a CSS-property "clip-path" with value "polygon(0px 100px, 100% 0px, 100% 100%, 0px 100%, 0px 100px)"
    * "Link" with text "Startseite" in list "Page footer -> Navigation panel" has following CSS-properties
      | border-bottom-width | 2px                    |
      | border-bottom-style | solid                  |
      | border-bottom-color | rgba(255, 255, 255, 1) |