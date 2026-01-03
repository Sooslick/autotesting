Feature: Sooslick.Art - Main Page

  @Test @T7 @SooslickArtMain @SooslickArt
  Scenario: Projects page - adaptability

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/projects"
    * "Sooslick.Art Project - Projects page" page opens

    # Step 2 - Links
    * A user sets browser window size to 1201, 900 pixels
    * Element "Main page links Block -> Main page Link" has a height equals to "60" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> Main page Link" as variable "baseline"

    * Element "Main page links Block -> All projects Link" has a text "ALL PROJECTS"
    * Element "Main page links Block -> All projects Link" has a height equals to "60" pixels
    * Element "Main page links Block -> All projects Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> About us Link" has a height equals to "60" pixels
    * Element "Main page links Block -> About us Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> Portfolio Link" has a height equals to "60" pixels
    * Element "Main page links Block -> Portfolio Link" has Y coordinate equals to "{variable: baseline}" pixels

    * List "Projects List" items are in lines of 3 elements per line
    * A user clicks on the element "Hidden projects Toggle"
    * List "Projects List" items are in lines of 3 elements per line
    * A user clicks on the element "Hidden projects Toggle"

    # Step 3
    * A user sets browser window width to "{random number: 900-1100}" pixels
    * Element "Main page links Block -> Main page Link" has a height equals to "60" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> Main page Link" as variable "baseline"

    * Element "Main page links Block -> All projects Link" has a text "PROJECTS"
    * Element "Main page links Block -> All projects Link" has a height equals to "60" pixels
    * Element "Main page links Block -> All projects Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> About us Link" has a height equals to "60" pixels
    * Element "Main page links Block -> About us Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> Portfolio Link" has a height equals to "60" pixels
    * Element "Main page links Block -> Portfolio Link" has Y coordinate equals to "{variable: baseline}" pixels

    * List "Projects List" items are in lines of 2 elements per line
    * A user clicks on the element "Hidden projects Toggle"
    * List "Projects List" items are in lines of 2 elements per line
    * A user clicks on the element "Hidden projects Toggle"

    # Step 4
    * A user remembers the value "{random number: 501-750}" as variable "width"
    * A user sets browser window width to "{variable: width}" pixels
    * Element "Main page links Block -> Main page Link" has a height equals to "60" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> Main page Link" as variable "baseline"

    * Element "Main page links Block -> All projects Link" has a text "PROJECTS"
    * Element "Main page links Block -> All projects Link" has a height equals to "60" pixels
    * Element "Main page links Block -> All projects Link" has Y coordinate bigger than "{variable: baseline}" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> All projects Link" as variable "baseline"

    * Element "Main page links Block -> About us Link" has a height equals to "60" pixels
    * Element "Main page links Block -> About us Link" has Y coordinate bigger than "{variable: baseline}" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> About us Link" as variable "baseline"

    * Element "Main page links Block -> Portfolio Link" has a height equals to "60" pixels
    * Element "Main page links Block -> Portfolio Link" has Y coordinate bigger than "{variable: baseline}" pixels

    * List "Projects List" items are in lines of 1 elements per line
    * A user clicks on the element "Hidden projects Toggle"
    * List "Projects List" items are in lines of 1 elements per line
