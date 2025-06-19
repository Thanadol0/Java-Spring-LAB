package demo.banking_service_test.controller;

import demo.banking_service_test.model.Account;
import demo.banking_service_test.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.With;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/open/{id}/{amount}")
    public Account open(@PathVariable String id, @PathVariable long amount) {
        return service.open(id, amount);
    }

    @PostMapping("/transfer/fast")
    public void fast(@RequestParam String from,
                     @RequestParam String to,
                     @RequestParam long amount) {
        service.transferFast(from, to, amount);
    }

    @PostMapping("/transfer/slow")
    public void slow(@RequestParam String from,
                     @RequestParam String to,
                     @RequestParam long amount) {
        service.transferSlow(from, to, amount);
    }

    @GetMapping("/accounts")
    public Collection<Account> all() { return service.all(); }
}
