package llm.dto.external.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class EvaluationRequest {

    // _________________________________________________________________________________________________________________
    // Frontend to Backend

    // Expected JSON
    // ____________________
    //
    //  {
    //	    "submission": "Text provided by the user here..."
    //	}
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________

    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("submission")
    private String submission;

}