Feature: Amrum A nach B

  @Test @T159 @AmrumFahren
  Scenario: Unsere Dienstleistungen - normal appearance

    * A user opens a new browser window and follows the link "https://www.amrumfahren.de/dienstleistungen/"
    * "Amrum A nach B - Unsere Dienstleistungen" page opens
    * The active tab has a title "Service / Amrum A nach B"
    * The page has header meta with name "description" and value "Inselrundfahrten, Krankenfahrten, Fahrten zum Flughafen Hamburg, Shuttle, Transfer"

    * List "Header Navigation panel" consists of items, where "Link" has text
      | Startseite           |
      | Dienstleistungen     |
      | Preise               |
      | FAQ                  |
      | Feste und Events     |
      | Über uns             |
      | Impressum            |
      | Datenschutzerklärung |

    * Element "Services Header" has a text "Unsere Dienstleistungen"

    * List "Insel block" consists of items, where "Block Header" has text
      | Shuttleservice   |
      | Inselrundfahrten |
      | Kurierfahrten    |
      | Krankenfahrten   |
    * List "Festland block" consists of items, where "Block Header" has text
      | Flughafentransfer und Fernfahrten |

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