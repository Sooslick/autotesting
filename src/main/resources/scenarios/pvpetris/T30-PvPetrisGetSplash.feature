Feature: Sooslick.Art - PvPetris

  @Test @T30 @PvPetris @SooslickArt
  Scenario: PvPetris API - getSplash

    # Step 1
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/getSplash" and writes the response to variable "r"
      | method | GET |
    * HTTP response from variable "r" has following properties
      | code | 200         |
      | body | [regexp] .+ |