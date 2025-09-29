Feature: Amrum A nach B

  @Test @T167 @AmrumFahren
  Scenario: Datenschutzerklarung - normal appearance

    * A user opens a new browser window and follows the link "https://www.amrumfahren.de/datenschutzerklarung/"
    * "Amrum A nach B - Datenschutzerklärung" page opens
    * The active tab has a title "Datenschutzerklärung"

    * List "Header Navigation panel" consists of items, where "Link" has text
      | Startseite           |
      | Dienstleistungen     |
      | Preise               |
      | FAQ                  |
      | Feste und Events     |
      | Über uns             |
      | Impressum            |
      | Datenschutzerklärung |

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