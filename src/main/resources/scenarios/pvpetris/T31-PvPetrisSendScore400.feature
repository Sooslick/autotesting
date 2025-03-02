Feature: Sooslick.Art - PvPetris

  @Test @T31 @PvPetris @SooslickArt
  Scenario: PvPetris API - sendScore, code 400

    # Step 1
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/sendScore" and writes the response to variable "r"
      | method | GET |
    * HTTP response from variable "r" has following properties
      | code | 400                                                                                     |
      | body | No name passed. No score passed. No lines passed. No startlevel passed. No hash passed. |

    # Step 2
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/sendScore" and writes the response to variable "r"
      | method          | GET    |
      | query parameter | name=a |
    * HTTP response from variable "r" has following properties
      | code | 400                                                                     |
      | body | No score passed. No lines passed. No startlevel passed. No hash passed. |

    # Step 3
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/sendScore" and writes the response to variable "r"
      | method          | GET     |
      | query parameter | name=a  |
      | query parameter | score=1 |
    * HTTP response from variable "r" has following properties
      | code | 400                                                    |
      | body | No lines passed. No startlevel passed. No hash passed. |

    # Step 4
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/sendScore" and writes the response to variable "r"
      | method          | GET     |
      | query parameter | name=a  |
      | query parameter | score=1 |
      | query parameter | burn=1  |
    * HTTP response from variable "r" has following properties
      | code | 400                                   |
      | body | No startlevel passed. No hash passed. |

    # Step 5
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/sendScore" and writes the response to variable "r"
      | method          | GET          |
      | query parameter | name=a       |
      | query parameter | score=1      |
      | query parameter | burn=1       |
      | query parameter | startlevel=1 |
    * HTTP response from variable "r" has following properties
      | code | 400             |
      | body | No hash passed. |

    # Step 6
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/sendScore" and writes the response to variable "r"
      | method          | GET          |
      | query parameter | name=a       |
      | query parameter | score=1      |
      | query parameter | burn=1       |
      | query parameter | startlevel=1 |
      | query parameter | hash=1       |
    * HTTP response from variable "r" has following properties
      | code | 400                            |
      | body | Hash is not identical. Name: a |