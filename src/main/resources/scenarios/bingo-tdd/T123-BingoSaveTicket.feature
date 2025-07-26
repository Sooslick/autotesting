Feature: Sooslick.Art - Bingo

  @Test @T123 @Bingo @SooslickArt
  Scenario: Sooslick.Art Bingo - save ticket

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
    * Element "Ticket Name field" has an attribute "value" with value "{variable: Ticket Name}"

    # Step 4
    * A user remembers the value "{random number: 1-25}" as variable "Random Cell"
    * "Item" with number {variable: Random Cell} in list "Bingo grid" has no attribute "striked"
    * A user clicks on "Item" with number {variable: Random Cell} in list "Bingo grid"
    * "Item" with number {variable: Random Cell} in list "Bingo grid" has an attribute "striked" with value "true"

    # Step 5
    * A user clicks on the element "Save button"
    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"
    * Element "Templates Tab" has an attribute "class" which is not "[substring] tab-selected"
    * Element "Tickets Tab" has an attribute "class" with value "[substring] tab-selected"

    # Step 6
    * A user clicks on "Item" with text "{variable: Ticket Name}" in list "Tickets List"
    * Element "Ticket preview Block -> Preview Image" is visible
    * Element "Ticket preview Block -> Ticket name Text" has a text "{variable: Ticket Name}"
    * Element "Ticket preview Block -> Template name Text" has a text "{variable: Template Name}"
    * Element "Ticket preview Block -> Ticket owner Text" has a text "Anonymous"
    * Element "Ticket preview Block -> Sealed ticket Text" has a text "not sealed yet"
    * Element "Ticket preview Block -> Created by Text" has a text "Created by Anonymous and not sealed yet"
    * Element "Ticket preview Block -> Ticket date Text" has a text "{current date in format: yyyy-MM-dd HH:mm}"
    * Element "Ticket preview Block -> Edit ticket Button" has no attribute "disabled"
    * Element "Ticket preview Block -> View ticket Button" is not visible
