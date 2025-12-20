Feature: Sooslick.Art - Main Page

  @Test @T12 @SooslickArtMain @SooslickArt
  Scenario: Portfolio page - backend

    * A user establishes a database connection "dbconn", using following connection string
      | {property: sooslick.jdbc.connection} |
    * A user executes following SQL using the connection "dbconn" and saves the result as table "showcase projects"
      | SELECT * FROM SOOSLICK_PROJECTS WHERE SHOWCASE ORDER BY `ORDER` ASC |
    * A user executes following SQL using the connection "dbconn" and saves the result as column "project descriptions"
      | SELECT GROUP_CONCAT(r.CONTENT SEPARATOR '\n') FROM                          |
      | (SELECT p.ID, p.`ORDER`, d.CONTENT                                          |
      | FROM SOOSLICK_PROJECTS p JOIN SOOSLICK_PROJECT_DETAILS d ON p.ID = d.PRJ_ID |
      | WHERE SHOWCASE ORDER BY p.`ORDER` ASC, d.`ORDER` ASC) r                     |
      | GROUP BY r.ID ORDER BY r.`ORDER`                                            |
    * A user performs regex find "<[^>]*>" and replace to "" for each entry in list variable "project descriptions"

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/portfolio"
    * "Sooslick.Art Project - Portfolio page" page opens

    # Step 2
    * A user reads property "id" of each object from list variable "showcase projects" and saves result to variable "ids"
    * A user reads property "link" of each object from list variable "showcase projects" and saves result to variable "hrefs"
    * A user reads property "path_banner_small" of each object from list variable "showcase projects" and saves result to variable "banners"
    * List "Showcase projects List" consists of items, where "Project Link" has attribute "id" from list variable "ids"
    * List "Showcase projects List" consists of items, where "Project Link" has attribute "href" from list variable "hrefs"
    * List "Showcase projects List" consists of items, where "Project Link -> Project Image" has attribute "src" from list variable "banners"
    * List "Showcase projects List" consists of items, where "Project descriptions List" has text from list variable "project descriptions"