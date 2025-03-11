Feature: Sooslick.Art - Theme Randomizer

  @Test @T39 @SCPCBMap @SooslickArt
  Scenario: SCP Containment Breach map - links

    * A user opens a new browser window and follows the link "https://sooslick.art/projects"
    * "Sooslick.Art Project - Projects page" page opens
    * A user clicks on "Project Link" with text "scpcbmap" in list "Projects List"

    * "SCP: Containment Breach map" page opens
    * The active tab has a title "SCP: Containment Breach map"
    * A user clicks on the element "App Info Block -> Sooslick link"

    * "Sooslick.Art Project - Main page" page opens
    * The active tab has a title "Sooslick.Art - Main Page"