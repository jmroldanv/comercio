Feature: Buscador de precios

  Scenario Outline: Comprobar distintas fechas y resultados
    Given Una petición con fecha <date>
    And El identificador de producto es <productId>
    And El identificador de cadena es <brandId>
    When Se solicita el precio con esas características
    Then El código de estado de la respuesta es <expectedStatus>
    And El precio es <expectedPrice>

    Examples:
      | date                  | productId | brandId | expectedStatus | expectedPrice |
      | "2020-06-14T10:00:00" | 35455     | 1       | 200            | 35.50         |
      | "2020-06-14T16:00:00" | 35455     | 1       | 200            | 25.45         |
      | "2020-06-14T21:00:00" | 35455     | 1       | 200            | 35.50         |
      | "2020-06-15T10:00:00" | 35455     | 1       | 200            | 30.50         |
      | "2020-06-16T21:00:00" | 35455     | 1       | 200            | 38.95         |