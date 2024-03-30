package dev.langchain4j.model.cohere;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.cohere.request.enumeration.CohereInputType;
import dev.langchain4j.model.cohere.request.enumeration.CohereLanguageModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CohereEmbeddingIT {

    @Test
    void test_embedding_float() {

        // given
        CohereEmbeddingModel model = CohereEmbeddingModel.withApiKey(System.getenv("COHERE_API_KEY"));

        // Use default Cohere language model : EMBED_ENGLISH_LIGHT_V3_0
        Response<Embedding> embed = model.embed("Hello World");

        assertThat(embed.content().vector()).isNotEmpty();
    }

    @Test
    void test_embedding_float_english_v3() {
        String cohereApiKey = System.getenv("COHERE_API_KEY");
        CohereEmbeddingModel cohereEmbeddingModel = new CohereEmbeddingModel(null,
            cohereApiKey,
            CohereLanguageModel.EMBED_ENGLISH_V3_0,
            CohereInputType.SEARCH_DOCUMENT,
            Duration.ofSeconds(60),
            false,
            false);

        Response<Embedding> embed = cohereEmbeddingModel.embed("Hello World");
        assertThat(embed.content().vector()).isNotEmpty();
    }
}
