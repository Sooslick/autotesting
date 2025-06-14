Feature: Sooslick.Art - SCP: Containment Breach map

  @Test @T88 @SCPCBMap @SooslickArt
  Scenario: SCP: Containment Breach map page - map w/o tunnel

    * A user opens a new browser window and follows the link "https://sooslick.art/scpcbmap/"
    * "SCP: Containment Breach map" page opens

    * A user types "1" to "Seed Block -> Modded seed input"
    * A user clicks on the element "Seed Block -> Create Map button"

    * Element "Map Block -> Cell 5,15" has a text "CAMS"
    * Element "Map Block -> Cell 5,15" has a CSS-property "vertical-align" with value "bottom"
    * Element "Map Block -> Cell 5,15 -> Text label" has a CSS-property "text-align" with value "center"