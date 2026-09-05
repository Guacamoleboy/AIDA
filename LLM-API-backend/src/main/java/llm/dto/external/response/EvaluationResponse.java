package llm.dto.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvaluationResponse {

    // _________________________________________________________________________________________________________________

    // Expected JSON
    // ____________________
    //
    //	{
    //		"overall_score": 10.0,
    //		"overall_assessment": "Text about the assessment evaluated by the LLM and provided as output.",
    //		"criteria": [
    //			{
    //				"criterion_name": "Name of the criterion",
    //				"score": 7.0,
    //				"comment": "Comment about the assessment of the criterion"
    //			}
    //		],
    //		"suggestions": [
    //			"Suggestion",
    //			"Another one",
    //			"Possibly final suggestion"
    //		]
    //	}
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________

    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("overall_score")
    private double overallScore;

    @JsonProperty("overall_assessment")
    private String overallAssessment;

    @JsonProperty("criteria")
    private List<CriterionEvaluationResponse> criteria;

    @JsonProperty("suggestions")
    private List<String> suggestions;

}
