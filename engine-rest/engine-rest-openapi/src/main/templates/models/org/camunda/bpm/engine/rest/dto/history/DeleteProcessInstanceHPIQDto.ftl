{
  "allOf": [
<<<<<<< HEAD
          {
            "$ref": "#/components/schemas/DeleteProcessInstancesDto"
          },
          {
                          "type": "object",
              "properties": {
                "processInstanceQuery": {
                  "$ref": "#/components/schemas/HistoricProcessInstanceQueryDto"
                }
              }
          }
        ]
=======
    {
      "$ref": "#/components/schemas/DeleteProcessInstancesDto"
    },
    {
      "type": "object",
      "properties": {
        "processInstanceQuery": {
          "$ref": "#/components/schemas/ProcessInstanceQueryDto"
        }
      }
    }
  ]
>>>>>>> f2ef5d215d... fix(rest-openapi): fix complex types as oneOf, allOf
}