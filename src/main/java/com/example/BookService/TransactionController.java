package com.example.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
public class TransactionController {
    private final List<Transaction> Transactions = new ArrayList<>();

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return Transactions;
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return Transactions.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction Transaction) {
        Transaction.setId((long) (Transactions.size() + 1));
        Transactions.add(Transaction);
        return Transaction;
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        Transaction existingTransaction = getTransactionById(id);
        if (existingTransaction != null) {
            existingTransaction.setAmount(updatedTransaction.getAmount());
            existingTransaction.setId(updatedTransaction.getId());
            existingTransaction.setDescription(updatedTransaction.getDescription());
        }
        return existingTransaction;
    }

    @DeleteMapping("/{id}")
    public void deleteTransactions(@PathVariable Long id) {
        Transactions.removeIf(Transaction -> Transaction.getId().equals(id));
    }

}
