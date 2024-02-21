Feature: Sooslick.Art - Main Page

  @Test @T1 @SooslickArtMain
  Scenario: Main page - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art"
    * "Sooslick.Art Project - Main page" page opens
    * The active tab has a title "Sooslick.Art Project"
    * All elements from the following list are visible
      | Main banner Image                            |
      | Sooslick Art banner Image                    |
      | Main page links Block -> Main page Link      |
      | Main page links Block -> All projects Link   |
      | Main page links Block -> About us Link       |
      | Main page links Block -> Portfolio Link      |
      | About us Text Block                          |
      | Featured project Header                      |
      | Featured project Link -> Project Image       |
      | Featured project Link -> Click to see Button |
      | Other projects Link                          |
      | Footer Block -> Footer separator             |
      | Footer Block -> Check out our socials Text   |
      | Footer Block -> Socials Image list           |
      | Footer Block -> Copyrights Text              |

    # Step 2
    * Element "Sooslick Art banner Image" has a CSS-property "top" with value "0px"
    * Element "Sooslick Art banner Image" has a CSS-property "position" with value "absolute"
    * A user scrolls the page to element "Footer Block"
    * Element "Sooslick Art banner Image" has a CSS-property "top" with value "-13px"
    * Element "Sooslick Art banner Image" has a CSS-property "position" with value "fixed"
    * A user scrolls the page to the top of the page
    * Element "Sooslick Art banner Image" has a CSS-property "top" with value "0px"
    * Element "Sooslick Art banner Image" has a CSS-property "position" with value "absolute"

    # Step 3
    * Element "Main page links Block -> Main page Link" has a text "MAIN PAGE"
    * Element "Main page links Block -> Main page Link" has a height equals to "60" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> Main page Link" as variable "baseline"
    * A user hovers the cursor over the element "Main page links Block -> Main page Link"
    * Element "Main page links Block -> Main page Link" has a CSS-property "animation-name" with value "flicker"

    * Element "Main page links Block -> All projects Link" has a text "ALL PROJECTS"
    * Element "Main page links Block -> All projects Link" has a height equals to "60" pixels
    * Element "Main page links Block -> All projects Link" has Y coordinate equals to "{variable: baseline}" pixels
    * A user hovers the cursor over the element "Main page links Block -> All projects Link"
    * Element "Main page links Block -> All projects Link" has a CSS-property "animation-name" with value "flicker"

    * Element "Main page links Block -> About us Link" has a text "ABOUT US"
    * Element "Main page links Block -> About us Link" has a height equals to "60" pixels
    * Element "Main page links Block -> About us Link" has Y coordinate equals to "{variable: baseline}" pixels
    * A user hovers the cursor over the element "Main page links Block -> About us Link"
    * Element "Main page links Block -> About us Link" has a CSS-property "animation-name" with value "flicker"

    * Element "Main page links Block -> Portfolio Link" has a text "PORTFOLIO"
    * Element "Main page links Block -> Portfolio Link" has a height equals to "60" pixels
    * Element "Main page links Block -> Portfolio Link" has Y coordinate equals to "{variable: baseline}" pixels
    * A user hovers the cursor over the element "Main page links Block -> Portfolio Link"
    * Element "Main page links Block -> Portfolio Link" has a CSS-property "animation-name" with value "flicker"

    # Step 4
    * Element "Featured project Header" has a text "FEATURED PROJECT"
    * Element "Featured project Link -> Click to see Button" has a text "CLICK TO SEE"
    * Element "Featured project Link -> Click to see Button" has a CSS-property "background-color" with value "rgba(240, 240, 240, 1)"

    * Image "Featured project Link -> Project Image" has a valid source
    * Element "Featured project Link -> Project Image" has a CSS-property "filter" with value "grayscale(1)"
    * A user hovers the cursor over the element "Featured project Link -> Project Image"
    * Element "Featured project Link -> Project Image" has a CSS-property "filter" with value "none"

    # Step 5
    * Element "Other projects Link" has a text "OTHER PROJECTS"
    * Element "Other projects Link" has an attribute "href" with value "https://sooslick.art/projects"
    * A user hovers the cursor over the element "Other projects Link"
    * Element "Other projects Link" has a CSS-property "animation-name" with value "flicker"

    # Step 6
    * Element "Footer Block -> Footer separator" has a CSS-property "border-color" with value "rgb(255, 255, 255)"
    * Element "Footer Block -> Check out our socials Text" has a text "Check out our socials"
    * Element "Footer Block -> Copyrights Text" has a text "© 2013 - {current date in format: yyyy} Sooslick.Art Project"
    # todo check logos
