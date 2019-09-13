package com.movimentacaobancaria.boundaries;

import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;

public class PaymentsApiBoundary implements PaymentsApiBoundaryContract {
    private String PAYMENTS_URL = "https://my-json-server.typicode.com/cairano/backend-test/pagamentos";
    private String RECEIPTS_URL = "https://my-json-server.typicode.com/cairano/backend-test/pagamentos";

    public PaymentsApiBoundary() {
    }

    @Override
    public Integer sendHttpPost(BankingMovement bankingMovement) throws IOException {


        try {
            String url = bankingMovement.getValor() < 0.0 ? PAYMENTS_URL : RECEIPTS_URL;
            HttpPost httpPost = new HttpPost(url);
            String json = bankingMovement.toString();

            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse responseClient = httpClient.execute(httpPost);
            return  responseClient.getStatusLine().getStatusCode();

        } catch (Exception ex) {
            PaymentMovementHelper.print(ex.fillInStackTrace().getMessage());
            throw ex;
        }
    }
}
