Feature: Sooslick.Art - Theme Randomizer

  @Test @T19 @Themegen @SooslickArt
  Scenario: Theme Randomizer learn page - normal appearance

    # todo add test to tms
    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/themegen/testPair"
    * "Theme Randomizer Learn page" page opens
    * The active tab has a title "Литературный рандомайзер"
  
    # Step 2
    * Element "Home Link" has a text "Вернуться на главную"
    * Element "Home Link" has an attribute "href" with value "[substring] index"

    # Step 3
    * Element "Mode Label" has a text "Вначале новые"
    * A user clicks on the element "Mode Switch"
    * Element "Mode Label" has a text "Вначале существующие"
    * A user clicks on the element "Mode Switch"
    * Element "Mode Label" has a text "Вначале новые"

    # Step 4
    * Element "First word Text" contains any text
    * Element "Second word Text" contains any text