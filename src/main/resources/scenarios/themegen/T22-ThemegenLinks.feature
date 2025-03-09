Feature: Sooslick.Art - Theme Randomizer

  @Test @T22 @Themegen @SooslickArt
  Scenario: Theme Randomizer pages - links

    * A user opens a new browser window and follows the link "https://sooslick.art/projects"
    * "Sooslick.Art Project - Projects page" page opens
    * A user clicks on "Project Link" with text "themegen" in list "Projects List"

    * "Theme Randomizer main page" page opens
    * The active tab has a title "Литературный рандомайзер"
    * A user clicks on the element "Special Page Link"

    * "Theme Randomizer Learn page" page opens
    * The active tab has a title "Литературный рандомайзер"
    * A user clicks on the element "Home Link"

    * "Theme Randomizer main page" page opens
    * The active tab has a title "Литературный рандомайзер"
    * A user clicks on the element "Home Button"

    * "Sooslick.Art Project - Main page" page opens
    * The active tab has a title "Sooslick.Art - Main Page"