Feature: Sooslick.Art - Main Page

  @Test @T13 @SooslickArtMain @SooslickArt
  Scenario: 404 page - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/404"
    * "Sooslick.Art Project - Error page" page opens
    * The active tab has a title "404 Not Found"
    * The page has header meta with name "author" and value "Sooslick"
    * The page has header meta with name "description" and value "YOUR PRINCESS IN ANOTHER CASTLE"
    * The page has header meta with name "theme-color" and value "black"

    * All elements from the following list are visible
      | Sooslick Art banner Image                  |
      | Main page links Block -> Main page Link    |
      | Main page links Block -> All projects Link |
      | Main page links Block -> About us Link     |
      | Main page links Block -> Portfolio Link    |
      | Error Header                               |
      | Description Header                         |
      | Footer Block -> Footer separator           |
      | Footer Block -> Check out our socials Text |
      | Footer Block -> Socials Image list         |
      | Footer Block -> Copyrights Text            |
    * Element "Error Header" has a text "404 NOT FOUND"
    * Element "Description Header" has a text "YOUR PRINCESS IN ANOTHER CASTLE"

    # Step 2
    * Element "Sooslick Art banner Image" has a CSS-property "top" with value "0px"
    * Element "Sooslick Art banner Image" has a CSS-property "position" with value "fixed"
    * A user scrolls the page to element "Footer Block"
    * Element "Sooslick Art banner Image" has a CSS-property "top" with value "0px"
    * Element "Sooslick Art banner Image" has a CSS-property "position" with value "fixed"
    * A user scrolls the page to the top of the page
    * Element "Sooslick Art banner Image" has a CSS-property "top" with value "0px"
    * Element "Sooslick Art banner Image" has a CSS-property "position" with value "fixed"

    # Step 3
    * Element "Main page links Block -> Main page Link" has a text "MAIN PAGE"
    * A user hovers the cursor over the element "Main page links Block -> Main page Link"
    * Element "Main page links Block -> Main page Link" has a CSS-property "animation-name" with value "flicker"

    * Element "Main page links Block -> All projects Link" has a text "ALL PROJECTS"
    * A user hovers the cursor over the element "Main page links Block -> All projects Link"
    * Element "Main page links Block -> All projects Link" has a CSS-property "animation-name" with value "flicker"

    * Element "Main page links Block -> About us Link" has a text "ABOUT US"
    * A user hovers the cursor over the element "Main page links Block -> About us Link"
    * Element "Main page links Block -> About us Link" has a CSS-property "animation-name" with value "flicker"

    * Element "Main page links Block -> Portfolio Link" has a text "PORTFOLIO"
    * A user hovers the cursor over the element "Main page links Block -> Portfolio Link"
    * Element "Main page links Block -> Portfolio Link" has a CSS-property "animation-name" with value "flicker"

    # Step 4
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

    # Step 5
    * A user follows the link "https://sooslick.art/definetely-unexisting-page-i-swear"
    * "Sooslick.Art Project - Error page" page opens
    * Element "Error Header" has a text "404 NOT FOUND"

    * A user follows the link "https://sooslick.art/test-php-extension.php"
    * "Sooslick.Art Project - Error page" page opens
    * Element "Error Header" has a text "404 NOT FOUND"

    * A user follows the link "https://sooslick.art/test-unexisting-directory/"
    * "Sooslick.Art Project - Error page" page opens
    * Element "Error Header" has a text "404 NOT FOUND"
