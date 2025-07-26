Feature: Sooslick.Art - Bingo

  @Test @T34 @Bingo @SooslickArt
  Scenario: Sooslick.Art Bingo - tickets list

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/bingo"
    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"

    * Element "Templates Tab" has an attribute "class" with value "[substring] tab-selected"
    * Element "Tickets Tab" has an attribute "class" which is not "[substring] tab-selected"

    # Step 2
    * A user clicks on the element "Tickets Tab"
    * Element "Templates Tab" has an attribute "class" which is not "[substring] tab-selected"
    * Element "Tickets Tab" has an attribute "class" with value "[substring] tab-selected"

    * Element "Templates List" is not visible
    * Element "Tickets List" is visible
    * Element "Template preview Block" is not visible
    * Element "Ticket preview Block" is visible

    # Step 3
    * List "Tickets List" has items
    * Element "Tickets List" has a CSS-property "text-align" with value "left"
    * Element "Template preview Block -> Preview Image" is not visible
    * Each "List Item" in list "Tickets List" has a CSS-property "border" with value "2px solid rgba(200, 200, 200, 1)" when hovered
    * Each "List Item" in list "Tickets List" has a CSS-property "border-radius" with value "0"
    * Element "Ticket preview Block -> Preview Image" is not visible

    # Step 4
    * A user clicks on "List Item" with number 1 in list "Tickets List"
    * "List Item" with number 1 in list "Tickets List" has a CSS-property "border" with value "2px solid rgba(255, 255, 255, 1)"
    * "List Item" with number 1 in list "Tickets List" has a CSS-property "border-radius" with value "0"
    * All elements from the following list are visible
      | Ticket preview Block -> Ticket name Text   |
      | Ticket preview Block -> Preview Image      |
      | Ticket preview Block -> Template name Text |
      | Ticket preview Block -> Ticket owner Text  |
      | Ticket preview Block -> Ticket date Text   |
      | Ticket preview Block -> Sealed ticket Text |
      | Ticket preview Block -> Edit ticket Button |
