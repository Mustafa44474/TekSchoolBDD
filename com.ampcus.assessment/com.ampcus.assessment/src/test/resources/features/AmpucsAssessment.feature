Feature: Ampcus Code Assessment

  Scenario: Code Assessment
    Given user is on Ampcus contact site
    * user verifies "How to reach us" page is loaded
    * user verifies background image and Ampcus logo is visible
    * user open email and read below data
      | name | email | phone | message | captcha |
    * user verifies submit button is clickable
