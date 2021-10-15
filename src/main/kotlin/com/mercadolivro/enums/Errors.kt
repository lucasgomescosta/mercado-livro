package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {

    ML000("ML-000", "Acess Denied"),
    ML001("ML-001", "Invalid Request"),
    ML101("ML-0001", "Book [%s] not exists"),
    ML102("ML-102", "Cannot update book with status [%s]"),
    ML201("ML-0002", "Customer [%s] not exists")
}