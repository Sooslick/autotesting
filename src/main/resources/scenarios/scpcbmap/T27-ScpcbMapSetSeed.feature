Feature: Sooslick.Art - SCP: Containment Breach map

  @Test @T27 @SCPCBMap @SooslickArt
  Scenario: SCP: Containment Breach map page - set seed

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/scpcbmap/"
    * "SCP: Containment Breach map" page opens
    * The active tab has a title "SCP: Containment Breach map"

    # Step 2
    * A user types "bmu23i0" to "Seed Block -> Vanilla seed input"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "748"
    * A user begin watching browser logs
    * A user clicks on the element "Seed Block -> Create Map button"

    * Element "Map Block -> Seed map" is visible
    * Element "Seed Block -> Loading icon" is not visible

    * Browser performance logs has entry with following parameters
      | /message/method                 | Network.responseReceived            |
      | /message/params/response/url    | [substring] /map.php?prompt=bmu23i0 |
      | /message/params/type            | XHR                                 |
      | /message/params/response/status | 200                                 |

    * Element "Map Block -> Map Metadata block -> Seed String text" has a text "Seed string: bmu23i0"
    * Element "Map Block -> Map Metadata block -> Seed Number text" has a text "Seed number: 748"
    * Element "Map Block -> Map Metadata block -> Loading Screen text" has a text "Loading screen: CWM"
    * Element "Map Block -> Map Metadata block -> SCP-106 timer text" has a text "SCP-106 timer: 12"
    * Element "Map Block -> Map Metadata block -> Initial Angle text" has a text "Initial angle: 197"

    # Step 3
    * A user clears element "Seed Block -> Modded seed input"
    * A user types "748" to "Seed Block -> Modded seed input"
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value "[empty]"
    * A user begin watching browser logs
    * A user clicks on the element "Seed Block -> Create Map button"

    * Element "Map Block -> Seed map" is visible
    * Element "Seed Block -> Loading icon" is not visible

    * Browser performance logs has entry with following parameters
      | /message/method                 | Network.responseReceived      |
      | /message/params/response/url    | [substring] /map.php?seed=748 |
      | /message/params/type            | XHR                           |
      | /message/params/response/status | 200                           |

    * Element "Map Block -> Map Metadata block -> Seed String text" has a text "Seed string:"
    * Element "Map Block -> Map Metadata block -> Seed Number text" has a text "Seed number: 748"
    * Element "Map Block -> Map Metadata block -> Loading Screen text" has a text "Loading screen: CWM"
    * Element "Map Block -> Map Metadata block -> SCP-106 timer text" has a text "SCP-106 timer: 12"
    * Element "Map Block -> Map Metadata block -> Initial Angle text" has a text "Initial angle: 197"

    # Step 4
    * Element "Map Block -> Cell 15,15" has a text "372"
    * Element "Map Block -> Cell 15,15" has a CSS-property "vertical-align" with value "bottom"
    * Element "Map Block -> Cell 15,15 -> Text label" has a CSS-property "text-align" with value "center"

    * Element "Map Block -> Cell 5,17" has a text "K2"
    * Element "Map Block -> Cell 5,17" has a CSS-property "vertical-align" with value "top"
    * Element "Map Block -> Cell 5,17 -> Text label" has a CSS-property "text-align" with value "center"

    * Element "Map Block -> Cell 5,15" has a text "CAMS"
    * Element "Map Block -> Cell 5,15" has a CSS-property "vertical-align" with value "middle"
    * Element "Map Block -> Cell 5,15 -> Text label" has a CSS-property "text-align" with value "right"

    * Element "Map Block -> Cell 13,8" has a text "096"
    * Element "Map Block -> Cell 13,8" has a CSS-property "vertical-align" with value "middle"
    * Element "Map Block -> Cell 13,8 -> Text label" has a CSS-property "text-align" with value "left"