Feature: Sooslick.Art - Bingo

  @Test @T86 @Bingo @SooslickArt
  Scenario: Sooslick.Art Bingo - seal ticket

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
    * "Bingo Ticket page" page opens

    # Step 3
    * A user remembers the value "{random ascii string: {random number: 1-50}}" as variable "Ticket Name"
    * A user types "{variable: Ticket Name}" to "Ticket Name field"
    * Element "Ticket Name field" has an attribute "value" with value "{variable: Ticket Name}"
    * A user remembers the value "{random number: 1-12}" as variable "Random Cell 1"
    * A user clicks on "Item" with number {variable: Random Cell 1} in list "Bingo grid"
    * A user clicks on the element "Save & Seal button"

    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"
    * Element "Templates Tab" has an attribute "class" which is not "[substring] tab-selected"
    * Element "Tickets Tab" has an attribute "class" with value "[substring] tab-selected"

    # Step 4
    * A user clicks on "Item" with text "{variable: Ticket Name}" in list "Tickets List"
    * Element "Ticket preview Block -> Preview Image" is visible
    * Element "Ticket preview Block -> Ticket name Text" has a text "{variable: Ticket Name}"
    * Element "Ticket preview Block -> Template name Text" has a text "{variable: Template Name}"
    * Element "Ticket preview Block -> Ticket owner Text" has a text "Anonymous"
    * Element "Ticket preview Block -> Sealed ticket Text" has a text "sealed"
    * Element "Ticket preview Block -> Created by text" has a text "Created by Anonymous and sealed"
    * Element "Ticket preview Block -> Ticket date Text" has a text "{current date in format: yyyy-MM-dd HH:mm}"
    * Element "Ticket preview Block -> View ticket Button" has no attribute "disabled"
    * Element "Ticket preview Block -> Edit ticket Button" is not visible

    # Step 5
    * A user clicks on the element "Ticket preview Block -> View ticket Button"
    * "Bingo Ticket page" page opens
    * Element "Ticket Name field" has an attribute "editable" with value "false"
    * Element "Ticket Name field" has an attribute "value" with value "{variable: Ticket Name}"
    * "Item" with number {variable: Random Cell 1} in list "Bingo grid" has an attribute "striked" with value "true"
    * Element "Sealed text" has a text "Created by Anonymous and sealed"
    * Element "Save button" is not visible
    * Element "Save & Seal button" is not visible

    # Step 6
    * A user clears element "Ticket Name field"
    * Element "Ticket Name field" has an attribute "value" with value "{variable: Ticket Name}"
    * A user clicks on "Item" with number {variable: Random Cell 1} in list "Bingo grid"
    * "Item" with number {variable: Random Cell 1} in list "Bingo grid" has an attribute "striked" with value "true"
    * A user remembers the value "{random number: 14-25}" as variable "Random Cell 2"
    * A user clicks on "Item" with number {variable: Random Cell 2} in list "Bingo grid"
    * "Item" with number {variable: Random Cell 2} in list "Bingo grid" has no attribute "striked"
