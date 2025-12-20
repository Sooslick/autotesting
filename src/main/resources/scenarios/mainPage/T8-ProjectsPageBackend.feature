Feature: Sooslick.Art - Main Page

  @Test @T8 @SooslickArtMain @SooslickArt
  Scenario: Projects page - backend

    * A user establishes a database connection "dbconn", using following connection string
      | {property: sooslick.jdbc.connection} |
    * A user executes following SQL using the connection "dbconn" and saves the result as table "displayed projects"
      | SELECT * FROM SOOSLICK_PROJECTS WHERE VISIBILITY > 50 ORDER BY `ORDER` ASC |
    * A user executes following SQL using the connection "dbconn" and saves the result as table "displayed projects including hidden"
      | SELECT * FROM SOOSLICK_PROJECTS WHERE VISIBILITY > 0 ORDER BY `ORDER` ASC |

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/projects"
    * "Sooslick.Art Project - Projects page" page opens

    # Step 2
    * A user reads property "id" of each object from list variable "displayed projects" and saves result to variable "step2_ids"
    * A user reads property "link" of each object from list variable "displayed projects" and saves result to variable "hrefs"
    * A user reads property "path_banner_small" of each object from list variable "displayed projects" and saves result to variable "banners"
    * List "Projects List" consists of items, where "Project Link" has attribute "id" from list variable "step2_ids"
    * List "Projects List" consists of items, where "Project Link" has attribute "href" from list variable "hrefs"
    * List "Projects List" consists of items, where "Project Image" has attribute "src" from list variable "banners"

    # Step 3
    * A user clicks on the element "Hidden projects Toggle"
    * A user reads property "id" of each object from list variable "displayed projects including hidden" and saves result to variable "ids"
    * A user reads property "link" of each object from list variable "displayed projects including hidden" and saves result to variable "hrefs"
    * A user reads property "path_banner_small" of each object from list variable "displayed projects including hidden" and saves result to variable "banners"
    * List "Projects List" consists of items, where "Project Link" has attribute "id" from list variable "ids"
    * List "Projects List" consists of items, where "Project Link" has attribute "href" from list variable "hrefs"
    * List "Projects List" consists of items, where "Project Image" has attribute "src" from list variable "banners"

    # Step 4
    * A user clicks on the element "Hidden projects Toggle"
    * List "Projects List" consists of items, where "Project Link" has attribute "id" from list variable "step2_ids"