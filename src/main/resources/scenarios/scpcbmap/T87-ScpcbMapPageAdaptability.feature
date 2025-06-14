Feature: Sooslick.Art - SCP: Containment Breach map

  @Test @T87 @SCPCBMap @SooslickArt
  Scenario: SCP: Containment Breach map page - adaptability

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/scpcbmap/"
    * A user sets browser window size to 1280, 900 pixels
    * "SCP: Containment Breach map" page opens
    * The active tab has a title "SCP: Containment Breach map"

    # Step 2
    * Element "Seed Block" has a width equals to "472" pixels
    * Element "Seed Block" has a height equals to "205" pixels
    * Element "App Info Block" has a width equals or bigger than "450" pixels
    * Element "App Info Block" has a width equals or lesser than "500" pixels
    * Element "Seed Block -> Vanilla seed text" has a height equals to "18" pixels
    * Element "Seed Block -> Modded seed text" has a height equals to "18" pixels

    * Element "Map Block -> Share button" is not visible

    # Step 3
    * A user clicks on the element "Seed Block -> Create Map button"
    * Element "Map Block -> Share button" is visible
    * Page vertical scroll position is bigger than "0" pixels

    * Element "Map Block -> Seed map" has a width equals or bigger than "550" pixels
    * Element "Map Block -> Seed map" has a width equals or lesser than "600" pixels
    * A user remembers the Y coordinate of element "Map Block -> Pocket Dimension map" as variable "baseline"
    * Element "Map Block -> Forest map" has Y coordinate equals to "{variable: baseline}" pixels

    # Step 4
    * A user sets browser window width to "1000" pixels
    * Element "Seed Block" has a width equals to "472" pixels
    * Element "Seed Block" has a height equals to "205" pixels
    * Element "Map Block -> Seed map" has a width equals or bigger than "550" pixels
    * Element "Map Block -> Seed map" has a width equals or lesser than "600" pixels
    * Element "Seed Block -> Vanilla seed text" has a height equals to "18" pixels
    * Element "Seed Block -> Modded seed text" has a height equals to "18" pixels
