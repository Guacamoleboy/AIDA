package llm.dto.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SuggestionResponse {

    // _________________________________________________________________________________________________________________

    // Expected JSON
    // ____________________
    //
    //	{
    //		"suggestion": "Suggestion about improving the report"
    //	}
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________

    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("suggestion")
    private String suggestion;

}