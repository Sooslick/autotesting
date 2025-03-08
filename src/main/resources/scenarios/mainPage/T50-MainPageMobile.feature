Feature: Sooslick.Art - Main Page

  @Test @T50 @SooslickArtMain @SooslickArt
  Scenario: Main page - appearance (mobile)

    # Step 1
    * A user opens a new browser window, emulating device "iPhone SE", and follows the link "https://sooslick.art"
    * "Sooslick.Art Project - Main page" page opens

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