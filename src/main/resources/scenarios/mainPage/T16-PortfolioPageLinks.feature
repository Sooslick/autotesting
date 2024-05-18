Feature: Sooslick.Art - Main Page

  @Test @T16 @SooslickArtMain @SooslickArt
  Scenario: Portfolio page - links

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/portfolio"
    * "Sooslick.Art Project - Portfolio page" page opens

    # Step 2
    * A user clicks on the element "Main page links Block -> Main page Link"
    * "Sooslick.Art Project - Main page" page opens
    * A user clicks back button in browser toolbar
    * "Sooslick.Art Project - Portfolio page" page opens

    # Step 3
    * A user clicks on the element "Main page links Block -> All projects Link"
    * "Sooslick.Art Project - Projects page" page opens
    * A user clicks back button in browser toolbar
    * "Sooslick.Art Project - Portfolio page" page opens

    # Step 4
    * A user clicks on the element "Main page links Block -> About us Link"
    * "Sooslick.Art Project - About page" page opens
    * A user clicks back button in browser toolbar
    * "Sooslick.Art Project - Portfolio page" page opens

    # Step 5
    * A user clicks on the element "Main page links Block -> Portfolio Link"
    * "Sooslick.Art Project - Portfolio page" page opens

    # Step 6
    * A user clicks on the element "All projects Link"
    * "Sooslick.Art Project - Projects page" page opens
