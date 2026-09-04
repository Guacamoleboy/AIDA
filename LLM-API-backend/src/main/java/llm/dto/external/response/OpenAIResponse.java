package llm.dto.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenAIResponse {

    // Links:
    // https://developers.openai.com/api/reference/resources/responses

    // _________________________________________________________________________________________________________________

    // Expected JSON
    // ____________________
    //
    //	{
    //		"output_text": "Generated response from OpenAI"
    //	}
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________

    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("output_text")
    private String outputText;

}