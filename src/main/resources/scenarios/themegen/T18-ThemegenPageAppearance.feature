Feature: Sooslick.Art - PvPetris

  @Test @T18 @Themegen
  Scenario: Theme Randomizer main page - normal appearance

    # todo add test to tms
    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/themegen"
    * "Theme Randomizer main page" page opens
    * The active tab has a title "Литературный рандомайзер"
    * All elements from the following list are visible
      | Page Header                                |
      | Home Button                                |
      | Theme Annotation text                      |
      | Theme text                                 |
      | Reload Icon                                |
      | About Block                                |
      | Special Page Link                          |
      | Footer Block -> Footer separator           |
      | Footer Block -> Check out our socials Text |
      | Footer Block -> Socials Image list         |
      | Footer Block -> Copyrights Text            |
    * Element "Page Header" has a text "Литературный рандомайзер"

    # Step 2
    * Element "Theme Annotation text" has a text "Карты перемешаны, барабан прокручен, монетки подброшены. Время писать на тему:"
    * Element "Theme text" contains any text
    * Element "Reload Icon" has an attribute "src" with value "[substring] Resources/reload.png"
    * Image "Reload Icon" has a valid source
    
    # Step 3
    * Element "About Header" has a text "Что за рандомайзер?"
    * Element "Special Page Link" has a text "специальная страница"
    * Element "Special Page Link" has an attribute "href" with value "[substring] testPair"

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
