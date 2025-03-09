Feature: Sooslick.Art - Theme Randomizer

  @Test @T32 @Themegen @SooslickArt
  Scenario: Theme Randomizer main page - random content

    # Step 1
    * A user opens a new browser window and follows the link "https://sooslick.art/themegen"
    * "Theme Randomizer main page" page opens
    * The active tab has a title "Литературный рандомайзер"

    * Browser performance logs has entry with following parameters
      | /message/method                 | Network.responseReceived             |
      | /message/params/response/url    | [substring] /api/generator.php?qty=9 |
      | /message/params/type            | XHR                                  |
      | /message/params/response/status | 200                                  |
    * A user saves response body of request "[substring] /generator.php" as JSON to variable "generator data"
    # forget previous logs
    * A user begin watching browser logs

    * Element "Theme text" has a text "{json variable: generator data/r/0}"
    * A user clicks on the element "Reload Icon"
    * Browser performance logs has not entries with following parameters
      | /message/params/response/url | [substring] /generator.php |

    * Element "Theme text" has a text "{json variable: generator data/r/1}"
    * A user clicks on the element "Reload Icon"
    * Browser performance logs has not entries with following parameters
      | /message/params/response/url | [substring] /generator.php |

    * Element "Theme text" has a text "{json variable: generator data/r/2}"
    * A user clicks on the element "Reload Icon"
    * Browser performance logs has not entries with following parameters
      | /message/params/response/url | [substring] /generator.php |

    * Element "Theme text" has a text "{json variable: generator data/r/3}"
    * A user clicks on the element "Reload Icon"
    * Browser performance logs has not entries with following parameters
      | /message/params/response/url | [substring] /generator.php |

    * Element "Theme text" has a text "{json variable: generator data/r/4}"
    * A user clicks on the element "Reload Icon"
    * Browser performance logs has not entries with following parameters
      | /message/params/response/url | [substring] /generator.php |

    * Element "Theme text" has a text "{json variable: generator data/r/5}"
    * A user clicks on the element "Reload Icon"
    * Browser performance logs has not entries with following parameters
      | /message/params/response/url | [substring] /generator.php |

    * Element "Theme text" has a text "{json variable: generator data/r/6}"
    * A user clicks on the element "Reload Icon"
    * Browser performance logs has not entries with following parameters
      | /message/params/response/url | [substring] /generator.php |

    * Element "Theme text" has a text "{json variable: generator data/r/7}"
    * A user clicks on the element "Reload Icon"
    * Browser performance logs has not entries with following parameters
      | /message/params/response/url | [substring] /generator.php |

    * Element "Theme text" has a text "{json variable: generator data/r/8}"
    * A user clicks on the element "Reload Icon"

    * Browser performance logs has entry with following parameters
      | /message/method                 | Network.responseReceived       |
      | /message/params/response/url    | [substring] /api/generator.php |
      | /message/params/type            | XHR                            |
      | /message/params/response/status | 200                            |
    * A user saves response body of request "[substring] /generator.php" as JSON to variable "generator data"
    * Element "Theme text" has a text "{json variable: generator data/r/0}"