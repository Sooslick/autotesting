Feature: Sooslick.Art - PvPetris

  @Test @T38 @PvPetris @SooslickArt
  Scenario: PvPetris - links

    * A user opens a new browser window and follows the link "https://sooslick.art/projects"
    * "Sooslick.Art Project - Projects page" page opens
    * A user clicks on "Project Link" with text "pvpetris" in list "Projects List"

    * "PvPetris main page" page opens
    * The active tab has a title "PvPetris"
    * A user clicks on the element "Home Button"

    * "Sooslick.Art Project - Main page" page opens
    * The active tab has a title "Sooslick.Art - Main Page"