Feature: Sooslick.Art - Main Page

  @Test @T6 @SooslickArtMain @SooslickArt
  Scenario: Projects page - backend

    * A user fulfills the precondition "establish an ssh connection with given parameters"
      | username | {property: sooslick.ssh.username} |
      | host     | {property: sooslick.ssh.host}     |
      | port     | {property: sooslick.ssh.port}     |
      | password | {property: sooslick.ssh.password} |
      | variable | ssh session                       |
    * A user fulfills the precondition "download projects.json and read data to test context"
      | session variable | ssh session |

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/projects"
    * "Sooslick.Art Project - Projects page" page opens

    # Step 2
    * A user reads property "id" of each object from list variable "displayed projects" and saves result to variable "ids"
    * A user reads property "link" of each object from list variable "displayed projects" and saves result to variable "hrefs"
    * A user reads property "banner" of each object from list variable "displayed projects" and saves result to variable "banners"
    * List "Projects List" consists of items, where "Project Link" has attribute "id" from list variable "ids"
    * List "Projects List" consists of items, where "Project Link" has attribute "href" from list variable "hrefs"
    * List "Projects List" consists of items, where "Project Image" has attribute "src" from list variable "banners"

    # Step 3
    * A user clicks on the element "Hidden projects Toggle"
    * A user reads property "id" of each object from list variable "displayed projects including hidden" and saves result to variable "ids"
    * A user reads property "link" of each object from list variable "displayed projects including hidden" and saves result to variable "hrefs"
    * A user reads property "banner" of each object from list variable "displayed projects including hidden" and saves result to variable "banners"
    * List "Projects List" consists of items, where "Project Link" has attribute "id" from list variable "ids"
    * List "Projects List" consists of items, where "Project Link" has attribute "href" from list variable "hrefs"
    * List "Projects List" consists of items, where "Project Image" has attribute "src" from list variable "banners"