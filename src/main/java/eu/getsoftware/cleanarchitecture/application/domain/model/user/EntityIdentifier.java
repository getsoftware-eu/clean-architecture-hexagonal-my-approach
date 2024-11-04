package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * Контракты и соглашения: Если связь между контекстами требует передачи данных, DDD предлагает использовать интеграционные механизмы, 
 * такие как Domain Events, Anti-Corruption Layer или API, которые позволят передавать OrderId между контекстами. 
 * Это позволяет избежать зависимости одного контекста от внутренней реализации другого.
 */
public interface EntityIdentifier   {
    
    public String value();
}