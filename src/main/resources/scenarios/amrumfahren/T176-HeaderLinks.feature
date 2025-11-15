Feature: Amrum A nach B

  @Test @T176 @AmrumFahren
  Scenario Outline: Amrum A nach B - header links

    * A user opens a new browser window and follows the link "<start link>"
    * "<start page>" page opens

    * A user clicks on "Link" with text "Startseite" in list "Header Navigation panel"
    * "Amrum A nach B - startseite" page opens
    * A user clicks back button in browser toolbar
    * "<start page>" page opens

    * A user clicks on "Link" with text "Dienstleistungen" in list "Header Navigation panel"
    * "Amrum A nach B - Unsere Dienstleistungen" page opens
    * A user clicks back button in browser toolbar
    * "<start page>" page opens

    * A user clicks on "Link" with text "Preise" in list "Header Navigation panel"
    * "Amrum A nach B - Unsere Preise" page opens
    * A user clicks back button in browser toolbar
    * "<start page>" page opens

    * A user clicks on "Link" with text "FAQ" in list "Header Navigation panel"
    * "Amrum A nach B - FAQ" page opens
    * A user clicks back button in browser toolbar
    * "<start page>" page opens

    * A user clicks on "Link" with text "Feste und Events" in list "Header Navigation panel"
    * "Amrum A nach B - Events" page opens
    * A user clicks back button in browser toolbar
    * "<start page>" page opens

    * A user clicks on "Link" with text "Über uns" in list "Header Navigation panel"
    * "Amrum A nach B - About Us" page opens
    * A user clicks back button in browser toolbar
    * "<start page>" page opens

    * A user clicks on "Link" with text "Impressum" in list "Header Navigation panel"
    * "Amrum A nach B - Impressum" page opens
    * A user clicks back button in browser toolbar
    * "<start page>" page opens

    * A user clicks on "Link" with text "Datenschutzerklärung" in list "Header Navigation panel"
    * "Amrum A nach B - Datenschutzerklärung" page opens

    Examples:
      | start link                                            | start page                               |
      | https://www.amrumfahren.de/#test                      | Amrum A nach B - startseite              |
      | https://www.amrumfahren.de/dienstleistungen/#test     | Amrum A nach B - Unsere Dienstleistungen |
      | https://www.amrumfahren.de/preise/#test               | Amrum A nach B - Unsere Preise           |
      | https://www.amrumfahren.de/faq/#test                  | Amrum A nach B - FAQ                     |
      | https://www.amrumfahren.de/feste-und-events/#test     | Amrum A nach B - Events                  |
      | https://www.amrumfahren.de/uber-uns/#test             | Amrum A nach B - About Us                |
      | https://www.amrumfahren.de/impressum/#test            | Amrum A nach B - Impressum               |
      | https://www.amrumfahren.de/datenschutzerklarung/#test | Amrum A nach B - Datenschutzerklärung    |