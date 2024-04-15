Feature: PvPetris

  @Test @T17 @PvPetris
    # todo add test to tms
  Scenario: PvPetris Main page

    # Open
    * A user opens a new browser window and follows the link "https://sooslick.art/pvpetris"
    * "PvPetris main page" page opens
    * The active tab has a title "PvPetris"
    * Element "Page Header" has a text "PVP TETRIS"

    # Main
    * Element "Current version Text" has a text "[substring] Current PvPetris version:"
    * Element "Download Button" has a text "Download"
    * Element "Scores Header" has a text "PvPetris score table"

    # todo table

    # Footer
    * Element "Footer Block -> Footer separator" has a CSS-property "border-color" with value "rgb(255, 255, 255)"
    * Element "Footer Block -> Check out our socials Text" has a text "Check out our socials"
    * Element "Footer Block -> Copyrights Text" has a text "© 2013 - {current date in format: yyyy} Sooslick.Art Project"

    * List "Footer Block -> Socials Image list" consists of items, where "Link" has text
      | https://vk.com/sooslick_art           |
      | https://twitter.com/Sooslick_Art      |
      | https://www.youtube.com/@Sooslick_art |
      | https://twitch.tv/sooslick_art        |
    * Each item in list "Footer Block -> Socials Image list" has following elements
      | Image |
