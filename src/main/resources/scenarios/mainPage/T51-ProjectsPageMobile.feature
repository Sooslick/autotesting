Feature: Sooslick.Art - Main Page

  @Test @T51 @SooslickArtMain @SooslickArt
  Scenario: Projects page - appearance (mobile)

    # Step 1
    * A user opens a new browser window, emulating device "Samsung Galaxy S8+", and follows the link "https://sooslick.art/projects"
    * "Sooslick.Art Project - Projects page" page opens

    * Element "Main page links Block -> Main page Link" has a height equals to "60" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> Main page Link" as variable "baseline"

    * Element "Main page links Block -> All projects Link" has a text "PROJECTS"
    * Element "Main page links Block -> All projects Link" has a height equals to "60" pixels
    * Element "Main page links Block -> All projects Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> About us Link" has a height equals to "60" pixels
    * Element "Main page links Block -> About us Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> Portfolio Link" has a height equals to "60" pixels
    * Element "Main page links Block -> Portfolio Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "About us Text Block" has a CSS-property "font-size" with value "38.4px"
    * Element "Hidden projects Text Block" has a CSS-property "font-size" with value "38.4px"

    * List "Projects List" items are in lines of 2 elements per line
