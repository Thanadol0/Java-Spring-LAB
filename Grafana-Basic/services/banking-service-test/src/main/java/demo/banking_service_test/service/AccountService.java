package demo.banking_service_test.service;

import demo.banking_service_test.model.Account;
import io.micrometer.observation.annotation.Observed;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@Observed(name = "account.service")           // Micrometer observation tag
public class AccountService {

    private final Map<String, Account> store = new ConcurrentHashMap<>();

    @WithSpan
    public Account open(String id, long init) {
        var acc = new Account();
        acc.setId(id);
        acc.setBalance(init);
        store.put(id, acc);
        log.info("Opened account {}", id);
        return acc;
    }

    /** O(1) transfer */

    @WithSpan
    public void transferFast(String from, String to, long amount) {
        var a = store.get(from);
        var b = store.get(to);
        a.setBalance(a.getBalance() - amount);
        b.setBalance(b.getBalance() + amount);
    }


    /** O(n²) transfer – จงใจช้าเพื่อให้เห็นต่างใน Grafana */
    @WithSpan
    public void transferSlow(String from, String to, long amount) {
        var list = new ArrayList<>(store.values());
        for (Account outer : list) {
            for (Account inner : list) {
                if (outer.getId().equals(from) && inner.getId().equals(to)) {
                    outer.setBalance(outer.getBalance() - amount);
                    inner.setBalance(inner.getBalance() + amount);
                }
            }
        }
    }

    public Collection<Account> all() { return store.values(); }
}
