Feature: Sooslick.Art - PvPetris

  @Test @T28 @PvPetris @SooslickArt
  Scenario: PvPetris main page - score table

    * A user establishes a database connection "pvpetris db" with following connection string
      | {property: sooslick.jdbc.connection} |

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/pvpetris"
    * "PvPetris main page" page opens
    * The active tab has a title "PvPetris"

    # Step 2
    * Element "Scores Header" has a text "PvPetris score table"
    * Table header of "Scores Table" has following headers
      | PLACE       |
      | NAME        |
      | SCORE       |
      | LINES       |
      | TTR%        |
      | START LEVEL |
    * Table "Scores Table" sorted by "SCORE" column in descending order, comparing as "numbers"

    * A user executes following SQL using the connection "pvpetris db" and saves the result to variable "scores"
      | SELECT * FROM PVPETRIS ORDER BY SCORE DESC LIMIT 100 |
    * Table "Scores Table" content matches variable "scores", using following mapping
      | NAME        | NAME       |
      | SCORE       | SCORE      |
      | LINES       | BURN       |
    #  | TTR%        | TTR        | # todo this column has some features on Web page (this is also not considered in test case)
      | START LEVEL | STARTLEVEL |
