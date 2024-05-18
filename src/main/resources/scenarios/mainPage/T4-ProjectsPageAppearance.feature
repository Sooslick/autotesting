Feature: Sooslick.Art - Main Page

  @Test @T4 @SooslickArtMain @SooslickArt
  Scenario: Projects page - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/projects"
    * "Sooslick.Art Project - Projects page" page opens
    * The active tab has a title "Sooslick.Art Project"
    * All elements from the following list are visible
      | Sooslick Art banner Image                  |
      | Main page links Block -> Main page Link    |
      | Main page links Block -> All projects Link |
      | Main page links Block -> About us Link     |
      | Main page links Block -> Portfolio Link    |
      | About us Text Block                        |
      | Hidden projects Text Block                 |
      | Hidden projects Toggle                     |
      | Projects List                              |
      | Footer Block -> Footer separator           |
      | Footer Block -> Check out our socials Text |
      | Footer Block -> Socials Image list         |
      | Footer Block -> Copyrights Text            |

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
    * Element "Hidden projects Toggle" has a CSS-property "border-radius" with value "24px"
    * Element "Hidden projects Toggle" has a CSS-property "border-color" with value "rgb(255, 255, 255)"
    * Checkbox "Hidden projects Toggle" is not checked
    * A user clicks on the element "Hidden projects Toggle"
    * Checkbox "Hidden projects Toggle" is checked
    * A user clicks on the element "Hidden projects Toggle"
    * Checkbox "Hidden projects Toggle" is not checked
    * Element "Hidden projects Toggle" has a CSS-property "border-color" with value "rgb(0, 255, 255)" when clicked

    # Step 5
    * Each item in list "Projects List" has following elements
      | Project Image       |
      | Click to see Button |
    * Each "Project Image" in list "Projects List" has a CSS-property "filter" with value "grayscale(1)"
    * Each "Project Image" in list "Projects List" has a CSS-property "filter" with value "none" when hovered

    # Step 6
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
