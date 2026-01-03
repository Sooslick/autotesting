Feature: Sooslick.Art - PvPetris

  @Test @T19 @PvPetris @SooslickArt
  Scenario: PvPetris main page - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/pvpetris"
    * "PvPetris main page" page opens
    * The active tab has a title "PvPetris"
    * The page has header meta with name "author" and value "Sooslick"
    * The page has header meta with name "description" and value "A regular Tetris game with multiplayer, styled like Classic Tetris World Championship"
    * The page has header meta with name "theme-color" and value "black"

    * All elements from the following list are visible
      | Page Header          |
      | Home Button          |
      | Current version Text |
      | Download Button      |
      | About Text block     |
      | Scores Header        |
      | Scores Table         |
      | Legacy Footer Block  |
    * Element "Page Header" has a text "PVP TETRIS"

    # Step 2
    * Element "Current version Text" has a text "[regexp] Current PvPetris version: [0-9]+\.[0-9]+(\.[0-9+](\.[0-9+])?)?"
    * Element "Download Button" has a text "Download"
    * A user hovers the cursor over the element "Current version Text"
    * Element "Download Button" has a CSS-property "background-color" with value "rgba(51, 0, 0, 1)"
    * Element "Download Button" has a CSS-property "color" with value "rgba(255, 255, 255, 1)"
    * Element "Download Button" has a CSS-property "border-color" with value "rgb(0, 0, 0)"
    * Element "Download Button" has a CSS-property "background-color" with value "rgba(51, 0, 0, 1)" when hovered
    * Element "Download Button" has a CSS-property "color" with value "rgba(255, 0, 0, 1)" when hovered
    * Element "Download Button" has a CSS-property "border-color" with value "rgb(255, 0, 0)" when hovered

    # Step 3
    * Element "About Text block" has a CSS-property "border-image-source" with value "[substring] sBorder_0.png"
    * Element "About Text block" has a CSS-property "border-width" with value "32px"
    * Element "About Text block" has a text "[substring] PvPetris is a regular Tetris game based on classic NES version"
    * Element "About Text block" has a text "[substring] How to host the game:"
    * Element "About Text block" has a text "[substring] Source code:"
    * Element "Github Link" has a text "Github link"
    * Element "Github Link" has a CSS-property "color" with value "rgba(255, 0, 0, 1)"
    * Element "Github Link" has a CSS-property "color" with value "rgba(255, 136, 0, 1)" when hovered
    * Element "Github Link" has an attribute "href" with value "https://github.com/Sooslick/Pvpetris"

    # Step 4
    * Element "Scores Header" has a text "PvPetris score table"
    * Table header of "Scores Table" has following headers
      | PLACE       |
      | NAME        |
      | SCORE       |
      | LINES       |
      | TTR%        |
      | START LEVEL |
    * Element "Scores Table" has a CSS-property "background-color" with value "rgba(0, 0, 0, 0.75)"
    * "Scores Table" table header has a CSS-property "background-color" with value "rgba(34, 34, 34, 1)"

    # Footer
    * Element "Legacy Footer Block -> Footer separator" has a CSS-property "border-color" with value "rgb(128, 128, 128)"
    * Element "Legacy Footer Block -> Check out our socials Text" has a text "Check out our socials"
    * Element "Legacy Footer Block -> Copyrights Text" has a text "© 2013 - {current date in format: yyyy} Sooslick.Art Project"

    * List "Legacy Footer Block -> Socials Image list" consists of items, where "Link" has text
      | https://vk.com/sooslick_art           |
      | https://twitter.com/Sooslick_Art      |
      | https://www.youtube.com/@Sooslick_art |
      | https://twitch.tv/sooslick_art        |
    * Each item in list "Legacy Footer Block -> Socials Image list" has following elements
      | Image |
