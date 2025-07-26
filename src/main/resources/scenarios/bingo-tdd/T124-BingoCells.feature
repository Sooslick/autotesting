Feature: Sooslick.Art - Bingo

  @Test @T124 @Bingo @SooslickArt
  Scenario: Sooslick.Art Bingo - marking cells

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/bingo"
    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"

    # Step 2
    * A user clicks on "List Item" with number 1 in list "Templates List"
    * A user clicks on the element "Template preview Block -> Create ticket Button"

    * "Bingo Ticket page" page opens
    * List "Bingo grid" has 25 items
    * Each "Item" in list "Bingo grid" has no attribute "striked"

    # Step 3
    * A user clicks on "Item" with number 1 in list "Bingo grid"
    * "Item" with number 1 in list "Bingo grid" has an attribute "striked" with value "true"
    * Each item in list "Bingo grid" has following elements
      | Cross mark |

    # Step 4
    * A user clicks on "Item" with number 2 in list "Bingo grid"
    * "Item" with number 2 in list "Bingo grid" has an attribute "striked" with value "true"
    * A user clicks on "Item" with number 3 in list "Bingo grid"
    * "Item" with number 3 in list "Bingo grid" has an attribute "striked" with value "true"
    * A user clicks on "Item" with number 4 in list "Bingo grid"
    * "Item" with number 4 in list "Bingo grid" has an attribute "striked" with value "true"
    * A user clicks on "Item" with number 5 in list "Bingo grid"
    * "Item" with number 5 in list "Bingo grid" has an attribute "striked" with value "true"
    * Item with number 1 in list "Bingo grid" has element "Horizontal strike" visible

    # Step 5
    * A user clicks on "Item" with number 7 in list "Bingo grid"
    * "Item" with number 7 in list "Bingo grid" has an attribute "striked" with value "true"
    * A user clicks on "Item" with number 12 in list "Bingo grid"
    * "Item" with number 12 in list "Bingo grid" has an attribute "striked" with value "true"
    * A user clicks on "Item" with number 17 in list "Bingo grid"
    * "Item" with number 17 in list "Bingo grid" has an attribute "striked" with value "true"
    * A user clicks on "Item" with number 22 in list "Bingo grid"
    * "Item" with number 22 in list "Bingo grid" has an attribute "striked" with value "true"
    * Item with number 2 in list "Bingo grid" has element "Vertical strike" visible

    # Step 6
    * A user clicks on "Item" with number 13 in list "Bingo grid"
    * "Item" with number 13 in list "Bingo grid" has an attribute "striked" with value "true"
    * A user clicks on "Item" with number 19 in list "Bingo grid"
    * "Item" with number 19 in list "Bingo grid" has an attribute "striked" with value "true"
    * A user clicks on "Item" with number 25 in list "Bingo grid"
    * "Item" with number 25 in list "Bingo grid" has an attribute "striked" with value "true"
    * "Cross mark" with number 1 in list "Bingo grid" has an attribute "diag-striked" with value "true"

    # Step 7
    * A user clicks on "Item" with number 1 in list "Bingo grid"
    * A user clicks on "Item" with number 2 in list "Bingo grid"
    * A user clicks on "Item" with number 3 in list "Bingo grid"
    * A user clicks on "Item" with number 4 in list "Bingo grid"
    * A user clicks on "Item" with number 5 in list "Bingo grid"

    * A user clicks on "Item" with number 7 in list "Bingo grid"
    * A user clicks on "Item" with number 12 in list "Bingo grid"
    * A user clicks on "Item" with number 17 in list "Bingo grid"
    * A user clicks on "Item" with number 22 in list "Bingo grid"

    * A user clicks on "Item" with number 13 in list "Bingo grid"
    * A user clicks on "Item" with number 19 in list "Bingo grid"
    * A user clicks on "Item" with number 25 in list "Bingo grid"

    * Each "Item" in list "Bingo grid" has no attribute "striked"

    * Item with number 1 in list "Bingo grid" has element "Horizontal strike" not visible
    * Item with number 6 in list "Bingo grid" has element "Horizontal strike" not visible
    * Item with number 11 in list "Bingo grid" has element "Horizontal strike" not visible
    * Item with number 16 in list "Bingo grid" has element "Horizontal strike" not visible
    * Item with number 21 in list "Bingo grid" has element "Horizontal strike" not visible

    * Item with number 1 in list "Bingo grid" has element "Vertical strike" not visible
    * Item with number 2 in list "Bingo grid" has element "Vertical strike" not visible
    * Item with number 3 in list "Bingo grid" has element "Vertical strike" not visible
    * Item with number 4 in list "Bingo grid" has element "Vertical strike" not visible
    * Item with number 5 in list "Bingo grid" has element "Vertical strike" not visible