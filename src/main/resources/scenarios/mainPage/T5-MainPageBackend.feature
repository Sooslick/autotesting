Feature: Sooslick.Art - Main Page

  @Test @T5 @SooslickArtMain @SooslickArt
  Scenario: Main page - backend

    * A user fulfills the precondition "establish an ssh connection with given parameters"
      | username | {property: sooslick.ssh.username} |
      | host     | {property: sooslick.ssh.host}     |
      | port     | {property: sooslick.ssh.port}     |
      | password | {property: sooslick.ssh.password} |
      | variable | ssh session                       |
    * A user fulfills the precondition "download projects.json and read data to test context"
      | session variable | ssh session |

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art"
    * "Sooslick.Art Project - Main page" page opens

    # Step 2
    * Element "Featured project Link" has an attribute "href" with value "[substring]{variable: featured project -> link}"
    * Element "Featured project Link" has an attribute "id" with value "{variable: featured project -> id}"
    * Element "Featured project Link -> Project Image" has an attribute "src" with value "[substring]{variable: featured project -> big}"
