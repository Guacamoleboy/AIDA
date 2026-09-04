# What is the expected JSON output at the end?

This .md file was created in order to establish that prior to creating the additional classes and internal software.

```JSON
{
  "overall_score": 10.0,
  "overall_assessment": "Text about the assessment evaluated by the LLM and provided as output.",
  "criteria": [
    {
      "criterion_name": "Name of the criteria",
      "score": 7.0,
      "comment": "Comment about the assessment of the criteria"
    },
    {
      "criterion_name": "name",
      "score": 6.7,
      "comment": "Comment here lol.."
    }
],
  "suggestions": [
    "Suggestion",
    "Another one",
    "Possibly final suggestion"
  ]
}
```

Notes for myself:

- overallAssessment is the final grade combined divided by the total amount of criterias.
- Criteria is a list of arrays that each have their name, score, comment and id (?)
- The suggestions array is a list of suggestions provided by the LLM from a specific rubric developed by myself ofc.
- criteriaId is not present as it's the output for the user and not an internal request.