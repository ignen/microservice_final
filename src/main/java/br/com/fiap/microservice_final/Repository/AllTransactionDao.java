package br.com.fiap.microservice_final.Repository;

import br.com.fiap.microservice_final.Domain.AllTransaction;
import br.com.fiap.microservice_final.Domain.NewTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllTransactionDao {
    public AllTransaction resume(ArrayList<NewTransaction> transactionArray) {
        long actual = System.currentTimeMillis();

        AllTransaction allTransaction = new AllTransaction();
        List<NewTransaction> result = transactionArray.stream()
                .filter(a -> (actual - a.getData())  <= 60000).collect(Collectors.toList());

        allTransaction.setSum(result.stream()
                .mapToDouble(NewTransaction::getAmount).sum());
        allTransaction.setAvg(result.stream()
                .mapToDouble(NewTransaction::getAmount).average().getAsDouble());
        allTransaction.setMin(result.stream()
                .mapToDouble(NewTransaction::getAmount).min().getAsDouble());
        allTransaction.setMax(result.stream()
                .mapToDouble(NewTransaction::getAmount).max().getAsDouble());
        allTransaction.setCount(result.size());

        return allTransaction;
    }
}
