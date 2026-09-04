package llm.dto.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CriterionEvaluationResponse {

    // _________________________________________________________________________________________________________________

    // Expected JSON
    // ____________________
    //
    //	{
    //		"criterion_name": "Name of the criterion",
    //		"score": 7.0,
    //		"comment": "Comment about the assessment of the criterion",
    //	}
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________

    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("criterion_name")
    private String criterionName;

    @JsonProperty("score")
    private double score;

    @JsonProperty("comment")
    private String comment;

}