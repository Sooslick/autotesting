Feature: Amrum A nach B

  @Test @T165 @AmrumFahren
  Scenario: Uber uns - normal appearance

    * A user opens a new browser window and follows the link "https://www.amrumfahren.de/uber-uns/"
    * "Amrum A nach B - About Us" page opens
    * The active tab has a title "Über uns"

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
    * "Link" with text "Über uns" in list "Header Navigation panel" has following CSS-properties
      | border-bottom-width | 2px                  |
      | border-bottom-style | solid                |
      | border-bottom-color | rgba(238, 92, 64, 1) |

    # Step 4
    * Element "Page content -> Header section" has a CSS-property "padding-bottom" with value "100px"
    * Element "Page content -> Header clipped area" has a CSS-property "clip-path" with value "polygon(0px 100px, 100% 0px, 100% 100%, 0px 100%, 0px 100px)"

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

    * Element "Page content -> Block before footer" has a CSS-property "padding-bottom" with value "100px"
    * Element "Page footer -> Clipped area" has a CSS-property "clip-path" with value "polygon(0px 100px, 100% 0px, 100% 100%, 0px 100%, 0px 100px)"

    * "Link" with text "Über uns" in list "Page footer -> Navigation panel" has following CSS-properties
      | border-bottom-width | 2px                    |
      | border-bottom-style | solid                  |
      | border-bottom-color | rgba(255, 255, 255, 1) |