package com.ajith.springbootmongodb.controller;

import com.ajith.springbootmongodb.model.Expense;
import com.ajith.springbootmongodb.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private  final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity addExpense(Expense expense){
        expenseService.addExpense(expense);
        return ResponseEntity.status ( HttpStatus.CREATED ).build();
    }
    @PutMapping
    public ResponseEntity<String> updateExpense(Expense expense){
        expenseService.updateExpense ( expense );
    return ResponseEntity.ok ("Expense updated successfully");
    }
    @GetMapping
    public ResponseEntity< List< Expense>> getAllExpense(){
       return ResponseEntity.ok ( expenseService.getAllExpense() );
    }
    @GetMapping("/{name}")
    public ResponseEntity < Expense > getExpenseByName(@PathVariable String name){
        return ResponseEntity.ok ( expenseService.getExpenseByName ( name ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity < String > deleteExpense(@PathVariable String id){
        expenseService.deleteExpense ( id );
        return ResponseEntity.status (HttpStatus.NO_CONTENT).body ( "Expense deleted successfully with id "+ id );
    }
}
