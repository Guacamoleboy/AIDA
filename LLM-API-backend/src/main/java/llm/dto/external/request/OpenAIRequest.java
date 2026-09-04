package llm.dto.external.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenAIRequest {

    // _________________________________________________________________________________________________________________

    // Expected JSON
    // ____________________
    //
    //	{
    //		"model": "gpt-5.2",
    //		"instructions": "System prompt here...",
    //		"input": "User prompt here..."
    //	}
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________

    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("model")
    private String model;

    @JsonProperty("instructions")
    private String instructions;

    @JsonProperty("input")
    private String input;

}