Feature: user registry

  I, as a client of bank, want to register into portal bank, to open a new savings account

  Scenario: Successfully user registry

    Given Gustavo open the bank registry application
    When he send your data for sing up
    Then he see the message successfully sing up