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

    # Step 6
    * Element "Services block" is visible

    # Step 7
    * List "Footer Navigation panel" consists of items, where "Link" has text
      | Startseite       |
      | Dienstleistungen |
      | Preise           |
      | FAQ              |
      | Über uns         |
      | Impressum        |
    * Element "Copyright text" has a text "©Copyright. Alle Rechte vorbehalten."
    * Element "Datenschutzerklärung link" has a text "Datenschutzerklärung"
    * Element "Datenschutzerklärung link" has an attribute "href" with value "[substring] datenschutzerklarung"