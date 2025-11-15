Feature: Amrum A nach B

  @Test @T159 @AmrumFahren
  Scenario: Unsere Dienstleistungen - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://www.amrumfahren.de/dienstleistungen/"
    * "Amrum A nach B - Unsere Dienstleistungen" page opens
    * The active tab has a title "Service / Amrum A nach B"
    * The page has header meta with name "description" and value "Inselrundfahrten, Krankenfahrten, Fahrten zum Flughafen Hamburg, Shuttle, Transfer"

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
    * "Link" with text "Dienstleistungen" in list "Header Navigation panel" has following CSS-properties
      | border-bottom-width | 2px                  |
      | border-bottom-style | solid                |
      | border-bottom-color | rgba(238, 92, 64, 1) |

    # Step 4
    * Element "Page content -> Header section" has a CSS-property "padding-bottom" with value "100px"
    * Element "Page content -> Header clipped area" has a CSS-property "clip-path" with value "polygon(0px 100px, 100% 0px, 100% 100%, 0px 100%, 0px 100px)"
    * Element "Services Header" has a text "Unsere Dienstleistungen"
    * Element "Page content -> Header image" has a CSS-property "background" with value "[substring] url("https://www.amrumfahren.de/wp-content/uploads/go-x/u/2a1d747d-c8f5-47f7-b20e-fd5aa153f888/image.jpg")"
    * A user sends a HTTP-request to "https://www.amrumfahren.de/wp-content/uploads/go-x/u/2a1d747d-c8f5-47f7-b20e-fd5aa153f888/image.jpg" and writes the response to variable "r"
      | method | GET |
    * HTTP response from variable "r" has following properties
      | code | 200 |

    # Step 5
    * A user remembers the following text as variable "Shuttleservice description"
    """
    Shuttleservice
    Es gibt etwas zu feiern: Hochzeit, Geburtstag, Firmenjubiläum oder Weihnachtsessen? Wir übernehmen die Beförderung Ihrer Gäste, damit alle zu Ihrer Veranstaltung und danach sicher nach Hause kommen. Für Ihre Gäste ist der Shuttle kostenlos.
    1 Stunde 86,- €
    """
    * A user remembers the following text as variable "Inselrundfahrten description"
    """
    Inselrundfahrten
    Sie machen einen Tagesausflug nach Amrum und haben nur 2-3 Stunden Zeit? Mit uns können Sie eine ganz individuelle Inselrundfahrt machen – mit Tipps und Fotopausen.
    60 Minuten 65,- €
    90 Minuten 85,- €
    """
    * A user remembers the following text as variable "Kurierfahrten description"
    """
    Kurierfahrten
    Sie sind mit dem Fahrrad angekommen, haben Koffer dabei und wissen nicht wohin damit? Kein Problem, wir können gerne den Koffertransport übernehmen. Natürlich kann man uns auch mit anderen Sachen beauftragen, wir bringen diese dann von A nach B. :)
    Aber Achtung: wir haben leider keine Möglichkeit, die Fahrräder zu transportieren!
    """
    * A user remembers the following text as variable "Krankenfahrten description"
    """
    Krankenfahrten
    Wir bringen Sie gerne zum Krankenhaus/zum Arzt, wenn ein sogenannter Transportschein (Verordnung für Krankenbeförderung) vorhanden ist. In diesem Fall übernimmt die Krankenkasse die Kosten dafür.
    Wir können Krankenfahrten natürlich auch ohne Transportschein machen, in diesem Fall tragen Sie selbst die Kosten einer solchen Fahrt.
    Wir machen Krankenfahrten aller Art: zur Dialyse, Strahlen- oder Chemotherapie, zur Reha oder OP, ab Amrum oder ab Dagebüll etc. Haben Sie Fragen? Dann rufen Sie uns an, wir beraten Sie gerne.
    """

    * List "Insel block -> Services list" consists of following items
      | Block Header     | Content                                                 |
      | Auf der Insel... | Auf der Insel...                                        |
      | Shuttleservice   | {variable: Shuttleservice description}                  |
      | Inselrundfahrten | {variable: Inselrundfahrten description}                |
      | Kurierfahrten    | {variable: Kurierfahrten description}                   |
      | Krankenfahrten   | [normalize space]{variable: Krankenfahrten description} |

    * Image "Insel block -> Image" has a valid source
    * Element "Insel block -> Image" has an attribute "src" with value "https://www.amrumfahren.de/wp-content/uploads/go-x/u/6594aed0-3319-4d97-9600-b402153f22b7/l357,t0,w1500,h1500/image-768x768.jpg"
    * Element "Insel block -> Image" has a CSS-property "border-radius" with value "500px"
    * Element "Insel block -> Background" has a CSS-property "background-color" with value "rgba(255, 255, 255, 1)"

    # Step 6
    * A user remembers the following text as variable "Flughafentransfer und Fernfahrten description"
    """
    Flughafentransfer und Fernfahrten

    Die Fahrten vom/zum Flughafen Hamburg sind oft gefragt. Für die Fahrt vom Flughafen oder Hauptbahnhof Hamburg bis Dagebüll zahlen Sie nur 330,- € (der Preis gilt für Fahrten mit bis zu 4 Personen).
    Wir fahren auch andere Flughäfen oder Bahnhöfe an. Die Züge haben ja gerne mal Verspätung :)
    Wir fahren Sie auf Wusch auch deutschlandweit oder sogar europaweit – ab 1,60 € pro Kilometer.
    """

    * List "Festland block -> Services list" consists of following items
      | Block Header                      | Content                                                   |
      | ... oder auf dem Festland         | ... oder auf dem Festland                                 |
      | Flughafentransfer und Fernfahrten | {variable: Flughafentransfer und Fernfahrten description} |

    * Image "Festland block -> Image" has a valid source
    * Element "Festland block -> Image" has an attribute "src" with value "https://www.amrumfahren.de/wp-content/uploads/go-x/u/68bd405f-3713-40d3-bb1f-079f29277259/l335,t61,w1387,h1387/image-768x768.jpg"
    * Element "Festland block -> Image" has a CSS-property "border-radius" with value "500px"
    * Element "Festland block -> Background" has a CSS-property "background-color" with value "rgba(255, 255, 255, 1)"

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
    * "Link" with text "Dienstleistungen" in list "Page footer -> Navigation panel" has following CSS-properties
      | border-bottom-width | 2px                    |
      | border-bottom-style | solid                  |
      | border-bottom-color | rgba(255, 255, 255, 1) |