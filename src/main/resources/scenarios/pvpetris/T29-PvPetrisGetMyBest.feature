Feature: Sooslick.Art - PvPetris

  @Test @T29 @PvPetris @SooslickArt
  Scenario: PvPetris API - getMyBest

    * A user establishes a database connection "pvpetris db", using following connection string
      | {property: sooslick.jdbc.connection} |
    * A user executes following SQL using the connection "pvpetris db" and saves the result as variable "max score"
      | SELECT MAX(SCORE) FROM PVPETRIS |
    * A user executes following SQL using the connection "pvpetris db" and saves the result as variable "max lines"
      | SELECT MAX(BURN) FROM PVPETRIS |
    * A user executes following SQL using the connection "pvpetris db" and saves the result as variable "random player"
      | SELECT DISTINCT(NAME) FROM PVPETRIS ORDER BY RAND() LIMIT 1 |
    * A user executes following SQL using the connection "pvpetris db" and saves the result as variable "max player score"
      | SELECT MAX(SCORE) FROM PVPETRIS WHERE NAME = '{variable: random player}' |
    * A user executes following SQL using the connection "pvpetris db" and saves the result as variable "max player lines"
      | SELECT MAX(BURN) FROM PVPETRIS WHERE NAME = '{variable: random player}' |

    # Step 1
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/getMyBest" and writes the response to variable "r"
      | method | GET |
    * HTTP response from variable "r" has following properties
      | code | 400             |
      | body | No name passed. |

    # Step 2
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/getMyBest" and writes the response to variable "r"
      | method          | GET  |
      | query parameter | name |
    * HTTP response from variable "r" has following properties
      | code | 200                                             |
      | body | 0,0,{variable: max score},{variable: max lines} |

    # Step 3
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/getMyBest" and writes the response to variable "r"
      | method          | GET                            |
      | query parameter | name={variable: random player} |
    * HTTP response from variable "r" has following properties
      | code | 200                                                                                                   |
      | body | {variable: max player score},{variable: max player lines},{variable: max score},{variable: max lines} |

    # Step 4
    * A user sends a HTTP-request to "{property: sooslick.domain}/pvpetris/rest/getMyBest" and writes the response to variable "r"
      | method          | GET           |
      | query parameter | name='quotes' |
    * HTTP response from variable "r" has following properties
      | code | 200                                             |
      | body | 0,0,{variable: max score},{variable: max lines} |
