Feature: Sooslick.Art - Theme Randomizer

  @Test @T21 @Themegen @SooslickArt
  Scenario: Theme Randomizer learn page - normal appearance

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/themegen/testPair"
    * "Theme Randomizer Learn page" page opens
    * The active tab has a title "Литературный рандомайзер"

    # Step 2
    * Element "Rules Block" has a text "[substring] Генератор рандомных словосочетаний на основе простого алгоритма."
    * Element "Rules Block" has a text "[substring] Для обучения алгоритма нужны данные. Помогите их собрать:"
    * Element "Rules Block" has a text "[substring] Если словосочетание соответствует правилам русского языка, несмотря на возможную абсурдность - это хорошие данные, нажимайте «+1»"
    * Element "Rules Block" has a text "[substring] Если словосочетание не соответствует правилам русского языка - это плохие данные, их надо отфильтровать, нажимайте «-1»"
    * Element "Rules Block" has a text "[substring] Иногда истина может быть где-то посередине. Если есть сомнения - нажимайте «0»"
    * Element "Rules Block" has a text "[substring] Ошиблись? Ничего страшного, время и алгоритмы обучения исправят погрешность"

    # Step 3
    * Element "Home Link" has a text "Вернуться на главную"
    * Element "Home Link" has an attribute "href" with value "[substring] index"

    # Step 4
    * Element "Mode Label" has a text "Вначале новые"
    * A user clicks on the element "Mode Switch"
    * Element "Mode Label" has a text "Вначале существующие"
    * A user clicks on the element "Mode Switch"
    * Element "Mode Label" has a text "Вначале новые"

    # Step 5
    * Element "First word Text" contains any text
    * Element "Second word Text" contains any text
    # Elements visibility covered by @Required across Page Object