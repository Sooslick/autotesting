Feature: Sooslick.Art - Main Page

  @Test @T2 @SooslickArtMain @SooslickArt
  Scenario: Main page - adaptability

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art"
    * "Sooslick.Art Project - Main page" page opens

    # Step 2
    * A user maximizes the browser window
    * Browser window width is bigger than "1200" pixels
    * Element "Main page links Block -> Main page Link" has a height equals to "60" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> Main page Link" as variable "baseline"

    * Element "Main page links Block -> All projects Link" has a text "ALL PROJECTS"
    * Element "Main page links Block -> All projects Link" has a height equals to "60" pixels
    * Element "Main page links Block -> All projects Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> About us Link" has a height equals to "60" pixels
    * Element "Main page links Block -> About us Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> Portfolio Link" has a height equals to "60" pixels
    * Element "Main page links Block -> Portfolio Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Featured project Link -> Project Image" has a width equals to "840" pixels

    # Step 3
    * A user sets browser window width to "{random number: 900-1150}" pixels
    * Element "Main page links Block -> Main page Link" has a height equals to "60" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> Main page Link" as variable "baseline"

    * Element "Main page links Block -> All projects Link" has a text "PROJECTS"
    * Element "Main page links Block -> All projects Link" has a height equals to "60" pixels
    * Element "Main page links Block -> All projects Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> About us Link" has a height equals to "60" pixels
    * Element "Main page links Block -> About us Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> Portfolio Link" has a height equals to "60" pixels
    * Element "Main page links Block -> Portfolio Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Featured project Link -> Project Image" has a width equals to "840" pixels

    # Step 4
    * A user remembers the value "{random number: 501-800}" as variable "width"
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

    * Element "Featured project Link -> Project Image" has a width lesser than "{variable: width}" pixels
