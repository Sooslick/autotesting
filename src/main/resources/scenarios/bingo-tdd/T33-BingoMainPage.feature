Feature: Sooslick.Art - Bingo

  @Test @T33 @Bingo @SooslickArt
  Scenario: Sooslick.Art Bingo - main page

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/bingo"
    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"
    * The page has header meta with name "author" and value "Sooslick"
    * The page has header meta with name "description" and value "Bingo App by Sooslick.Art Project. Create themed bingo and lotto tickets using custom templates"
    * The page has header meta with name "theme-color" and value "black"

    * All elements from the following list are visible
      | Main banner Image                          |
      | App by Text                                |
      | Sooslick Art home button                   |
      | Localization Drop down                     |
      | Color theme Switch                         |
      | Templates Tab                              |
      | Tickets Tab                                |
      | Templates List                             |
      | Template preview Block                     |
      | Footer Block -> Footer separator           |
      | Footer Block -> Check out our socials Text |
      | Footer Block -> Socials Image list         |
      | Footer Block -> Copyrights Text            |

    * Element "Main header Text" has a text "Bingo!"
    * Element "App by Text" has a text "App by Sooslick.Art Project for various Bingo and Lotto tickets"
    * Drop-down list "Localization Drop down" has selected text "EN"
    * Element "Templates Tab" has an attribute "class" with value "[substring] tab-selected"
    * Element "Tickets Tab" has an attribute "class" which is not "[substring] tab-selected"
    * List "Templates List" has items
    * Element "Templates List" has a CSS-property "text-align" with value "left"
    * Element "Template preview Block -> Preview Image" is not visible

    # Step 2
    * Each "List Item" in list "Templates List" has a CSS-property "border" with value "2px solid rgba(200, 200, 200, 1)" when hovered
    * Each "List Item" in list "Templates List" has a CSS-property "border-radius" with value "0"
    * Element "Template preview Block -> Preview Image" is not visible

    # Step 3
    * A user clicks on "List Item" with number 1 in list "Templates List"
    * "List Item" with number 1 in list "Templates List" has a CSS-property "border" with value "2px solid rgba(255, 255, 255, 1)"
    * "List Item" with number 1 in list "Templates List" has a CSS-property "border-radius" with value "0"
    * All elements from the following list are visible
      | Template preview Block -> Preview Image             |
      | Template preview Block -> Template name Text        |
      | Template preview Block -> Template author Text      |
      | Template preview Block -> Template description Text |
      | Template preview Block -> Random method Text        |
      | Template preview Block -> Entries amount Text       |
      | Template preview Block -> Create ticket Button      |

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
