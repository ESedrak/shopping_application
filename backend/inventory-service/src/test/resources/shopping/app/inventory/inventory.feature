Feature: Inventory

  Scenario: Check if a product is in stock
    Given a product with skuCode "iphone_15" and quantity 10 in the inventory
    When a stock check is requested for skuCode "iphone_15" and quantity 1
    Then the service should return true

  Scenario: Check if a product is out of stock
    Given a product with skuCode "iphone_15" and quantity 0 in the inventory
    When a stock check is requested for skuCode "iphone_15" and quantity 1
    Then the service should return false
