Feature: Sooslick.Art - Main Page

  @Test @T5 @SooslickArtMain @SooslickArt
  Scenario: Main page - backend

    * A user establishes a database connection "dbconn", using following connection string
      | {property: sooslick.jdbc.connection} |
    * A user executes following SQL using the connection "dbconn" and saves the result as map "featured project"
      | SELECT * FROM SOOSLICK_PROJECTS WHERE FEATURED > 0 ORDER BY `ORDER` ASC LIMIT 1 |

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art"
    * "Sooslick.Art Project - Main page" page opens

    # Step 2
    * Element "Featured project Link" has an attribute "href" with value "[substring]{variable: featured project -> link}"
    * Element "Featured project Link" has an attribute "id" with value "{variable: featured project -> id}"
    * Element "Featured project Link -> Project Image" has an attribute "src" with value "[substring]{variable: featured project -> path_banner_big}"
