Feature: Sooslick.Art - SCP: Containment Breach map

  @Test @T89 @SCPCBMap @SooslickArt
  Scenario: SCP Containment Breach map - copy seed link

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/scpcbmap/"
    * "SCP: Containment Breach map" page opens
    * The active tab has a title "SCP: Containment Breach map"

    # Step 2
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value "[empty]"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "[empty]"
    * A user clicks on the element "Seed Block -> Create Map button"
    * Element "Map Block -> Map Metadata block" is visible

    * Element "Map Block -> Map Metadata block -> Seed String text" has a text "[regexp] Seed string:.*"
    * Element "Map Block -> Map Metadata block -> Seed Number text" has a text "[regexp] Seed number: [0-9]+"
    * A user remembers the text in element "Map Block -> Map Metadata block -> Seed String text" to variable "vanilla seed"
    * A user remembers the text in element "Map Block -> Map Metadata block -> Seed Number text" to variable "modded seed"

    # Step 3
    * A user clicks on the element "Map Block -> Share button"
    * The clipboard has text "[regexp]https:\/\/sooslick\.art\/scpcbmap\/\?(prompt=.+|seed=[0-9]+)"

    # Step 4
    * A user opens a new browser tab and follows the link "{clipboard}"
    * "SCP: Containment Breach map" page opens
    * The active tab has a title "SCP: Containment Breach map"

    * Element "Map Block -> Map Metadata block" is visible
    * Element "Map Block -> Map Metadata block -> Seed String text" has a text "{variable: vanilla seed}"
    * Element "Map Block -> Map Metadata block -> Seed Number text" has a text "{variable: modded seed}"