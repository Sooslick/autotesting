Feature: Amrum A nach B

  @Test @T162 @AmrumFahren
  Scenario: Preise - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://www.amrumfahren.de/preise/"
    * "Amrum A nach B - Unsere Preise" page opens
    * The active tab has a title "Preise / Amrum A nach B"

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
    * "Link" with text "Preise" in list "Header Navigation panel" has following CSS-properties
      | border-bottom-width | 2px                  |
      | border-bottom-style | solid                |
      | border-bottom-color | rgba(238, 92, 64, 1) |

    # Step 4
    * Element "Page content -> Header section" has a CSS-property "padding-bottom" with value "100px"
    * Element "Page content -> Header clipped area" has a CSS-property "clip-path" with value "polygon(0px 100px, 100% 0px, 100% 100%, 0px 100%, 0px 100px)"
    * Element "Unsere Preise Header" has a text "Unsere Preise"
    * Element "Unsere Preise Subheader" has a text "Transparente Preisgestaltung ist uns sehr wichtig!"
    * Element "Page content -> Header image" has a CSS-property "background" with value "[substring] url("https://www.amrumfahren.de/wp-content/uploads/go-x/u/3232d818-2c53-4ac0-b03d-cd63b5241248/image.jpg")"
    * A user sends a HTTP-request to "https://www.amrumfahren.de/wp-content/uploads/go-x/u/3232d818-2c53-4ac0-b03d-cd63b5241248/image.jpg" and writes the response to variable "r"
      | method | GET |
    * HTTP response from variable "r" has following properties
      | code | 200 |

    # Step 5
    * Element "Feste Preise Header" has a text "Feste Preise für die Fahrten auf Amrum"
    * List "Feste Preise text lines" consists of items, where "List Item" has text
      | Die Fahrpreise hängen von der Strecke, Personenzahl und Uhrzeit ab. Wir haben für alle Strecken auf der Insel feste Preise.                                           |
      | Ab 5 Personen und/oder bei Mitnahme von Sperrgepäck (Bollerwagen, Sportzubehör o. Ä.) nehmen wir Großraumzuschlag in der Höhe von 5,- €.                              |
      | Fahrten vor 8:00 Uhr oder nach 21:00 Uhr finden nur auf Vorbestellung statt. Für diese Fahrten berechnen wir einen Aufpreis i. H. v. 5,00 € (Änderungen vorbehalten). |
      | Alle Preise inkl. 19% MwSt.                                                                                                                                           |

    # Step 6
    * List "Destinations List" items are arranged in 3 columns of equal width
    * Each item in list "Destinations List" has following elements
      | Block Image  |
      | Block Header |
      | Text lines   |
    # TODO All images have equal dimensions

    # Step 7
    # TODO step 7

    # Step 8
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

    # Step 9
    * "Link" with text "Preise" in list "Page footer -> Navigation panel" has following CSS-properties
      | border-bottom-width | 2px                    |
      | border-bottom-style | solid                  |
      | border-bottom-color | rgba(255, 255, 255, 1) |