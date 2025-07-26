Feature: Sooslick.Art - Bingo

  @Test @T36 @Bingo @SooslickArt
  Scenario: Sooslick.Art Bingo - light theme

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/bingo"
    * "Bingo Main page" page opens
    * The active tab has a title "Bingo!"
    * Element "Page body" has a CSS-property "background" with value "rgba(0, 0, 0, 1)"
    * Element "Page body" has a CSS-property "color" with value "rgba(255, 255, 255, 1)"
    * Element "Page background" is visible
    * Element "Templates List" has a CSS-property "border-color" with value "rgba(255, 255, 255, 1)"
    * Element "Template preview Block" has a CSS-property "border-color" with value "rgba(255, 255, 255, 1)"

    # Step 2
    * A user clicks on the element "Color theme Switch"
    * Element "Page body" has a CSS-property "filter" with value "invert(1)"

    # Step 3
    * A user clicks on the element "Color theme Switch"
    * Element "Page body" has a CSS-property "filter" with value "invert(0)"