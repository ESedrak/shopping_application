Feature: Product

  Scenario: Create a new product
    Given a product request with name "product1", description "description1", skuCode "sku1", and price "1000"
    When the product is created
    Then the product should be saved and returned with an id

  Scenario: Get all products
    Given there are products in the repository
    When all products are requested
    Then all products should be returned

  Scenario: Get all products when no products exist
    Given there are no products in the repository
    When all products are requested
    Then an empty list should be returned
