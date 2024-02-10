Feature: Sooslick.Art - Main Page

  @Test @T1 @SooslickArtMain
  Scenario: Main page - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art"
    * "Sooslick.Art Project - Main page" page opens
    * All elements from the following list are visible
      | Main banner Image                            |
      | Sooslick Art banner Image                    |
      | Main page links Block                        |
      | Main page links Block -> Main page Link      |
      | Main page links Block -> All projects Link   |
      | Main page links Block -> About us Link       |
      | Main page links Block -> Portfolio Link      |
      | About us Text Block                          |
      | Featured project Header                      |
      | Featured project Link                        |
      | Featured project Link -> Project Image       |
      | Featured project Link -> Click to see Button |
      | All projects Link                            |
      | Footer Block                                 |
      | Footer Block -> Footer separator             |
      | Footer Block -> Check out our socials Text   |
      | Footer Block -> Socials Image list           |
      | Footer Block -> Copyrights Text              |

    # Step 2
    * A user scrolls the page to element "Footer Block"
