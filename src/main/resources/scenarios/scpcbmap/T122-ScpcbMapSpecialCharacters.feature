Feature: Sooslick.Art - SCP: Containment Breach map

  @Test @T122 @SCPCBMap @SooslickArt
  Scenario: SCP: Containment Breach map page - special characters

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/scpcbmap/"
    * "SCP: Containment Breach map" page opens
    * The active tab has a title "SCP: Containment Breach map"

    # Step 2
    * A user types "?/&= #" to "Seed Block -> Vanilla seed input"
    * A user begin watching browser logs
    * A user clicks on the element "Seed Block -> Create Map button"

    * Element "Map Block -> Seed map" is visible
    * Element "Seed Block -> Loading icon" is not visible

    * Element "Map Block -> Map Metadata block -> Seed String text" has a text "Seed string: ?/&= #"
    * Element "Map Block -> Map Metadata block -> Seed Number text" has a text "Seed number: 1905"

    * Browser performance logs has entry with following parameters
      | /message/method                 | Network.responseReceived                       |
      | /message/params/response/url    | [substring] /map.php?prompt=%3F%2F%26%3D%20%23 |
      | /message/params/type            | XHR                                            |
      | /message/params/response/status | 200                                            |

    # Step 3
    * A user clicks on the element "Map Block -> Share button"
    * The clipboard has text "https://sooslick.art/scpcbmap/?prompt=%3F%2F%26%3D%20%23"
