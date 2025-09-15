Feature: Amrum A nach B

  @Test @T157 @AmrumFahren
  Scenario: Startseite - normal appearance

    * A user opens a new browser window and follows the link "https://www.amrumfahren.de/"
    * "Amrum A nach B - startseite" page opens
    * The active tab has a title "Amrum A nach B, ihr Fahrdienst auf und nach Amrum"
    * The page has header meta with name "description" and value "Kein Taxi auf Amrum? Lösung: Amrum A nach B! Fahrdienst, Shuttle, Krankenfahrten, Inselrundfahrten."

    * List "Header Navigation panel" consists of items, where "Link" has text
      | Startseite           |
      | Dienstleistungen     |
      | Preise               |
      | FAQ                  |
      | Feste und Events     |
      | Über uns             |
      | Impressum            |
      | Datenschutzerklärung |

    * List "Main block" consists of items, where "List Item" has text
      | Amrum A nach B                                      |
      | Ihr Fahrdienst auf und nach Amrum                   |
      | Tel. 0171 3287237                                   |
      | Erreichbar sind wir:                                |
      | [normalize space] Mo.- Fr.: von 8:00 bis 21:00 Uhr, |
      | Sa., So. und Feiertage: von 9:00 bis 21:00 Uhr      |
      | Fahrten zu anderen Zeiten nach Vereinbarung         |

    * Element "Welcome block" is visible
    * Element "Services block" is visible

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