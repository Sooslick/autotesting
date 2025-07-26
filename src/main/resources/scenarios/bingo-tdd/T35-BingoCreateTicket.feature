Feature: Sooslick.Art - Bingo

  @Test @T35 @Bingo @SooslickArt
  Scenario: Sooslick.Art Bingo - Create ticket

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/bingo"
    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"

    # Step 2
    # todo: insert template precondition
    * A user clicks on "List Item" with number 1 in list "Templates List"
    * Element "Template preview Block -> Template name Text" is visible

    # Step 3
    * A user clicks on the element "Template preview Block -> Create ticket Button"
    * "Bingo Ticket page" page opens
    * All elements from the following list are visible
      | Ticket Name field  |
      | Bingo grid         |
      | Save button        |
      | Save & Seal button |

    * Element "Ticket Name field" has an attribute "editable" with value "true"
    * Element "Ticket Name field" has an attribute "placeholder" with value "title - edit me!"
    * Each "Item" in list "Bingo grid" has a text "[regexp] .+"
    * List "Bingo grid" items are in lines of 5 elements per line
    * List "Bingo grid" has 25 items

    * Element "Save button" has an attribute "disabled" with value "true"
    * Element "Save & Seal button" has an attribute "disabled" with value "true"
