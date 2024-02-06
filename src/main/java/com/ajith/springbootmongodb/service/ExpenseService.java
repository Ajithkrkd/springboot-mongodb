package com.ajith.springbootmongodb.service;

import com.ajith.springbootmongodb.model.Expense;
import com.ajith.springbootmongodb.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;
    public void addExpense(Expense expense){
        repository.insert ( expense );
    }
    public void updateExpense(Expense expense){
        Expense savedExpense = repository.findById( expense.getId () )
                .orElseThrow (()->new RuntimeException ( "Could not find expense with id "+ expense.getId () ));
        savedExpense.setExpenseAmount ( expense.getExpenseAmount () );
        savedExpense.setExpenseName (expense.getExpenseName ());
        savedExpense.setExpenseCategory ( expense.getExpenseCategory ());
        repository.save ( savedExpense );

    }
    public List<Expense>  getAllExpense(){
        return repository.findAll();
    }
    public Expense getExpenseByName(String name){
        return repository.findByName ( name )
                .orElseThrow (()->new RuntimeException ( String.format (  "Could not find expense with name "+ name  )));
    }
    public void deleteExpense(String id){
        repository.deleteById ( id );
    }
}
