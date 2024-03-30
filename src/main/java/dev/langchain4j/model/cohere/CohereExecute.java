package dev.langchain4j.model.cohere;

import dev.langchain4j.model.cohere.callback.AsyncCallback;
import dev.langchain4j.model.cohere.exception.CohereException;
import dev.langchain4j.model.cohere.request.EmbedRequest;
import dev.langchain4j.model.cohere.request.RequestLoggingInterceptor;
import dev.langchain4j.model.cohere.request.RerankRequest;
import dev.langchain4j.model.cohere.request.TypedEmbedRequest;
import dev.langchain4j.model.cohere.response.EmbedResponse;
import dev.langchain4j.model.cohere.response.RerankResponse;
import dev.langchain4j.model.cohere.response.ResponseLoggingInterceptor;
import dev.langchain4j.model.cohere.response.TypedEmbedResponse;
import lombok.Builder;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.Duration;

class CohereExecute {

    protected  <T> T execute(Call<T> action) {
        try {
            Response<T> response = action.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw CohereException.fromResponse(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> void execute(Call<T> action, AsyncCallback<T> callback) {
        action.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(CohereException.fromResponse(response));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }
}
