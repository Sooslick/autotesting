Feature: Sooslick.Art - Main Page

  @Test @T3 @SooslickArtMain @SooslickArt
  Scenario: Main page - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art"
    * "Sooslick.Art Project - Main page" page opens
    * The active tab has a title "Sooslick.Art - Main Page"
    * The page has header meta with name "author" and value "Sooslick"
    * The page has header meta with name "description" and value "This is my personal page where I host my projects and cool stuff by my friends and partners. We mostly create games and do horror-related stuff, however we are not limited to specified genres or activities."
    * The page has header meta with name "theme-color" and value "black"

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
      | Footer Block                                 |

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
