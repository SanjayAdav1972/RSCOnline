Feature: RS Online End 2 End testing

  Background: RS Component website is up and running
    			List of Scenarios covered:
    			01. E2E test_Home to Check out for battery charger from All Products
    			02. E2E test_Home to check out ABB product from Our Brands
    			03. Search Product by Keyword
    			04. Search Product by Part No
    			05. Search Product by RS Stock No

  Scenario: 01. E2E test_Home to Check out for battery charger from All Products
    Given I login into RS online website home page
    And I ensure my basket is empty
    When I search 'battery chargers in All Products' and added 1 item
    Then I verify 1 item get added in basket

  Scenario: 02. E2E test_Home to check out ABB product from Our Brands
    Given I login into RS online website home page
    And I ensure my basket is empty
    When I search 'ABB product in Our Brands' and added 1 item
    Then I verify 1 item get added in basket

  Scenario: 03. Search Product by Keyword
    Given I login into RS online website home page
    When I search product by 'Keyword' provided in excel sheet
    Then I check the product exists or not

  Scenario: 04. Search Product by Part No
    Given I login into RS online website home page
    When I search product by 'PartNo' provided in excel sheet
    Then I check the product exists or not

  Scenario: 05. Search Product by RS Stock No
    Given I login into RS online website home page
    When I search product by 'RSStockNo' provided in excel sheet
    Then I check the product exists or not
