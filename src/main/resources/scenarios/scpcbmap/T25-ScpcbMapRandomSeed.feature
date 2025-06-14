Feature: Sooslick.Art - SCP: Containment Breach map

  @Test @T25 @SCPCBMap @SooslickArt
  Scenario: SCP: Containment Breach map page - random seed

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/scpcbmap/"
    * "SCP: Containment Breach map" page opens
    * The active tab has a title "SCP: Containment Breach map"

    # Step 2
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value "[empty]"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "[empty]"
    * A user begin watching browser logs
    * A user clicks on the element "Seed Block -> Create Map button"
    * A user moves mouse to point 0, 0

    * All elements from the following list are visible
      | Map Block -> Seed map             |
      | Map Block -> Map Metadata block   |
      | Map Block -> Room Info block      |
      | Map Block -> Pocket Dimension map |
      | Map Block -> Forest map           |
      | Map Block -> Share button         |
    * Element "Seed Block -> Loading icon" is not visible
    * Element "Map Block -> Copied to clipboard Text" is not visible

    * Browser performance logs has entry with following parameters
      | /message/method                 | Network.responseReceived    |
      | /message/params/response/url    | [substring] /map.php?random |
      | /message/params/type            | XHR                         |
      | /message/params/response/status | 200                         |

    # Step 3
    * All elements from the following list are visible
      | Map Block -> Map Metadata block -> Map metadata header |
      | Map Block -> Map Metadata block -> Seed String text    |
      | Map Block -> Map Metadata block -> Seed Number text    |
      | Map Block -> Map Metadata block -> Loading Screen text |
      | Map Block -> Map Metadata block -> SCP-106 timer text  |
      | Map Block -> Map Metadata block -> SCP-106 timer hint  |
      | Map Block -> Map Metadata block -> Initial Angle text  |
      | Map Block -> Map Metadata block -> Initial Angle hint  |
    * All elements from the following list are not visible
      | Map Block -> Map Metadata block -> SCP-106 timer popup |
      | Map Block -> Map Metadata block -> Initial Angle popup |

    * Element "Map Block -> Map Metadata block -> Map metadata header" has a text "Map metadata"
    * Element "Map Block -> Map Metadata block -> Seed String text" has a text "[regexp] Seed string:.*"
    * Element "Map Block -> Map Metadata block -> Seed Number text" has a text "[regexp] Seed number: [0-9]+"
    * Element "Map Block -> Map Metadata block -> Loading Screen text" has a text "[regexp] Loading screen: .+"
    * Element "Map Block -> Map Metadata block -> SCP-106 timer text" has a text "[regexp] SCP-106 timer: 1[2-7]"
    * Element "Map Block -> Map Metadata block -> Initial Angle text" has a text "[regexp] Initial angle: [12][0-9][0-9]"

    * A user hovers the cursor over the element "Map Block -> Map Metadata block -> SCP-106 timer hint"
    * Element "Map Block -> Map Metadata block -> SCP-106 timer popup" is visible
    * Element "Map Block -> Map Metadata block -> SCP-106 timer popup" has a text "Time until the first appearance of SCP-106 in minutes"

    * A user hovers the cursor over the element "Map Block -> Map Metadata block -> Initial Angle hint"
    * Element "Map Block -> Map Metadata block -> Initial Angle popup" is visible
    * Element "Map Block -> Map Metadata block -> Initial Angle popup" has a text "Player's initial rotation is random and selected between 160 and 200 degrees"

    # Step 4
    * All elements from the following list are visible
      | Map Block -> Room Info block -> Room Info header |
      | Map Block -> Room Info block -> Room text        |
      | Map Block -> Room Info block -> Events text      |
      | Map Block -> Room Info block -> Extra Info text  |

    * Element "Map Block -> Room Info block -> Room text" has a text "Room:"
    * Element "Map Block -> Room Info block -> Events text" has a text "Events:"
    * Element "Map Block -> Room Info block -> Extra Info text" has a text "Extra info:"

    # Step 5
    * A user hovers the cursor over the element "Map Block -> Cell 8,16"
    * Element "Map Block -> Room Info block -> Room text" has a text "[regexp] Room: .*"
    * Element "Map Block -> Room Info block -> Events text" has a text "[regexp] Events: .*"
    * Element "Map Block -> Room Info block -> Extra Info text" has a text "[regexp] Extra info: .*"
    * Element "Map Block -> Cell 8,16" has a CSS-property "background" with value "[substring] rgb(68, 68, 68)" when hovered
    * Element "Map Block -> Cell 8,16 -> Room image" is visible

    # Step 6
    * "Map Block -> Seed map" table row with number 1 has a CSS-property "background" with value "[substring] rgba(64, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 2 has a CSS-property "background" with value "[substring] rgba(64, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 3 has a CSS-property "background" with value "[substring] rgba(64, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 4 has a CSS-property "background" with value "[substring] rgba(64, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 5 has a CSS-property "background" with value "[substring] rgba(64, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 6 has a CSS-property "background" with value "[substring] rgba(64, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 7 has a CSS-property "background" with value "[substring] rgba(0, 0, 0, 0.1)"
    * "Map Block -> Seed map" table row with number 8 has a CSS-property "background" with value "[substring] rgba(128, 0, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 9 has a CSS-property "background" with value "[substring] rgba(128, 0, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 10 has a CSS-property "background" with value "[substring] rgba(128, 0, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 11 has a CSS-property "background" with value "[substring] rgba(128, 0, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 12 has a CSS-property "background" with value "[substring] rgba(128, 0, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 13 has a CSS-property "background" with value "[substring] rgba(0, 0, 0, 0.1)"
    * "Map Block -> Seed map" table row with number 14 has a CSS-property "background" with value "[substring] rgba(128, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 15 has a CSS-property "background" with value "[substring] rgba(128, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 16 has a CSS-property "background" with value "[substring] rgba(128, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 17 has a CSS-property "background" with value "[substring] rgba(128, 128, 0, 0.3)"
    * "Map Block -> Seed map" table row with number 18 has a CSS-property "background" with value "[substring] rgba(128, 128, 0, 0.3)"