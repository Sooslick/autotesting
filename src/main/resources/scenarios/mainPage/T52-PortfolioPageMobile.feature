Feature: Sooslick.Art - Main Page

  @Test @T52 @SooslickArtMain @SooslickArt
  Scenario: Portfolio page - appearance (mobile)

    # Step 1
    * A user opens a new browser window, emulating device "iPad Mini", and follows the link "https://sooslick.art/portfolio"
    * "Sooslick.Art Project - Portfolio page" page opens
    * The active tab has a title "Sooslick.Art - Portfolio"

    # Step 2
    * Element "Main page links Block -> Main page Link" has a height equals to "60" pixels
    * A user remembers the Y coordinate of element "Main page links Block -> Main page Link" as variable "baseline"

    * Element "Main page links Block -> All projects Link" has a text "PROJECTS"
    * Element "Main page links Block -> All projects Link" has a height equals to "60" pixels
    * Element "Main page links Block -> All projects Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> About us Link" has a height equals to "60" pixels
    * Element "Main page links Block -> About us Link" has Y coordinate equals to "{variable: baseline}" pixels

    * Element "Main page links Block -> Portfolio Link" has a height equals to "60" pixels
    * Element "Main page links Block -> Portfolio Link" has Y coordinate equals to "{variable: baseline}" pixels

    # Step 3
    * Each item in list "Showcase projects List" has following elements
      | Project Link -> Project Image       |
      | Project Link -> Click to see Button |
      | Project descriptions List           |
    * Each "Project Block" in list "Showcase projects List" have an alternating CSS-property "flex-direction" from the following list
      | row         |
      | row-reverse |
    * Each sublist "Project descriptions List" in list "Showcase projects List" has the following elements
      | White dot |
      | Text line |