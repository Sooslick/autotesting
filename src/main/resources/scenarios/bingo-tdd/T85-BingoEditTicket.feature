Feature: Sooslick.Art - Bingo

  @Test @T85 @Bingo @SooslickArt
  Scenario: Sooslick.Art Bingo - edit ticket

    # todo: implement insert, add rollbacks
    * A user fulfills the precondition "Create Templates and Tickets for Bingo App"

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/bingo"
    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"

    # Step 2
    * A user clicks on "List Item" with number 1 in list "Templates List"
    * Element "Template preview Block -> Template name Text" is visible
    * A user remembers the text in element "Template preview Block -> Template name Text" to variable "Template Name"
    * A user clicks on the element "Template preview Block -> Create ticket Button"

    # Step 3
    * "Bingo Ticket page" page opens
    * A user remembers the value "{random ascii string: {random number: 1-50}}" as variable "Ticket Name"
    * A user types "{variable: Ticket Name}" to "Ticket Name field"
    * A user remembers the value "{random number: 1-12}" as variable "Random Cell 1"
    * A user clicks on "Item" with number {variable: Random Cell 1} in list "Bingo grid"
    * A user clicks on the element "Save button"

    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"
    * List "Tickets List" has an item, where "Item" has text "{variable: Ticket Name}"

    # Step 4
    * A user clicks on "Item" with text "{variable: Ticket Name}" in list "Tickets List"
    * A user clicks on the element "Ticket preview Block -> Edit ticket Button"

    * "Bingo Ticket page" page opens
    * Element "Ticket Name field" has an attribute "editable" with value "true"
    * Element "Ticket Name field" has an attribute "value" with value "{variable: Ticket Name}"
    * "Item" with number {variable: Random Cell 1} in list "Bingo grid" has an attribute "striked" with value "true"
    * Element "Save button" has no attribute "disabled"
    * Element "Save & Seal button" has no attribute "disabled"

    # Step 5
    * A user remembers the value "{random ascii string: {random number: 1-50}}" as variable "Ticket Name Edited"
    * A user clears element "Ticket Name field"
    * A user types "{variable: Ticket Name Edited}" to "Ticket Name field"
    * A user remembers the value "{random number: 14-25}" as variable "Random Cell 2"
    * A user clicks on "Item" with number {variable: Random Cell 2} in list "Bingo grid"
    * A user clicks back button in browser toolbar

    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"
    * List "Tickets List" has an item, where "Item" has text "{variable: Ticket Name}"
    * List "Tickets List" has no items, where "Item" has text "{variable: Ticket Name Edited}"

    # Step 6
    * A user clicks on "Item" with text "{variable: Ticket Name}" in list "Tickets List"
    * A user clicks on the element "Ticket preview Block -> Edit ticket Button"

    * "Bingo Ticket page" page opens
    * Element "Ticket Name field" has an attribute "editable" with value "true"
    * Element "Ticket Name field" has an attribute "value" with value "{variable: Ticket Name}"
    * "Item" with number {variable: Random Cell 1} in list "Bingo grid" has an attribute "striked" with value "true"
    * "Item" with number {variable: Random Cell 2} in list "Bingo grid" has no attribute "striked"
    * Element "Save button" has no attribute "disabled"
    * Element "Save & Seal button" has no attribute "disabled"

    # Step 7
    * A user clears element "Ticket Name field"
    * A user types "{variable: Ticket Name Edited}" to "Ticket Name field"
    * A user clicks on "Item" with number {variable: Random Cell 2} in list "Bingo grid"
    * A user clicks on the element "Save button"

    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"
    * List "Tickets List" has no items, where "Item" has text "{variable: Ticket Name}"
    * List "Tickets List" has an item, where "Item" has text "{variable: Ticket Name Edited}"

    # Step 8
    * A user clicks on "Item" with text "{variable: Ticket Name Edited}" in list "Tickets List"
    * A user clicks on the element "Ticket preview Block -> Edit ticket Button"

    * "Bingo Ticket page" page opens
    * Element "Ticket Name field" has an attribute "editable" with value "true"
    * Element "Ticket Name field" has an attribute "value" with value "{variable: Ticket Name Edited}"
    * "Item" with number {variable: Random Cell 1} in list "Bingo grid" has an attribute "striked" with value "true"
    * "Item" with number {variable: Random Cell 2} in list "Bingo grid" has an attribute "striked" with value "true"
    * Element "Save button" has no attribute "disabled"
    * Element "Save & Seal button" has no attribute "disabled"
