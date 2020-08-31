#Author: Allan Sklarow | allan@sklarow.blog.br
Feature: Shopping Cart
  In order to purchase an item
  As a consumer
  I want to add items to my shopping cart

  @RegularTest
  Scenario: Consumer can add an item to shopping cart
    Given I open "http://kabum.com.br"
    When I search for "PC-CBST04"
    And I open the "PC-CBST04" result
    And I add the item to shopping cart
    And I open the shopping cart
    Then I should see the item "PC-CBST04" in my shopping cart

  @RegularTest
  Scenario: Consumer can add multiple itens to shopping cart
    Given I open "http://kabum.com.br"
    When I search for "PC-CBST04"
    And I open the "PC-CBST04" result
    And I add the item to shopping cart
    And I search for "BAP102 - 51920"
    And I open the "BAP102 - 51920" result
    And I add the item to shopping cart
    And I open the shopping cart
    Then I should see the item "PC-CBST04" in my shopping cart
    And I should see the item "BAP102 - 51920" in my shopping cart

  @RegularTest
  Scenario: Consumer can add the same item only once to shopping cart
    Given I open "http://kabum.com.br"
    When I search for "PC-CBST04"
    And I open the "PC-CBST04" result
    And I add the item to shopping cart
    When I search for "PC-CBST04"
    And I open the "PC-CBST04" result
    And I add the item to shopping cart
    And I open the shopping cart
    Then I should see only the item "PC-CBST04" in my shopping cart with quantity 1

  @RegularTest
  Scenario: Consumer can empty the shopping cart
    Given I open "http://kabum.com.br"
    When I search for "BAP102 - 51920"
    And I open the "BAP102 - 51920" result
    And I add the item to shopping cart
    And I open the shopping cart
    And I remove the "BAP102 - 51920" item from shopping cart
    Then I should see the message "O seu carrinho está vazio :("

  @RegularTest
  Scenario: Consumer can remove an item from the shopping cart
    Given I open "http://kabum.com.br"
    When I search for "PC-CBST04"
    And I open the "PC-CBST04" result
    And I add the item to shopping cart
    When I search for "BAP102 - 51920"
    And I open the "BAP102 - 51920" result
    And I add the item to shopping cart
    And I open the shopping cart
    And I remove the "PC-CBST04" item from shopping cart
    Then I should see only the item "BAP102 - 51920" in my shopping cart with quantity 1

  @RegularTest
  Scenario: Consumer can see unavailable items
    Given I open "http://kabum.com.br"
    When I search for "988-000089"
    And I open the "988-000089" result
    Then I should see the image telling me the item are not available

  @RegularTest
  Scenario: Consumer can't see non existing items
    Given I open "http://kabum.com.br"
    When I search for "Unexisting Item"
    Then I should see the message "Lamentamos, nenhum produto encontrado com esse critério de pesquisa"