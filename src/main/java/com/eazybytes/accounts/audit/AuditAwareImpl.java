package com.eazybytes.accounts.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityListeners;

@Component("auditAwareImpl")
@EntityListeners(AuditingEntityListener.class)
public class AuditAwareImpl implements AuditorAware<String> {
	@Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }
}
