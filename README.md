# Cohere LangChain4J support

Cleanup of existing Langchain4J Cohere client (backwards compatible).

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

    public TypedEmbedResponse typedEmbed(TypedEmbedRequest request) {
        return execute(cohereApi.typedEmbed(request));
    }

    public void embedAsync(EmbedRequest request, AsyncCallback<EmbedResponse> callback) {
        execute(cohereApi.embed(request), callback);
    }

    public void typedEmbedAsync(TypedEmbedRequest request, AsyncCallback<TypedEmbedResponse> callback) {
        execute(cohereApi.typedEmbed(request), callback);
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

## Preparations

### Summarize

### (De)tokenizer

### Generate

### Detect Language

### Classify
