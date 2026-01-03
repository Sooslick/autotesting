Feature: Sooslick.Art - Theme Randomizer

  @Test @T20 @Themegen @SooslickArt
  Scenario: Theme Randomizer main page - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/themegen"
    * "Theme Randomizer main page" page opens
    * The active tab has a title "Литературный рандомайзер"
    * The page has header meta with name "author" and value "Sooslick"
    * The page has header meta with name "description" and value "Генератор случайных тем для литературных конкурсов. Рандомит всякую ерунду, но иногда получается что-то интересное и смешное"
    * The page has header meta with name "theme-color" and value "gray"

    * All elements from the following list are visible
      | Page Header                                |
      | Theme Annotation text                      |
      | Theme text                                 |
      | Reload Icon                                |
      | About Block                                |
      | Special Page Link     |
      | Footer Block          |
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
    * Element "Footer Block" has a CSS-property "border-top" with value "2px solid rgb(255, 255, 255)"
    * Element "Footer Block -> Check out our socials Text" has a text "Check out our socials"
    * Element "Footer Block -> Copyrights Text" has a text "© 2013 - {current date in format: yyyy} Sooslick.Art Project"
    * Element "Footer Block -> Sooslick.Art Project link" has a text "Sooslick.Art Project"
    * Element "Footer Block -> Sooslick.Art Project link" has an attribute "href" with value "https://sooslick.art/"

    * List "Footer Block -> Socials Image list" consists of items, where "Link" has text
      | https://twitch.tv/sooslick_art        |
      | https://www.youtube.com/@Sooslick_art |
      | https://x.com/Sooslick_Art            |
      | https://vk.com/sooslick_art           |
    * Each item in list "Footer Block -> Socials Image list" has following elements
      | Image |
    * List "Footer Block -> Component versions list" consists of items, where "List Item" has text
      | [regexp]Engine build v\d+\.\d+(\.\d+(.\d+)?)?-\d{6} |
      | [regexp]App version v\d+\.\d+(\.\d+(.\d+)?)?-\d{6}  |
