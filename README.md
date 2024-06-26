# Cohere LangChain4J support

Cleanup of existing Langchain4J Cohere client (backwards compatible).
See also the [Cohere embed API documentation](https://docs.cohere.com/docs/embed)

```java
class CohereClient extends CohereExecute {

    private final CohereApi cohereApi;

    @Builder
    CohereClient(String baseUrl,
                 String apiKey,
                 Duration timeout,
                 boolean logRequests,
                 boolean logResponses) {
        cohereApi = new CohereApiFactory()
            .createHttpClient(baseUrl, apiKey, timeout, logRequests, logResponses).build();
    }

    public RerankResponse rerank(RerankRequest request) {
        return execute(cohereApi.rerank(request));
    }

    public EmbedResponse embed(EmbedRequest request) {
        return execute(cohereApi.embed(request));
    }

    public void embedAsync(EmbedRequest request, AsyncCallback<EmbedResponse> callback) {
        execute(cohereApi.embed(request), callback);
    }
}
```

## ReRank (existed in v0.29.0)

## EmbeddingModel (new)

```java
CohereEmbeddingModel model = CohereEmbeddingModel.withApiKey(System.getenv("COHERE_API_KEY"));

Response<Embedding> embed = model.embed("Hello World");

assertThat(embed.content().vector()).isNotEmpty();
```

Specify model

```Java
String cohereApiKey = System.getenv("COHERE_API_KEY");
CohereEmbeddingModel cohereEmbeddingModel = new CohereEmbeddingModel(
    "https://api.cohere.ai/v1/",
    cohereApiKey,
    CohereLanguageModel.EMBED_ENGLISH_V3_0,
    CohereInputType.SEARCH_DOCUMENT,
    Duration.ofSeconds(60),
    false,
    false);

Response<Embedding> embed = cohereEmbeddingModel.embed("Hello World");
assertThat(embed.content().vector()).isNotEmpty();
```

## Preparations
Extra Cohere features are also included but not yet activated because Langchain4J doesn't support it yet.

### Summarize

### (De)tokenizer

### Generate

### Detect Language

### Classify
