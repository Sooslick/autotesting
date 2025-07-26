Feature: Sooslick.Art - Main Page

  @Test @T12 @SooslickArtMain @SooslickArt
  Scenario: Portfolio page - backend

    * A user fulfills the precondition "establish an ssh connection with given parameters" with following parameters
      | username | {property: sooslick.ssh.username} |
      | host     | {property: sooslick.ssh.host}     |
      | port     | {property: sooslick.ssh.port}     |
      | password | {property: sooslick.ssh.password} |
      | variable | ssh session                       |
    * A user fulfills the precondition "download projects.json and read data to test context" with following parameters
      | session variable | ssh session |

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/portfolio"
    * "Sooslick.Art Project - Portfolio page" page opens

    # Step 2
    * A user reads property "id" of each object from list variable "showcase projects" and saves result to variable "ids"
    * A user reads property "link" of each object from list variable "showcase projects" and saves result to variable "hrefs"
    * A user reads property "banner" of each object from list variable "showcase projects" and saves result to variable "banners"
    * List "Showcase projects List" consists of items, where "Project Link" has attribute "id" from list variable "ids"
    * List "Showcase projects List" consists of items, where "Project Link" has attribute "href" from list variable "hrefs"
    * List "Showcase projects List" consists of items, where "Project Link -> Project Image" has attribute "src" from list variable "banners"
    * List "Showcase projects List" has description lines, corresponding to list variable "showcase projects"