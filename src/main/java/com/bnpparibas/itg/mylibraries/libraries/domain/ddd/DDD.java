package com.bnpparibas.itg.mylibraries.libraries.domain.ddd;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.bnpparibas.itg.mylibraries.libraries.domain.ddd.DDD.Layer.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@DDD.DddPracticesRef(
    "58: Design must be model-first, not data-first or UI-first\n" +
    "Domain design cannot be tacked on an existing design. Therefore, the domain model must be designed first\n" +
            
    "59: Ubiquitous language \n" +
    "Code is design, design is domain\n" +
    "There must be no mismatch between implementation and design: design components must be incarnated one-to-one by software components.  \n" +
    "There must be no mismatch between the design and the business domain: all design components must correspond one-to-one to business concepts.\n" +
    "In a given Bounded Context, the exact same vocabulary must be used to describe business concepts from analysis through design to implementation.\n" +
            
    "60: The design and implementation process should be iterative\n" +
    "It is almost impossible to model a complex domain on the first try. Therefore, its design should be iterative. New concepts which are discovered during design and implementation should be distilled, then integrated to the Ubiquitous language. Ambiguities and missing/forgotten business rules should be fixed progressively, using intermediate releases. The tunnel effect must be avoided.\n" +
            
    "61: Existing Analysis Patterns should be studied, adapted to the domain context, and used when applicable\n" +
    "Analysis patterns are eleents of reusable OO design applied to common business domains. Some of them are natural candidates to apply in the context of a bank, such as Observations for Corporate Finance, Planning, Accountability, Trading, and Derivative Contracts.\n"
)
public @interface DDD {

    @Documented @Retention(RUNTIME) @Target(ANNOTATION_TYPE)
    @interface DddPracticesRef {
        String value();
    }
    
    enum Layer {
        DOMAIN, INFRASTRUCTURE, APPLICATION, EXPOSITION, PRESENTATION
    }

    @Documented @Retention(RUNTIME) @Target(ANNOTATION_TYPE)
    @DddPracticesRef(
        "7: Apart from the exposition layer, the top-level modules should be Application, Domain, and Infrastructure\n" +
        "The application layer contains use-case logic, the domain layer contains shared domain logic, and the infrastructure contains the implementations of technical contingencies. \n" +
        "This recommendation concerns the horizontal layering by responsibility, it doesnâ€™t preclude deployment units based on vertical slices of functionality. In that case the same Application/Domain/Infrastructure layers should be used within each functional deployment unit.\n" +
        "This recommendation doesnâ€™t concern the granularity of build artifacts.\n"+
        
        "8: The Infrastructure module must depend from the Domain module, not the other way around.\n"+
        "The Dependency Inversion Principle (DIP) states that:\n"+
        "High-level modules must not depend on low-level modules. Both must depend on abstractions\n"+
        "Abstractions must not depend on details. Details must depend on abstractions\n"+
        "When a domain component needs to invoke an infrastructure component, it must do so through an interface in the Domain layer, whose implementation is in the Infrastructure layer (Separated Interface)\n"
    )
    @interface Module {
        Layer value();
    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(DOMAIN)
    @DddPracticesRef(
        "17: Avoid the primitive obsession anti-pattern with Value Objects\n" +
        "Designs that use too many low-level types such as primitives or strings are difficult to understand because theyâ€™re weakly typed and repetitive. This common anti-pattern is called â€œprimitive obsessionâ€� and should be avoided. \n" +
        "Refer to the other Value Object rules and guidelines for examples of behaviour that can be included in Value Objects.\n" +

        "18: Value Objects must be used to aggregate magnitude and unit\n" +
        "Values that have a unit (ex: monetary amount) form a conceptual whole, and are meaningless without their unit. This causes duplication and increases the probability of bugs.\n" +

        "19: Value Objects should have behaviour\n" +
        "All Value Objects have conversion/parsing/formatting/validation logic. Another common example is binary operators. Placing this behaviour in the Value Object itself avoids a lot of duplication.\n" +

        "20: Value Objects must have value-based equality\n" +
        "Value Objects have no identity, and must be considered equal if they hold the same values. A Value Object must be transparently replaceable by another equal Value Object.\n" +
        "Depending on the domain, this criterion can be more complex than structural equality, for instance if the domain asserts that 1 meter is the same as 100 centimeters.\n" +

        "21: Value Objects must be immutable\n" +
        "They represent pure values without identity and therefore cannot change. Therefore, their design must enforce immutability. When a change of state is necessary, it must be implemented by creating a new Value Object holding the resulting value.\n" +

        "22: Value Objects construction must include validation logic when applicable\n" +
        "Since they cannot change, they must be well-formed and therefore enforce their invariants as soon as they are constructed\n" +

        "23: Compose Value Objects when applicable\n" +
        "Since Value Objects are conceptually lightweight, designing as much as possible with just them is advantageous. In particular, they should be composed in new VOs when applicable; for instance Duration and Length can be composed into a new Speed VO.\n" +

        "24: Value Objects must not hold references to Entities\n" +
        "Since VOs have no identity, it is meaningless for them to reference Entities, which do have an identity. If they did, this would violate the principle that equal VOs are replaceable.\n"
    )
    @interface ValueObject {

    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(DOMAIN)
    @DddPracticesRef(
        "11: The names of Entities must be nouns of the Ubiquitous language\n" +
        "An Entity represents an object of the domain. Therefore, its name must be a noun or an (adjective + noun).\n" +

        "12: Entities must protect their collections of elements from direct or indirect state updates\n" +
        "An Entity that owns a collection of elements must be the only one to modify it, otherwise there are too many execution paths that can lead to updates, which is very difficult to understand.\n" +
        "If a collection is exposed by a read-only accessor, special care must be taken to ensure that it is really read-only.\n" +

        "13: Donâ€™t use anemic Entities\n" +
        "Entities must hide their internal state and expose behaviour instead. They must not export their state for external use.\n" +
        "Using the internal state of an Entity in a different component couples that component to the internal representation of the Entity, making the design more brittle. Also, it becomes difficult to reason about the possible states of the Entity. Finally, it is harder to have a mental model of the domain, since one cannot understand what the Entity represents and how it works without examining all the components that access it.\n" +
        "For instance exposing even a read-only set of elements means that another component will have to iterate that set. Instead, collocate the behavior that requires iteration within the entity.\n" +

        "14: Entities must have identity-based equality\n" +
        "Entities are defined by the continuity of their identity, and not by their identity. The identifier field(s) used to represent their identity must be unique within the Entityâ€™s Bounded Context.\n" +

        "15: Entities must have a stable identity\n" +
        "The fields used to identify an Entity must never change, otherwise the continuity of identity is not respected.  This applies as soon as the Entity is created, in particular even before the Entity is saved in a database: this means that for instance, generating the identity field when the Entity is persisted is too late and can cause bugs.\n"
    )
    @interface Entity {
    }

    @Documented @Retention(RUNTIME) @Target({TYPE, FIELD})
    @Module(DOMAIN)
    @DddPracticesRef(
        "16: Entities should use specific Value Object Identifiers (VOIDs)\n" +
        "VOIDs ensure stability of identity, encapsulate the identity field creation rule if it is system-generated, can enforce its format if user-provided, and are strongly typed (make explicit the type of Entity they identify).\n"
    )
    @interface ValueObjectId {
    }

    @Documented @Retention(RUNTIME) @Target(PACKAGE)
    @Module(DOMAIN)
    @DddPracticesRef(
        "45: Distinguish eventual invariants from transactional invariants, define the inconsistency window for eventual invariants\n" +
        "For some invariants, eventual consistency might be a sufficient guarantee. Design must clearly distinguish these from invariants that require the stricter transactional consistency.\n" +
        "It is not possible to determine the Aggregates without analysing which invariants are transactional invariants.\n" +
        "For eventually consistent invariants, define the acceptable inconsistency window. It can range from less than a second to several days. Define the necessary reconciliation procedures if any (ex: periodic batch)\n" +

        "46: Prefer Aggregates to a single complex object graph model\n" +
        "In a complex object graph, multiple invariants (ex: referential integrity invariants) make analysis of transactional invariants difficult. The only solution is then to use a single lock for the whole graph, which is bad for scalability. The unlimited graph approach also makes it difficult to determine the boundary of an Entity composed of other Entities. \n" +
        "The preferred approach is thus cutting the graph along Aggregate boundaries.\n" +

        "47: Aggregate granularity must be no smaller than transactional consistency boundaries\n" +
        "Transactional invariants can only be enforced by updating all involved Entities in a single transaction. \n" +
        "Therefore, the boundaries of Aggregates must be chosen to allow transactional invariants: they must include all Entities that participate to the Aggregateâ€™s invariant(s).\n" +

        "48: No more than one Aggregate instance must be changed within a transaction\n" +
        "Acquiring several locks in a transaction might cause order-dependent deadlocks. Also, it makes persistent more dependent on data store capability (NoSQL databases only implement atomic updates on a single Aggregate instance). \n" +

        "50: Aggregate should refer to each other using VOIDs \n" +
        "Replacing a direct relationship by a common VOID is the safest way to ensure that two Aggregates are not modified in a transaction. \n" +

        "51: Create complex aggregates with a Factory\n" +
        "If creating an aggregate becomes a complex operation, encapsulate this creation inside a Factory. The Factoryâ€™s contract guarantees that it produces a completely built aggregate that satisfies all its invariants.\n"
    )
    @interface Aggregate {
    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(DOMAIN)
    @DddPracticesRef(
        "49: The Aggregate root must be the unique entry point into the Aggregate \n" +
        "The safest way to enforce the Aggregateâ€™s invariants is to only allow updates through the root. For example, the Aggregate Root can increment the Optimistic Locking version field every time it modifies one of its Entities.\n" +
        "Therefore, the Aggregate Root must expose behaviour that modifies its non-root Entities behind the scene, rather than expose them to the outside world\n" +
        "Other components may reference only the Aggregate Root. They may access the non-root Entities with restrictions (only in read-only mode, and only in a transient way â€“storing a reference is not allowed-). The preferred approach is to encapsulate query behaviour in the Aggregate Root. \n"
    )
    @interface AggregateRoot {
    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(DOMAIN)
    @DddPracticesRef(
        "36: Behaviour that doesnâ€™t fit naturally in an Entity should be placed in a Domain Service\n" +
        "This happens when (for instance): \n" +
        "The Ubiquitous Language spontaneously refers to that behaviour as a service (ex: authentication service)\n" +
        "That behaviour straddles two aggregate instances (ex: transfer(from, to))\n"
    )
    @interface DomainService {
    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(DOMAIN)
    @DddPracticesRef(
        "52: Track significant transitions of domain state with Domain Events \n" +
        "Domain Events enable non-intrusive audit trails (traceability, compliance), operational tracking for later analysis (including feedback loops to operational processes), and integration with external systemsâ€¦\n" +
        "Produce them from within the domain, typically when an Entity undergoes a significant transition. \n" +

        "53: Name Domain Events with a past verbal form\n" +
        "Domain Events represent a fact that has already occurred (InvoiceSent, AccountActivated)\n" +

        "54: Include relevant context in Domain Events \n" +
        "So that consumers of Domain Events can react to them, they should hold: \n" +
        "   -The IDs of the Entity(s) that underwent a transition\n" +
        "   -The date and time of the transition\n" +
        "   -A unique technical ID (GUID/UUID) to allow distributed consumers to detect duplicate events\n" +
        "   -(optional) The causes and circumstances of the transition\n" +
        "   -(optional) Snapshots of relevant state before and after the transition\n"
    )
    @interface DomainEvent {
    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(DOMAIN)
    @DddPracticesRef(
        "37: Repositories should be used to represent access to persistent collections of elements\n" +
        "From the point of view of the Domain layer, Repositories simulate in-memory collections of elements. This enables powerful behaviour in the Domain layer. Since the Repositories implementations are in the Infrastructure layer (Separated Interface), this doesnâ€™t cause infrastructure dependencies in the Domain layer.\n" +

        "38: The naming of Repository finders should indicate the criterion used to find elements\n" +
        "It should be possible to guess the criterion by just looking at its name\n" +

        "39: Repository methods that persist elements should not use the save prefix\n" +
        "Save is too vague, prefer add/remove\n" +

        "40: Repositories should participate in transactions, not initiate them\n" +
        ": The granularity of transactions should almost always be the use case. Repositories are too fine-grained, and implementing a use case with several transactions introduces the risk of inconsistent updates (breaking of invariants). \n" +
        "Only in rare cases, an auxiliary transaction can be initiated within a Repository.\n" +

        "41: Repository operations should be idempotent\n" +
        "They emulate in-memory collections, which are idempotent (executing add or remove twice produces the same result as executing it once). Also, idempotent repositories facilitate their usage in resilient distributed architectures.\n" +

        "42: There must be one Repository per Aggregate\n" +
        "For read operations, the Repository loads the Aggregate Root; the other Entities of the Aggregate can only be reached by navigating the association links.\n" +
        "For write operations, the Repository encapsulates creation, removal, and update of an Aggregate without exposing non-root Entities.\n" +

        "43: Repository naming patterns\n" +
        "Several naming patterns are possible; the preferred one is the plural form, for instance Cars for a Repository of Car Aggregates. Other possible patterns include CarRepository, or CarCollection.\n" +
        "When applicable a â€œsmart pluralâ€� can be more domain-specific, for instance Library for a Repository of Books.\n")
    @interface Repository {
    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(INFRASTRUCTURE)
    @interface RepositoryImpl {
    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(DOMAIN)
    @DddPracticesRef(
        "44: Within the Domain layer, software contingencies must be represented by Infrastructure Services \n" +
        "Like Repositories, they use a Separated Interface to avoid Domain layer dependencies toward the Infrastructure layer, while still enabling powerful behaviour in the Domain layer.\n" +
        "Examples of contingencies include: integration, mapping, external communication, â€¦\n"
    )
    @interface InfrastructureService {
    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(INFRASTRUCTURE)
    @interface InfrastructureServiceImpl {
    }

    @Documented @Retention(RUNTIME) @Target(TYPE)
    @Module(APPLICATION)
    @DddPracticesRef(
        "26: Use-case logic  must not be implemented in the Exposition layer\n" +
        "Implementing use cases in the Exposition layer leads to a mixing of business logic and technical preoccupations.\n" +
                
        "27: Use-case logic should be implemented in Application Services, not in domain components \n" +
        "The domain model should not contain use-case-specific business logic. \n" +
        "Exceptions include cases where it is difficult to decide upfront whether a piece of business logic is specific to a use case or constitutes domain knowledge.\n" +
                
        "28: Application Services should be implemented by simple orchestration of Domain layer components\n" +
        "Transaction scripts should be used only in very simple cases. If the orchestration becomes too complex, this means the granularity of Domain components is too fine and they should be refactored (and maybe new abstractions should be introduced).\n" +
                
        "29: The boundary components must be Application Services\n" +
        "Application Services are the unique entry points into the Domain layer. \n" +
        "This implies that the Exposition layer goes through them to invoke business logic.\n" +
                
        "30: The granularity of transactions, authorization checks, and other boundary preoccupations must be Application Services\n" +
        "The granularity of transactions and authorization is the use-case. Transactions and security contexts propagate when the Application Service orchestrates Domain components (Domain components participate in transactions and security contexts initiated by the Application Service or by the framework on behalf of the Application Service).\n" +
        "Multiple transactions with finer granularity within a use case incur the risk of incoherent updates if (for instance) one transaction commits and the other rollbacks.\n" +
                
        "31: Boundary preoccupations should be declared, not called, by Application Services\n" +
        "The declarative model using IoC (Inversion of Control) is less intrusive than the imperative model, induces less pollution of business with technical concerns, and incurs less dependency. \n" +
        "The imperative model should be used only exceptionally, for instance to manage transactions that have a conversation scope.\n" +
                
        "32: The names of use cases in Application Services should be verbs\n" +
        "Application Services implement use cases, which are actions. Therefore, their names should be a verb or (verb + noun). Ex: SearchBooks, AddBookToWishList\n" +
        "If an Application Service includes several related use cases, this naming applies to its methods (eg: SearchBooks, AddBookToWishList). In that case, one possible naming for the Application Serviceâ€™s class is to use the Actions suffix (ex: BookActions).\n" +
        "If on the other hand the Application Service implements a single use case, it is more natural to name the class with a verb (ex: SearchBooks, AddBookToWishList)\n" +
                
        "33: Application Services must not call other Application Services\n" +
        "Unlike other kinds of services, Application Services are not â€œself-composableâ€�. Shared logic must be placed into the Domain layer instead.\n" +
                
        "34: An Application Service should modify a single aggregate instance\n" +
        "Only a single aggregate instance should be modified within a transaction, and the transactionâ€™s scope is the Application.\n" +
        "Therefore, avoid designs involving use cases that require modifying many elements transactionnally. An extreme example of disfavoured design is the transactional spreadsheet, which modifies many unrelated elements. \n" +
                
        "35: Assemble complex view-specific data structures from the Application Service\n" +
        "When a view needs data from several Aggregates, this set of data is specific to the use case. It is thus the Application Serviceâ€™s responsibility to assemble this DTO; it can delegate it to a DTO assembler.\n"
    )
    @interface ApplicationService {
    }

}
