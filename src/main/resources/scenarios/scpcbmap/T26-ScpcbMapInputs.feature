Feature: Sooslick.Art - SCP: Containment Breach map

  @Test @T26 @SCPCBMap @SooslickArt
  Scenario: SCP: Containment Breach map page - inputs

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/scpcbmap/"
    * "SCP: Containment Breach map" page opens
    * The active tab has a title "SCP: Containment Breach map"

    # Step 2
    * A user types "{random ascii string: 1}" to "Seed Block -> Vanilla seed input"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "[regexp] [0-9]+"
    * A user remembers the attribute "value" of element "Seed Block -> Modded seed input" to variable "seedNumber"

    * A user types "{random ascii string: 1}" to "Seed Block -> Vanilla seed input"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "[regexp] [0-9]+"
    * Element "Seed Block -> Modded seed input" has an attribute "value" which is not "{variable: seedNumber}"

    * A user clears element "Seed Block -> Vanilla seed input"
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value "[empty]"
    * A user remembers the value "{random ascii string: 15}" as variable "seedString"
    * A user types "{variable: seedString}" to "Seed Block -> Vanilla seed input"
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value "{variable: seedString}"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "[regexp] [0-9]+"

    # Step 3
    * A user remembers the attribute "value" of element "Seed Block -> Modded seed input" to variable "seedNumber"
    * A user press "Back_space" 1 times
    * Element "Seed Block -> Modded seed input" has an attribute "value" which is not "{variable: seedNumber}"

    * A user clears element "Seed Block -> Vanilla seed input"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "[empty]"

    # Step 4
    * A user types "йцукенгшщзххъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ" to "Seed Block -> Vanilla seed input"
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value "[empty]"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "[empty]"

    # Step 5
    * A user copies text "qwertyuiop1йцукенгшщ234567890" to clipboard
    * A user pastes from clipboard to "Seed Block -> Vanilla seed input"
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value "qwertyuiop12345"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "[regexp] [0-9]+"

    # Step 6
    * A user clicks on the element "Seed Block -> Modded seed input"
    * A user press "Back_space" 1 times
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value "[empty]"

    # Step 7
    * A user remembers the value "{random number: 1000000000-2147483646}" as variable "seedString"
    * A user clears element "Seed Block -> Modded seed input"
    * A user types "{variable: seedString}" to "Seed Block -> Modded seed input"
    * Element "Seed Block -> Vanilla seed input" has an attribute "value" with value "[empty]"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "{variable: seedString}"

    * A user types "{random number: 1-9}" to "Seed Block -> Modded seed input"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "2147483647"

    # Step 8
    * A user clears element "Seed Block -> Modded seed input"
    * A user types "qwertyuiop ADFGHJKL -.,(*&^%$#@" to "Seed Block -> Modded seed input"
    * Element "Seed Block -> Modded seed input" has an attribute "value" with value "[empty]"

    # Step 9
    * A user types "0" to "Seed Block -> Modded seed input"
    * Element "Seed Block -> Modded seed input" has an attribute "value" which is not "0"