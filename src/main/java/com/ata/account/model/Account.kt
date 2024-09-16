package com.ata.account.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Account(
    @Id
    @UuidGenerator
    val id: String? = "",
    val balance: BigDecimal? = BigDecimal.ZERO,
    val created: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer", nullable = false)
    val customer: Customer?,

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val transactions: Set<Transaction> = HashSet()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (id != other.id) return false
        if (balance != other.balance) return false
        if (created != other.created) return false
        if (customer != other.customer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (balance?.hashCode() ?: 0)
        result = 31 * result + created.hashCode()
        result = 31 * result + (customer?.hashCode() ?: 0)
        return result
    }
}
