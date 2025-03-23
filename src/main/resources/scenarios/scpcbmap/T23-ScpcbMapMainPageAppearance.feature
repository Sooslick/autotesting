Feature: Sooslick.Art - SCP: Containment Breach map

  @Test @T23 @SCPCBMap @SooslickArt
  Scenario: SCP: Containment Breach map page - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/scpcbmap/"
    * "SCP: Containment Breach map" page opens
    * The active tab has a title "SCP: Containment Breach map"
    * The page has header meta with name "author" and value "Sooslick"
    * The page has header meta with name "description" and value "SCP: Containment Breach v1.3.11 map. Preview SCP:CB seeds on single online webpage without launching the game!"
    * The page has header meta with name "theme-color" and value "gray"

    * All elements from the following list are visible
      | Seed Block -> Vanilla seed input     |
      | Seed Block -> Vanilla seed text      |
      | Seed Block -> Vanilla seed hint      |
      | Seed Block -> Modded seed input      |
      | Seed Block -> Modded seed text       |
      | Seed Block -> Modded seed hint       |
      | Seed Block -> Blank fields hint      |
      | Seed Block -> Create Map button      |
      | App Info Block -> Sooslick link      |
      | App Info Block -> Download game link |
    * All elements from the following list are not visible
      | Map Block                  |
      | Seed Block -> Loading icon |

    # Step 2
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value ""
    * Element "Seed Block -> Vanilla seed text" has a text "Vanilla game seed"
    * Element "Seed Block -> Vanilla seed hint" has a text "Up to 15 ASCII characters"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value ""
    * Element "Seed Block -> Modded seed text" has a text "Speedrun Mod seed"
    * Element "Seed Block -> Modded seed hint" has a text "Number in range 1, 2147483647"
    * Element "Seed Block -> Blank fields hint" has a text "Leave fields blank for random seed"
    * Element "Seed Block -> Create Map button" has a text "CREATE MAP"

    # Step 3
    * List "App Info Block" consists of items, where "List Item" has text
      | SCP: Containment Breach v1.3.11 map                       |
      | [regexp] App version: v1\.[0-9]+(\.[0-9]+)? \/\/ [0-9]{6} |
      | Created by @Sooslick for speedrunning community           |
      | Download game: Official website                           |
      | Download Speedrun Mod: Github link                        |
    * Element "App Info Block -> Sooslick link" has an attribute "href" with value "[substring] sooslick.art"
    * Element "App Info Block -> Download game link" has an attribute "href" with value "https://www.scpcbgame.com/"
